package fr.wcs.blablawild;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        final EditText editDeparture = findViewById(R.id.edit_departure);
        final EditText editDestination = findViewById(R.id.edit_destination);

        Button buttonSearchItinerary = findViewById(R.id.button_search_itinerary_action);
        buttonSearchItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String departure = editDeparture.getText().toString();
                String destination = editDestination.getText().toString();
                if (departure.isEmpty() || destination.isEmpty()) {
                    Toast.makeText(ItinerarySearchActivity.this, R.string.search_error, Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(ItinerarySearchActivity.this, ItineraryListActivity.class);
                    intent.putExtra(EXTRA_DEPARTURE, departure);
                    intent.putExtra(EXTRA_DESTINATION, destination);
                    startActivity(intent);
                }
                // hides keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        final Calendar calendar = Calendar.getInstance();
        final EditText editDate = findViewById(R.id.edit_date);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(editDate, calendar);
            }

        };

        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ItinerarySearchActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(EditText editDate, Calendar calendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(calendar.getTime()));
    }
}
