package fr.wcs.blablawild;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

        final Calendar myCalendar = Calendar.getInstance();
        final EditText dateValue = findViewById(R.id.dateValue);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(dateValue, myCalendar);
            }

        };

        dateValue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ItinerarySearchActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(EditText dateValue, Calendar myCalendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateValue.setText(sdf.format(myCalendar.getTime()));
    }
}
