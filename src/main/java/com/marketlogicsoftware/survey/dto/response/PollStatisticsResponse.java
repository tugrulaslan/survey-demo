package com.marketlogicsoftware.survey.dto.response;

public class PollStatisticsResponse {
    private long choiceId;
    private long totalCount;

    public PollStatisticsResponse() {
    }

    public PollStatisticsResponse(long choiceId, long totalCount) {
        this.choiceId = choiceId;
        this.totalCount = totalCount;
    }

    public long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(long choiceId) {
        this.choiceId = choiceId;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
