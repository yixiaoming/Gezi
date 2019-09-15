package org.miles.personal;

import org.miles.lib.mvvm.BaseViewModelFragment;
import org.miles.personal.databinding.PersonalFragmentBinding;

public class PersonalFragment
        extends BaseViewModelFragment<PersonalFragmentBinding, PersonalFragmentModel> {
    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.personal_fragment;
    }

    @Override
    protected Class<PersonalFragmentModel> getViewModelClass() {
        return PersonalFragmentModel.class;
    }
}
