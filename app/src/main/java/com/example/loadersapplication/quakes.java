package com.example.loadersapplication;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class quakes extends AppCompatActivity implements LoaderCallbacks<ArrayList<DataContainerModel>> {

    private static final String TAG = quakes.class.getSimpleName();
    private static final String QUAKES_URL_API_REQUEST =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";
    TemplateAdapterModel QuakesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes);
        //playing like the orginal play in the past
        ListView QuakesList = (ListView) findViewById(R.id.List);
        QuakesAdapter = new TemplateAdapterModel(this, new ArrayList<DataContainerModel>());
        QuakesList.setAdapter(QuakesAdapter);
        //end of the adapter section
        QuakesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataContainerModel currentData = QuakesAdapter.getItem(position); //that's is the trick
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(currentData.getURL()));
                startActivity(intent);
            }

            ;
        });

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    ;

    //this is the pretty old loaderManger
    @Override
    public Loader<ArrayList<DataContainerModel>> onCreateLoader(int id, Bundle args) {
        return new quakesLoaders(this, QUAKES_URL_API_REQUEST);
    }

    ;

    @Override
    public void onLoadFinished(Loader<ArrayList<DataContainerModel>> loader, ArrayList<DataContainerModel> earthquakes) {
        // Clear the adapter of previous earthquake data
        QuakesAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            QuakesAdapter.addAll(earthquakes);
        }
        ;
    }

    ;

    @Override
    public void onLoaderReset(Loader<ArrayList<DataContainerModel>> loader) {
        // Loader reset, so we can clear out our existing data.
        QuakesAdapter.clear();
    }

    ;
};
