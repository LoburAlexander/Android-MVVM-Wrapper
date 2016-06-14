package by.pavlovskii.ilya.mvvm.test.viewmodel;

import android.support.annotation.NonNull;

import by.mvvmwrapper.viewmodel.BaseViewModel;
import by.pavlovskii.ilya.mvvm.test.activity.ProfileActivity;
import by.pavlovskii.ilya.mvvm.test.databinding.ActivityProfileBinding;
import by.pavlovskii.ilya.mvvm.test.handlers.ProfileHandler;
import by.pavlovskii.ilya.mvvm.test.viewdata.ProfileViewData;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 06.01.16<br>
 * Time: 19:20<br>
 * Project name: MVVMtest<br>
 * ===================================================================================
 * //TODO Add description<br>
 * ===================================================================================
 */
public class ProfileActivityViewModel
        extends BaseViewModel<ProfileViewData, ActivityProfileBinding, ProfileActivity>
        implements ProfileHandler.ProfileHandlerListener {

    //======================================================
    //------------------------Fields------------------------
    //======================================================
    private ProfileHandler mProfileHandler;

    //======================================================
    //-------------------Override methods-------------------
    //======================================================
    @NonNull
    @Override
    public ProfileViewData initViewData() {
        ProfileViewData viewData = new ProfileViewData();
        mProfileHandler = new ProfileHandler(this);
        return viewData;
    }

    @Override
    public void bindViewData(@NonNull ActivityProfileBinding viewDataBinding) {
        viewDataBinding.setViewData(mViewData);
        viewDataBinding.setHandler(mProfileHandler);
    }

    @Override
    public ProfileViewData getViewData() {
        return mViewData;
    }

    //======================================================
    //------------------------Listeners---------------------
    //======================================================

}
