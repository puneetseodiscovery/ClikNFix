package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.responseModels.TechDetailResponseModel;

public interface IPPastJobsAdapter {
    void getTechDetail(int id, String token);
    void getTechDetailSuccessResponse(TechDetailResponseModel techDetailResponseModel);
    void getTechDetailFailureResponse(String message);
}
