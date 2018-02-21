
/**
 * A Class to Test the Changes to Clock Display
 *
 * @author William Wheatley-Uhl
 * @version 2018.02.18
 */
public class Test
{
    private ClockDisplay clock;
    
    public void testClock()
    {
        clock = new ClockDisplay(23,40);
        clock.turnAlarmOn(23, 45);
        for(int x = 0; x < 5; x++) {
            clock.timeTick();
            System.out.println(clock.getTime());
        }
        clock.alarmSnooze();
        System.out.println("I Snoozed the Alarm");
        
        for(int x = 0; x < 15; x++) {
            clock.timeTick();
            System.out.println(clock.getTime());
        }
        
        clock.alarmSnooze();
        System.out.println("I Snoozed the Alarm");
        
        for(int x = 0; x < 15; x++) {
            clock.timeTick();
            System.out.println(clock.getTime());
        }
    }
}
