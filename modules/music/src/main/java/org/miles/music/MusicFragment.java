package org.miles.music;

import org.miles.lib.mvvm.BaseViewModelFragment;
import org.miles.music.databinding.MusicFragmentBinding;

public class MusicFragment
        extends BaseViewModelFragment<MusicFragmentBinding, MusicFragmentModel> {
    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.music_fragment;
    }

    @Override
    protected Class<MusicFragmentModel> getViewModelClass() {
        return MusicFragmentModel.class;
    }
}
