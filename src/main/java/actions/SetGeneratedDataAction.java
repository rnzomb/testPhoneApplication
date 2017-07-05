package actions;

import models.Call;
import models.Event;
import services.CallService;
import services.EventService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SetGeneratedDataAction {
    private CallService callService;
    private EventService eventService;

    private static final Random RANDOM = new Random ();
    private static final String[] EVENTS = {
            "EVENT_PICK_UP",
            "EVENT_DIAL",
            "EVENT_CALL_ESTABLISHED",
            "EVENT_CALL_END",
            "EVENT_HANG_UP"};


    public String execute() throws Exception {

        Call call = new Call ();
        Event event = new Event ();

        int callsRandom = 100 + RANDOM.nextInt (201);                  // random number of  calls      100-300

        int[] randomCallers = new int[30 + RANDOM.nextInt (21)];       // random number of callers     30-50

        for (int i = 0; i < randomCallers.length; i++) {
            randomCallers[i] = (getRandomNumber ());                         // generate callers random phone numbers
        }
        long startTime = 1488394800644L;                                     // start time in milliseconds from 1.01.1970

        /* Event options - set of event types:
        Full          -  1,2,3,4,5       - 80%
        Cancelled     -  1,2,4,5         - 15%
        Non-Dialed    -  1,5             - 5%
        */

        int full = callsRandom * 80 / 100;
        int cancelled = callsRandom * 15 / 100;
        int nonDial = callsRandom * 5 / 100;

        int percent;
        int callId = 1;

        while ((full + cancelled + nonDial) > 0) {
            percent = RANDOM.nextInt (100);

            // Insert full calls    80%      events 1,2,3,4,5
            if (full > 0 & (percent >= 0 & percent < 80)) {

                event.setCallId (callId++);
                int caller = randomCallers[RANDOM.nextInt (randomCallers.length)];
                int receiver = getRandomNumber ();
                call.setCaller (caller);
                call.setReceiver (0);

                event.setRecordEventId (EVENTS[0]);
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //1x
                callService.insertCall (call);

                call.setReceiver (receiver);
                event.setRecordEventId (EVENTS[1]);
                startTime += (1000 + RANDOM.nextInt (20000));                          // from 1 sec  till 20 sec
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //2x
                callService.insertCall (call);

                event.setRecordEventId (EVENTS[2]);
                startTime += 1000 + RANDOM.nextInt (60000);                          // from 1 sec  till 1 minute
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //3x
                callService.insertCall (call);

                event.setRecordEventId (EVENTS[3]);
                startTime += 1000 + RANDOM.nextInt (3600000);                         // from 1 sec  till 1 hour
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //4x
                callService.insertCall (call);

                event.setRecordEventId (EVENTS[4]);
                startTime += 1000 + RANDOM.nextInt (20000);                          // from 1 sec  till 20 sec
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //5x
                callService.insertCall (call);

                startTime += 60000 + RANDOM.nextInt (36000000);                      // from 1 minute  till 10 hours
                full--;
            }

            // Insert cancelled calls   15%    events 1,2,4,5
            if (cancelled > 0 & (percent >= 80 & percent < 95)) {

                event.setCallId (callId++);
                int caller = randomCallers[RANDOM.nextInt (randomCallers.length)];
                int receiver = getRandomNumber ();
                call.setCaller (caller);
                call.setReceiver (0);

                event.setRecordEventId (EVENTS[0]);
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //1x
                callService.insertCall (call);

                call.setReceiver (receiver);
                event.setRecordEventId (EVENTS[1]);
                startTime += (1000 + RANDOM.nextInt (20000));                          // from 1 sec  till 20 sec
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //2x
                callService.insertCall (call);

                event.setRecordEventId (EVENTS[3]);
                startTime += 1000 + RANDOM.nextInt (60000);                          // from 1 sec  till 1 minute
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //3x
                callService.insertCall (call);

                event.setRecordEventId (EVENTS[4]);
                startTime += 1000 + RANDOM.nextInt (20000);                          // from 1 sec  till 20 sec
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);     //5x
                callService.insertCall (call);

                startTime += 60000 + RANDOM.nextInt (36000000);                      // from 1 minute  till 10 hours
                cancelled--;
            }

            // Insert non-dialed calls   5%   events 1,5
            if (nonDial > 0 & (percent >= 95 & percent <= 100)) {

                event.setCallId (callId++);
                int caller = randomCallers[RANDOM.nextInt (randomCallers.length)];
                call.setCaller (caller);
                call.setReceiver (0);

                event.setRecordEventId (EVENTS[0]);
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);      //1
                callService.insertCall (call);
                event.setRecordEventId (EVENTS[4]);
                startTime += (1000 + RANDOM.nextInt (20000));                          // from 1 sec  till 20 sec
                event.setRecordDateSqlFormat (secondsToDate (startTime));
                eventService.insertEvent (event);      //2x
                callService.insertCall (call);

                startTime += 60000 + RANDOM.nextInt (36000000);                      // from 1 minute  till 10 hours
                nonDial--;
            }
        }
        return "SUCCESS";
    }

    private int getRandomNumber() {
        int baseNumbers[] = {300000, 500000, 800000};
        return baseNumbers[RANDOM.nextInt (3)] + RANDOM.nextInt (100000);
    }

    private String secondsToDate(Long seconds) {
        Date date = new Date (seconds);
        SimpleDateFormat myFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return myFormat.format (date);
    }

    public void setCallService(CallService callService) {
        this.callService = callService;
    }


    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

}
