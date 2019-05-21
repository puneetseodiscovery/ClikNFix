package com.cliknfix.user.homeScreen.bottomFragments;

import com.cliknfix.user.responseModels.CategoriesListResponseModel;

public interface IHomeFragment {
    void getCategoryListSuccessFromPresenter(CategoriesListResponseModel categoriesListResponseModel);
    void getCategoryListFailureFromPresenter(String message);
}
