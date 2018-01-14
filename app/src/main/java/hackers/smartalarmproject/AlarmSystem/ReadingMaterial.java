package hackers.smartalarmproject.AlarmSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 2018/1/13.
 */

public class ReadingMaterial {
    public static final String SPLIT_TAG = "!SPLITTAG";
    private String reading;
    private String tag;
    private List<Alarm> alarms;

    public ReadingMaterial(String reading, String tag) {
        this.reading = reading;
        this.tag = tag;
        alarms = new ArrayList<>();
    }

    public void addAlarm(Alarm a) {
        alarms.add(a);
    }

    public boolean pass(String test) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadingMaterial)) return false;

        ReadingMaterial that = (ReadingMaterial) o;

        if (reading != null ? !reading.equals(that.reading) : that.reading != null) return false;
        return tag != null ? tag.equals(that.tag) : that.tag == null;

    }

    @Override
    public int hashCode() {
        int result = reading != null ? reading.hashCode() : 0;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return reading + SPLIT_TAG + tag;
    }
}
