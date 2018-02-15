package fr.wcs.blablawild;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ItineraryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_list);

        Intent intent = getIntent();
        String departure = intent.getStringExtra(ItinerarySearchActivity.EXTRA_DEPARTURE);
        String destination = intent.getStringExtra(ItinerarySearchActivity.EXTRA_DESTINATION);
        String title = String.format(getString(R.string.title_list), departure, destination);
        setTitle(title);
    }
}
