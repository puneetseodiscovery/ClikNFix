package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.responseModels.CategoriesListResponseModel;

public interface IPHomeFragment {
    public void getCategoriesList(String token);
    public void getCategoriesListSuccessResponse(CategoriesListResponseModel categoriesListResponseModel);
    public void getCategoriesListFailureResponse(String message);
}
