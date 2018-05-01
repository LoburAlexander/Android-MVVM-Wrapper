package by.pavlovskii.ilya.mvvm.test.ui.fragments.timer.di;

import android.support.annotation.NonNull;

import by.mvvmwrapper.dagger.map.ViewModelKey;
import by.mvvmwrapper.dagger.scope.FragmentScope;
import by.mvvmwrapper.viewmodel.BaseViewModel;
import by.pavlovskii.ilya.mvvm.test.ui.fragments.timer.TimerViewData;
import by.pavlovskii.ilya.mvvm.test.ui.fragments.timer.TimerViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 08.12.2017<br>
 * Time: 17:10<br>
 * Project name: MVVMtest<br>
 * ===================================================================================<br>
 */
@Module
public abstract class TimerViewModelModule {

    @Provides
    @FragmentScope
    static TimerViewData viewData() {
        return new TimerViewData();
    }

    @Binds
    @IntoMap
    @ViewModelKey(TimerViewModel.class)
    abstract BaseViewModel bindTimerViewModel(@NonNull TimerViewModel viewModel);

}