package costello.alex.tidal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TideItemActivity extends AppCompatActivity{

    private TextView dateTextView;
    private TextView dayTextView;
    private TextView typeTextView;
    private TextView timeTextView;

    private String date;
    private String day;
    private String type;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tide_item);

        dateTextView = (TextView) findViewById(R.id.dateTextView);
        dayTextView = (TextView) findViewById(R.id.dayTextView);
        typeTextView = (TextView) findViewById(R.id.typeTextView);
        timeTextView = (TextView) findViewById(R.id.typeTextView);

        Intent intent = getIntent();

        date = intent.getStringExtra("date");
        day = intent.getStringExtra("day");
        type = intent.getStringExtra("type");
        time = intent.getStringExtra("time");

        dateTextView.setText(date);
        dayTextView.setText(day);
        typeTextView.setText(type);
        timeTextView.setText(time);

    }


}
