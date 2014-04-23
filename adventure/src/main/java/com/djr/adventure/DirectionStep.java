package com.djr.adventure;


public class DirectionStep {
    private String mDirection;
    private String mDirecitonInfo;
    private int mImageSource;

    public DirectionStep(String mDirection, String mDirecitonInfo) {
        this.mDirection = mDirection;
        this.mDirecitonInfo = mDirecitonInfo;
        mImageSource = setmImageSource(mDirection);
    }

    private int setmImageSource(String mDirection) {
        return 0;
    }

    public String getmDirection() {  return mDirection;  }

    public String getmDirecitonInfo() {  return mDirecitonInfo; }

    public int getmImageSource() { return mImageSource; }
}
