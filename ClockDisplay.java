
/**
 * The ClockDisplay class implements a digital clock display for a traditional
 * 12- hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00AM (midnight) to 11:59PM (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * Modifications:
 * WWU: Added Logic to updateDisplay() to determine "AM" or "PM"
 * WWU: Added Logic to updateDisplay() to display time in 12-hour format
 * WWU: Added turnAlarmOn(), turnAlarmOff(), and alarm()
 * WWU: Added Logic to alarm() to only print alarm text if alarmOn == true
 * WWU: Added validation to setTime()
 * 
 * 
 * @author Michael Kölling and David J. Barnes and William Wheatley-Uhl
 * @version 2018.02.10
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private boolean alarmOn = false;
    private int alarmHour;
    private int alarmMinute;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     * 
     * @param hour Integer value for Hour
     * @param minute Integer value for Minute
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute. Check that they are valid time values.
     * 
     * @param hour Integer value for Hour
     * @param minute Integer value for Minute
     */
    public void setTime(int hour, int minute)
    {
        if(hour >= 0 && hour <= 23) {
            hours.setValue(hour);
        }
        
        if(minute >= 0 && minute <= 59) {
            minutes.setValue(minute);
        }
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     * 
     * @return displayString
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Determines Am or Pm, converts 24 hour values into 12 hour values, and maintains 4 digit display format.
     */
    private void updateDisplay()
    {
        int numericHour = hours.getValue();
        String displayAmOrPm;
        String displayTwoDigits;
        
        if(hours.getValue() < 12) {
            displayAmOrPm = "AM";
        }
        else {
            displayAmOrPm = "PM";
        }
        
        if(numericHour > 12) {
            numericHour -= 12;
        }
        
        if(numericHour == 0) {
            numericHour = 12;
        }
        
        if(numericHour < 10) {
            displayTwoDigits = "0";
        }
        else {
            displayTwoDigits = "";
        }
        
        displayString = displayTwoDigits + numericHour + ":" + minutes.getDisplayValue() + displayAmOrPm;
        alarm();
    }
    
    /**
     * Turns Alarm on and Sets Alarm to Given Time
     * 
     * @param hour Integer value for Alarm Hour (0 - 23)
     * @param minute Integer value for Alarm Minute (0 - 59)
     */
    public void turnAlarmOn(int hour, int minute)
    {
        alarmOn = true;
        alarmHour = hour;
        alarmMinute = minute;
    }
    
    /**
     * Turns Alarm Off
     */
    public void turnAlarmOff()
    {
        alarmOn = false;
    }
    
    /**
     * If the Set Alarm Time is reached and the Alarm is On, Print the Alarm
     */
    public void alarm(){
        if(alarmOn){
            if(hours.getValue() == alarmHour && minutes.getValue() == alarmMinute) {
                System.out.println("************************");
                System.out.println("  ALARM! ALARM! ALARM!");
                System.out.println("  The Time is: " + displayString);
                System.out.println("************************");
            }
        }
    }
}
