package com.example.loadersapplication;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class quakesLoaders extends AsyncTaskLoader<ArrayList<DataContainerModel>> {
    private static final String TAG = quakesLoaders.class.getSimpleName();
    private String mURL;

    public quakesLoaders(Context context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    ;

    @Override
    public ArrayList<DataContainerModel> loadInBackground() {
        if (mURL == null) {
            return null;
        }
        ;
        //creating a data fetch back

        ArrayList<DataContainerModel> dataFetchedBack = QuakesUtiles.fetchDataBackToQuakesLoader(mURL);
        return dataFetchedBack;
    }

    ;
};
