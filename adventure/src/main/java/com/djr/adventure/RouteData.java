package com.djr.adventure;


import java.util.ArrayList;
import java.util.HashMap;

public class RouteData {
    private HashMap<String,Boolean> mPreferenceMap;
    private ArrayList<DirectionStep> mDirectionSteps;
    private ArrayList<String> mRoute;

    public RouteData(HashMap<String,Boolean> preferenceMap) {
        mPreferenceMap = preferenceMap;
    }




    public ArrayList<DirectionStep> getmDirectionSteps() { return mDirectionSteps; }

    public ArrayList<String> getmRoute() {  return mRoute; }
}
