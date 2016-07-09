package costello.alex.tidal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class TideFeedActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //UI Elements//
    private TextView titleTextView;
    private ListView tideListView;

    //Instances//
    private TideFeed tideFeed;

    //Context//
    private Context context;

    //file//
    private final String TIDEFILE = "coosbay_annual.xml";

    //TideFile//
    private InputStream tideFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //UI Elements//
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        tideListView = (ListView) findViewById(R.id.tideListView);

        //Listeners//
        tideListView.setOnItemClickListener(this);

        new ReadFeed().execute();
    }

    class ReadFeed extends AsyncTask<Void, Void, TideFeed>{

        @Override
        protected TideFeed doInBackground(Void... params){
            try {

                // get the XML reader
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                XMLReader xmlreader = parser.getXMLReader();

                //Content handler
                TideFeedHandler tideFeedHandler = new TideFeedHandler();
                xmlreader.setContentHandler(tideFeedHandler);

                //Read File//
                context = getApplicationContext();
                tideFile = context.getAssets().open(TIDEFILE);

                //Parse File
                InputSource inputSource = new InputSource(tideFile);
                xmlreader.parse(inputSource);

                //return feed
                TideFeed tideFeed = tideFeedHandler.getTideFeed();
                return tideFeed;

            }
            catch (Exception e) {
                Log.e("News reader", e.toString());
                return null;
            }

        }

        protected void onPostExecute(TideFeed result){
            TideFeedActivity.this.tideFeed = result;
            TideFeedActivity.this.updateDisplay();
        }


    }

    public void updateDisplay(){
        if (tideFeed == null) {
            titleTextView.setText("Unable to get tidal information");
            return;
        }

        ArrayList<TideFeedItem> tideFeedArray = tideFeed.getAllItems();

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        for(TideFeedItem item : tideFeedArray){
            HashMap<String, String> map = new HashMap<String, String>();
            String day = " "+item.getDay();
            String time = " "+item.getTime();
            map.put("date", item.getDate());
            map.put("day", day);
            map.put("time", time);
            map.put("tideType", item.getTideType());
            map.put("heightFeet", item.getHeightFeet());
            map.put("heightCenti", item.getHeightCenti());
            data.add(map);

        }

        int resource = R.layout.activity_tide_item;
        String[] from = {"date", "day", "time", "tideType"};
        int[] to = {R.id.dateTextView, R.id.dayTextView, R.id.timeTextView, R.id.typeTextView};

        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        tideListView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id){

        String heightFeet;
        String heightCenti;

        TideFeedItem item = tideFeed.getItem(position);

        heightFeet = item.getHeightFeet();
        heightCenti = item.getHeightCenti();

        heightToast(heightFeet, heightCenti);


        /*
        TideFeedItem item = tideFeed.getItem(position);

        Intent intent = new Intent(this, TideItemActivity.class);

        intent.putExtra("date", item.getDate());
        intent.putExtra("day", item.getDay());
        intent.putExtra("time", item.getTime());
        intent.putExtra("type", item.getTideType());
        intent.putExtra("heightFeet", item.getHeightFeet());
        intent.putExtra("heightCenti", item.getHeightCenti());

        this.startActivity(intent); //why this? seems to come up a lot
        */

    }

    public void heightToast(String _heightFeet, String _heightCenti){
        String heightMessage = _heightFeet +", "+ _heightCenti;
        Toast.makeText(this, heightMessage, Toast.LENGTH_LONG).show();
    }


}
