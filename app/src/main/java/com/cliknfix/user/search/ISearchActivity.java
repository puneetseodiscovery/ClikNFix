package com.cliknfix.user.search;

import com.cliknfix.user.responseModels.SearchTechResponseModel;

public interface ISearchActivity {
    void searchTechSuccessResponseFromPresenter(SearchTechResponseModel searchTechResponseModel);
    void searchTechFailureResponseFromPresenter(String message);
    void noTechFoundResponseFromPresenter(String message);
}
