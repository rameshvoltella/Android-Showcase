package mobi.mergen.androidshowcase.ui.act_splash;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobi.mergen.androidshowcase.R;
import mobi.mergen.androidshowcase.common.Navigator;
import mobi.mergen.androidshowcase.databinding.ActivitySplashBinding;
import mobi.mergen.androidshowcase.ui.act_main.MainActivity;
import mobi.mergen.androidshowcase.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private Disposable timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int layoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean doubleClickToExitEnabled() {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
    }

    @Override
    protected void onStop() {
        stopTimer();
        super.onStop();
    }

    private void startTimer() {
        timer = Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    Navigator.finishAndStart(this, MainActivity.class);
                });
    }

    private void stopTimer() {
        if (timer != null) {
            timer.dispose();
            timer = null;
        }
    }
}
