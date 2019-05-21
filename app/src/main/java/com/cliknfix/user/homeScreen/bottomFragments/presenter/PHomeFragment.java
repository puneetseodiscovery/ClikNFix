package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.homeScreen.bottomFragments.HomeFragment;
import com.cliknfix.user.homeScreen.bottomFragments.IHomeFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.IMHomeFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.MHomeFragment;
import com.cliknfix.user.responseModels.CategoriesListResponseModel;

public class PHomeFragment implements IPHomeFragment {

    IHomeFragment iHomeFragment;
    IMHomeFragment imHomeFragment;

    public PHomeFragment(HomeFragment homeFragment) {
        this.iHomeFragment = homeFragment;
        this.imHomeFragment = new MHomeFragment(this);
    }

    @Override
    public void getCategoriesList(String token) {
        imHomeFragment.getCategoriesList(token);
    }

    @Override
    public void getCategoriesListSuccessResponse(CategoriesListResponseModel categoriesListResponseModel) {
        iHomeFragment.getCategoryListSuccessFromPresenter(categoriesListResponseModel);
    }

    @Override
    public void getCategoriesListFailureResponse(String message) {
        iHomeFragment.getCategoryListFailureFromPresenter(message);
    }
}
