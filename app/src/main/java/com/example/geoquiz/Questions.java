package com.example.geoquiz;

public class Questions {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mIsAnswered;

    public int getTextResId() {
        return mTextResId;
    }
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
    public boolean isAnswered() {
        return mIsAnswered;
    }
    public void setAnswered(boolean answered) {
        mIsAnswered = answered;
    }

    public Questions(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mIsAnswered = false;
    }
}
