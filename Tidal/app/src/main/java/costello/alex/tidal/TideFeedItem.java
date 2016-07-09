package costello.alex.tidal;

/**
 * Created by Alex on 7/5/2016.
 */
public class TideFeedItem {

    //Instance Variables//
    private String date;
    private String day;
    private String time;
    private String tideType;
    private String heightFeet;
    private String heightCenti;
    private String monthInt;
    private String monthName;

    //Getters and Setters//
    public void setDate(String _date){
        setMonthInt(parseMonth(_date));
        this.date = _date;
    }

    public String getDate(){
        return date;
    }

    public void setDay(String _day){
        this.day = formatDay(_day);
    }

    public String getDay(){
        return day;
    }

    public void setTime(String _time){
        this.time = formatTime(_time);
    }

    public String getTime(){
        return time;
    }

    public void setTideType(String _tideType){
        this.tideType = formatType(_tideType);
    }

    public String getTideType(){
        return tideType;
    }

    public void setHeightFeet(String _heightFeet){
        this.heightFeet = formatHeightFeet(_heightFeet);
    }

    public String getHeightFeet(){
        return heightFeet;
    }

    public void setHeightCenti(String _heightCenti){
        this.heightCenti = formatHeightCenti(_heightCenti);
    }

    public String getHeightCenti(){
        return heightCenti;
    }

    public void setMonthInt(String _month){
        this.monthInt = _month;
        setMonthName(_month);
    }

    public String getMonthInt(){
        return monthInt;
    }

    public void setMonthName(String _month){
        this.monthName = convertMonth(_month);
    }

    public String getMonthName(){
        return monthName;
    }


    public String formatDay(String _day){
        switch(_day){
            case "Mon":
                _day = "Monday";
                return _day;
            case "Tue":
                _day = "Tuesday";
                return _day;
            case "Wed":
                _day = "Wednesday";
                return _day;
            case "Thu":
                _day = "Thursday";
                return _day;
            case "Fri":
                _day = "Friday";
                return _day;
            case "Sat":
                _day = "Saturday";
                return _day;
            case "Sun":
                _day = "Sunday";
                return _day;
            default:
                return _day;
        }

    }

    public String formatType(String _type){
        switch(_type){
            case "H":
                _type = "High";
                return _type;
            case "L":
                _type = "Low";
                return _type;
            default:
                return _type;

        }
    }

    public String formatTime(String _time){

        char[] timeArray = _time.toCharArray();
        char firstChar = timeArray[0];
        if(firstChar == '0'){
            _time = "";
            for(int i = 1; i < timeArray.length; i++){
                _time+=timeArray[i];
            }
            return _time;
        }
        return _time;
    }

    public String parseMonth(String _date){

        char[] dateArray = _date.toCharArray();
        int l = dateArray.length;
        if(l < 6){
            return "Month parsing error";
        }
        String _month = "";
        String digitOne = Character.toString(dateArray[5]);
        String digitTwo = Character.toString(dateArray[6]);
        _month = digitOne + digitTwo;

        return _month;
    }

    public String convertMonth(String _month){
        String month = "Month Conversion Error";
        switch(_month){
            case "01":
                month = "January";
                return month;
            case "02":
                month = "February";
                return month;
            case "03":
                month = "March";
                return month;
            case "04":
                month = "April";
                return month;
            case "05":
                month = "May";
                return month;
            case "06":
                month = "June";
                return month;
            case "07":
                month = "July";
                return month;
            case "08":
                month = "August";
                return month;
            case "09":
                month = "September";
                return month;
            case "10":
                month = "October";
                return month;
            case "11":
                month = "November";
                return month;
            case "12":
                month = "December";
                return month;
        }
        return month;
    }

    public String formatHeightFeet(String _heightFeet){
        String hFeet = _heightFeet + " ft.";
        return hFeet;
    }

    public String formatHeightCenti(String _heightCenti){
        String hCenti = _heightCenti + " cm";
        return hCenti;
    }


}
