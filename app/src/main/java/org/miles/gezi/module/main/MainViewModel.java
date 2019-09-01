package org.miles.gezi.module.main;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import org.miles.gezi.R;
import org.miles.kaiyan.main.KaiyanFragment;

public class MainViewModel extends ViewModel {

    public static final String TAG = "MainViewModel";

    private static final String FRAGMENT_TAG_KAIYAN = "fragment_tag_kaiyan";

    private Fragment mDisplayFragment;
    private KaiyanFragment mKaiyanFragment;

    public boolean showFragment(@IdRes int navigationId, @IdRes int containerId, FragmentManager fragmentManager) {
        Fragment tempFragment = null;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (navigationId) {
            case R.id.action_video:
                if (mKaiyanFragment == null) {
                    mKaiyanFragment = KaiyanFragment.newInstance();
                    transaction.add(containerId, mKaiyanFragment, FRAGMENT_TAG_KAIYAN);
                }
                tempFragment = mKaiyanFragment;
                break;
            default:
                break;
        }
        if (tempFragment == null) {
            throw new IllegalArgumentException("Error show Fragment, not found!");
        }

        if (mDisplayFragment == null) {
            mDisplayFragment = tempFragment;
            transaction.show(mDisplayFragment);
            transaction.commit();
            return true;
        }

        transaction.hide(mDisplayFragment);
        transaction.show(tempFragment);
        transaction.commit();
        mDisplayFragment = tempFragment;
        return true;
    }
}
