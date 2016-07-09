package costello.alex.tidal;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

/**
 * Created by Alex on 7/7/2016.
 */
public class TideFeedHandler extends DefaultHandler {

    private TideFeedItem tideFeedItem;
    private TideFeed tideFeed;

    private boolean isDate = false;
    private boolean isDay = false;
    private boolean isTime = false;
    private boolean isType = false;
    private boolean isHeightFeet = false;
    private boolean isHeightCenti = false;

    public TideFeed getTideFeed(){
        return tideFeed;
    }

    public void startDocument() throws SAXException {
        tideFeed = new TideFeed();
        tideFeedItem = new TideFeedItem();
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException{

        if (qName.equals("item")){
            tideFeedItem = new TideFeedItem();
            return;
        }
        else if(qName.equals("date")){
            isDate = true;
            return;
        }
        else if(qName.equals("day")){
            isDay = true;
            return;
        }
        else if(qName.equals("time")){
            isTime = true;
            return;
        }
        else if(qName.equals("highlow")){
            isType = true;
        }
        else if(qName.equals("predictions_in_ft")){
            isHeightFeet = true;
            return;
        }
        else if(qName.equals("predictions_in_cm")){
            isHeightCenti = true;
            return;
        }
    }

    public void endElement(String namespaceURI, String localName,
                           String qName) throws SAXException
    {
        if (qName.equals("item")) {
            tideFeed.getAllItems().add(tideFeedItem);
        }
        return;
    }

    public void characters(char ch[], int start, int length)
    {
        String s = new String(ch, start, length);
        if (isDate) {
            tideFeedItem.setDate(s);
            isDate = false;
        }
        else if (isDay){
            tideFeedItem.setDay(s);
            isDay = false;
        }
        else if (isTime) {
            tideFeedItem.setTime(s);
            isTime = false;
        }
        else if (isType) {
            tideFeedItem.setTideType(s);
            isType = false;
        }
        else if (isHeightFeet){
            tideFeedItem.setHeightFeet(s);
            isHeightFeet = false;

        }
        else if(isHeightCenti){
            tideFeedItem.setHeightCenti(s);
            isHeightCenti = false;
        }
    }
}
