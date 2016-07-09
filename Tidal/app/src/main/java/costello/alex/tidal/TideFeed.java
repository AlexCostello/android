package costello.alex.tidal;

import java.util.ArrayList;

/**
 * Created by Alex on 7/5/2016.
 */
public class TideFeed {
    private String date = null;
    private String day = null;
    private String time = null;
    private String tideType = null;
    private ArrayList<TideFeedItem> tideFeedItems;

    public TideFeed(){
        tideFeedItems = new ArrayList<TideFeedItem>();
    }

    //Getters and Setters//
    public void setDate(String _date){
        this.date = _date;
    }

    public String getDate(){
        return date;
    }

    public void setDay(String _day){
        this.day = _day;
    }

    public String getDay(){
        return day;
    }

    public void setTime(String _time){
        this.time = _time;
    }

    public String getTime(){
        return time;
    }

    public void setTideType(String _tideType){
        this.tideType = _tideType;
    }

    public String getTideType(){
        return tideType;
    }

    public int addItem(TideFeedItem tideFeedItem){
        tideFeedItems.add(tideFeedItem);
        return tideFeedItems.size();
    }

    public TideFeedItem getItem(int index){
        return tideFeedItems.get(index);
    }

    public ArrayList<TideFeedItem> getAllItems(){
        return tideFeedItems;
    }

}
