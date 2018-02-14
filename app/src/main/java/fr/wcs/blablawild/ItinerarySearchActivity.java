package fr.wcs.blablawild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ItinerarySearchActivity extends AppCompatActivity {

    public static final String EXTRA_DEPARTURE = "fr.wcs.blablawild.DEPARTURE";
    public static final String EXTRA_DESTINATION = "fr.wcs.blablawild.DESTINATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_search);

        final EditText departure = findViewById(R.id.departureValue);
        final EditText destination = findViewById(R.id.destinationValue);

        Button searchItinerary = findViewById(R.id.searchItinerary);
        searchItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String departureContent = departure.getText().toString();
                String destinationContent = destination.getText().toString();
                if (departureContent.isEmpty() || destinationContent.isEmpty()) {
                    Toast.makeText(ItinerarySearchActivity.this, R.string.search_error, Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(ItinerarySearchActivity.this, ItineraryListActivity.class);
                    intent.putExtra(EXTRA_DEPARTURE, departureContent);
                    intent.putExtra(EXTRA_DESTINATION, destinationContent);
                    startActivity(intent);
                }
            }
        });
    }
}
