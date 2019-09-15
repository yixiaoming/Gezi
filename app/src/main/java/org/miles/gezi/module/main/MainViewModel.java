package org.miles.gezi.module.main;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import org.miles.gank.GankFragment;
import org.miles.gezi.R;
import org.miles.kaiyan.main.KaiyanFragment;
import org.miles.music.MusicFragment;
import org.miles.personal.PersonalFragment;

public class MainViewModel extends ViewModel {

    public static final String TAG = "MainViewModel";

    private static final String FRAGMENT_TAG_KAIYAN = "fragment_tag_kaiyan";
    private static final String FRAGMENT_TAG_GANK = "fragment_tag_gank";
    private static final String FRAGMENT_TAG_MUSIC = "fragment_tag_music";
    private static final String FRAGMENT_TAG_PERSONAL = "fragment_tag_personal";

    private Fragment mDisplayFragment;
    private KaiyanFragment mKaiyanFragment;
    private GankFragment mGankFragment;
    private MusicFragment mMusicFragment;
    private PersonalFragment mPersonalFragment;

    public boolean showFragment(@IdRes int navigationId, @IdRes int containerId, FragmentManager fragmentManager) {
        Fragment tempFragment = null;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (navigationId) {
            case R.id.action_gank:
                if (mGankFragment == null) {
                    mGankFragment = GankFragment.newInstance();
                    transaction.add(containerId, mGankFragment, FRAGMENT_TAG_GANK);
                }
                tempFragment = mGankFragment;
                break;
            case R.id.action_video:
                if (mKaiyanFragment == null) {
                    mKaiyanFragment = KaiyanFragment.newInstance();
                    transaction.add(containerId, mKaiyanFragment, FRAGMENT_TAG_KAIYAN);
                }
                tempFragment = mKaiyanFragment;
                break;
            case R.id.action_music:
                if (mMusicFragment == null) {
                    mMusicFragment = MusicFragment.newInstance();
                    transaction.add(containerId, mMusicFragment, FRAGMENT_TAG_MUSIC);
                }
                tempFragment = mMusicFragment;
                break;
            case R.id.action_personal:
                if (mPersonalFragment == null) {
                    mPersonalFragment = PersonalFragment.newInstance();
                    transaction.add(containerId, mPersonalFragment, FRAGMENT_TAG_PERSONAL);
                }
                tempFragment = mPersonalFragment;
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
