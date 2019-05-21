package com.cliknfix.user.search;

import com.cliknfix.user.responseModels.SearchTechResponseModel;

public interface IPSearchActivity {
    void searchTech(int categoryId, String problem, double latitude, double longitude, String token);
    void onSearchTechSuccessResponse(SearchTechResponseModel searchTechResponseModel);
    void onSearchTechFailureResponse(String message);
    void noTechFoundResponse(String message);
}
