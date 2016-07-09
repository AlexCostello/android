package costello.alex.tidal;

import android.content.Context;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Alex on 7/8/2016.
 */
public class FileIO {

    private final String TIDEFILE = "coosbay_annual.xml";
    private Context context = null;

    public FileIO(Context context){this.context = context;}

    public TideFeed readFile(){
        try {
            // get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            TideFeedHandler tideFeedHandler = new TideFeedHandler();
            xmlreader.setContentHandler(tideFeedHandler);

            InputStream inputStream = context.getAssets().open(TIDEFILE);

            InputSource inputSource = new InputSource(inputStream);

            TideFeed tideFeed = tideFeedHandler.getTideFeed();
            return tideFeed;
        }
        catch(Exception exception){
            Log.e("Tide Feed", exception.toString());
            return null;

        }


    }

}

