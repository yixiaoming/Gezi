package org.miles.kaiyan;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.miles.kaiyan.main.KaiyanFragment;

public class ModuleActivity extends AppCompatActivity {

    private KaiyanFragment mKaiyanFragment;
    private String mKaiyanFragmentTag = KaiyanFragment.class.getCanonicalName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity);
        mKaiyanFragment = (KaiyanFragment) getSupportFragmentManager().findFragmentByTag(mKaiyanFragmentTag);
        if (mKaiyanFragment == null) {
            mKaiyanFragment = KaiyanFragment.newInstance();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentView, mKaiyanFragment, mKaiyanFragmentTag);
        transaction.commit();
    }
}
