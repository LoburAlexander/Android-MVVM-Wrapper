package by.mvvmwrapper.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import by.mvvmwrapper.exceptions.ExceptionHandlerChain;
import by.mvvmwrapper.interfaces.DialogActionsDelegate;
import by.mvvmwrapper.viewmodel.BaseViewModel;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 04.08.16<br>
 * Time: 13:09<br>
 * Project name: MVVMtest<br>
 * ===================================================================================
 * Base {@link android.support.v4.app.Fragment} realization of view component<br>
 * ===================================================================================
 */
public abstract class BaseFragmentSupport<M extends BaseViewModel, B extends ViewDataBinding>
        extends android.support.v4.app.Fragment
        implements DialogActionsDelegate {

    //======================================================
    //------------------------Fields------------------------
    //======================================================
    @NonNull
    protected B mBinding;
    @NonNull
    protected M mViewModel;

    private Dialog mProgressDialog;

    //======================================================
    //-------------------Abstract methods-------------------
    //======================================================
    @LayoutRes
    protected abstract int getLayoutRes();
    @NonNull
    protected abstract M initViewModel();

    //======================================================
    //-------------------Protected methods------------------
    //======================================================
    @NonNull
    protected ExceptionHandlerChain initExceptionHandlerChain() {
        return new ExceptionHandlerChain();
    }

    //======================================================
    //-------------------Override methods-------------------
    //======================================================
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = initViewModel();
        mViewModel.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflateBinding(inflater, container, savedInstanceState);
        if (mBinding == null) {
            throw new NullPointerException("ViewDataBinding must be initialized");
        }
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.bindViewData(mBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModel.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mViewModel.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mViewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModel.onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public Dialog getProgressDialog() {
        if (getActivity() instanceof DialogActionsDelegate) {
            return ((DialogActionsDelegate) getActivity()).getProgressDialog();
        }
        if (mProgressDialog == null) {
            mProgressDialog = getAlertDialogBuilder()
                    .progress(true, 0)
                    .build();
        }
        return mProgressDialog;
    }

    @Override
    public void showErrorDialog(@Nullable String message) {
        if (getActivity() instanceof DialogActionsDelegate) {
            ((DialogActionsDelegate) getActivity()).showErrorDialog(message);
        }
    }

    @NonNull
    @Override
    public MaterialDialog.Builder getAlertDialogBuilder() {
        if (getActivity() instanceof DialogActionsDelegate) {
            return ((DialogActionsDelegate) getActivity()).getAlertDialogBuilder();
        }
        return new MaterialDialog.Builder(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.onDestroy();
        mBinding.unbind();
    }

    @NonNull
    protected M getViewModel() {
        return mViewModel;
    }

    protected void inflateBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        if (mBinding == null) {
            throw new NullPointerException("ViewDataBinding must be initialized");
        }
    }
}