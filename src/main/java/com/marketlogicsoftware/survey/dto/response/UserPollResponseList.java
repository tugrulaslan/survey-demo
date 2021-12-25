package com.marketlogicsoftware.survey.dto.response;

import java.util.List;

public class UserPollResponseList {
    List<UserPollResponse> responseList;

    public UserPollResponseList() {
    }

    public UserPollResponseList(List<UserPollResponse> responseList) {
        this.responseList = responseList;
    }

    public List<UserPollResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<UserPollResponse> responseList) {
        this.responseList = responseList;
    }
}
