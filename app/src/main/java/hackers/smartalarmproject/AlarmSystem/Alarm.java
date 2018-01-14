package hackers.smartalarmproject.AlarmSystem;

import java.sql.Time;

/**
 * Created by Will on 2018/1/13.
 */

public class Alarm {
    public static final String SPILT_ALARM_TAG = "!SPLITALARMTAG";
    private Time time;
    private boolean isOpen;
    private ReadingMaterial readingMaterial;

    public Alarm(Time time, ReadingMaterial readingMaterial) {
        this.time = time;
        isOpen = true;
        this.readingMaterial = readingMaterial;
    }

    public Alarm(String time, String isOpen, String readingMaterial) {
        Time tempT = new Time(Long.parseLong(time)); // TODO: might have bug?
        boolean tempO = Boolean.parseBoolean(isOpen);
        String[] tempS = readingMaterial.split(ReadingMaterial.SPLIT_TAG);
        ReadingMaterial tempR = new ReadingMaterial(tempS[0], tempS[1]);
        this.time = tempT;
        this.isOpen = tempO;
        this.readingMaterial = tempR;
    }

    public Time getTime() {
        return time;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public ReadingMaterial getReadingMaterial() {
        return readingMaterial;
    }

    public void switchOff() {
        isOpen = false;
    }

    public void switchOn() {
        isOpen = true;
    }

    @Override
    public String toString() {
        return time.toString() + SPILT_ALARM_TAG + isOpen + SPILT_ALARM_TAG + readingMaterial.toString();
    }

    public static Alarm formAlarm(String s) {
        String[] tempS = s.split(SPILT_ALARM_TAG);
        return new Alarm(tempS[0], tempS[1], tempS[2]);
    }
}
