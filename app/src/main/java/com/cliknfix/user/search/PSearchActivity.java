package com.cliknfix.user.search;

import com.cliknfix.user.responseModels.SearchTechResponseModel;

public class PSearchActivity implements IPSearchActivity {

    private ISearchActivity iSearchActivity;
    private IMSearchActivity imSearchActivity;


    public PSearchActivity(SearchActivity searchActivity) {
        iSearchActivity = searchActivity;
        imSearchActivity = new MSearchActivity(this);
    }

    @Override
    public void searchTech(int categoryId, String problem, double latitude, double longitude, String token) {
        imSearchActivity.searchTech(categoryId,problem,latitude,longitude,token);
    }

    @Override
    public void onSearchTechSuccessResponse(SearchTechResponseModel searchTechResponseModel) {
        iSearchActivity.searchTechSuccessResponseFromPresenter(searchTechResponseModel);
    }

    @Override
    public void onSearchTechFailureResponse(String message) {
        iSearchActivity.searchTechFailureResponseFromPresenter(message);
    }

    @Override
    public void noTechFoundResponse(String message) {
        iSearchActivity.noTechFoundResponseFromPresenter(message);
    }
}
