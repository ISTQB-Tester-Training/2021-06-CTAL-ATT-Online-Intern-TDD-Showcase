package tddshowcasepackage;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class TimePair {

    private String startTime = null;
    private String endTime = null;

    public void setTimeValues (String strStartTime, String strEndTime) {
        startTime = strStartTime;
        endTime = strEndTime;
    }

    public double getTimeDifference(String strStartTime, String strEndTime) {
        setTimeValues(strStartTime, strEndTime);
        return getTimeDifference();
    }

    public double getTimeDifference() {

        try { LocalTime checkStartTime = LocalTime.parse(startTime);
        }
        catch (Exception e) {
            throw new TimePairException(e.getMessage() + "(Input value: " + startTime + ")", 501);
        }

        try { LocalTime checkEndTime = LocalTime.parse(endTime);
        }
        catch (Exception e) {
            throw new TimePairException(e.getMessage() + "(Input value: " + endTime + ")", 502);
        }

        double aTimeDifference = LocalTime.parse(startTime).until(LocalTime.parse(endTime), ChronoUnit.MINUTES)/60.0;
        if (aTimeDifference < 0.0) {
            throw new TimePairException("Invalid time period " +
                    startTime + " / " +
                    endTime +
                    " time difference " + aTimeDifference + " hours", 503);
        }
        return aTimeDifference;
    }

    public class TimePairException extends RuntimeException {
        private final String message;
        private final Long messageNr;

        public TimePairException (String argMessage, long argMessageNr) {
            super(argMessage);
            message = argMessage;
            messageNr = argMessageNr;
        }
        public String getMessageText() {
            return message;
        }
        public Long getMessageNr() {
            return messageNr;
        }
    }
}