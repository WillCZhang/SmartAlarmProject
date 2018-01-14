package hackers.smartalarmproject.AlarmSystem;

import java.util.ArrayList;
import java.util.List;

import hackers.smartalarmproject.Dependency.diff_match_patch;

/**
 * Created by Will on 2018/1/13.
 */

public class ReadingMaterial {
    public static final String SPLIT_TAG = "!SPLITTAG";
    public static final double PASS_RATE = 0.6;
    private String reading;
    private String modifiedReading;
    private String tag;
    private List<Alarm> alarms;

    public ReadingMaterial(String reading, String tag) {
        this.reading = reading;
        modifiedReading = reading.replaceAll("\\W", "");
        this.tag = tag;
        alarms = new ArrayList<>();
    }

    public void addAlarm(Alarm a) {
        alarms.add(a);
    }

    public boolean pass(String test) {
        String modifiedTest = test.replaceAll("\\W", "");

        diff_match_patch diffMatchPatch = new diff_match_patch();
        List<diff_match_patch.Diff> result = diffMatchPatch.diff_main(modifiedTest, modifiedReading);

        double totalDiff = 0;
        for (diff_match_patch.Diff diff : result)
            if (!diff.operation.equals(diff_match_patch.Operation.EQUAL))
                totalDiff += diff.text.length();

        double failRate = totalDiff / (double) modifiedReading.length();
        return failRate < (1 - PASS_RATE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadingMaterial)) return false;

        ReadingMaterial that = (ReadingMaterial) o;

        return modifiedReading != null ? modifiedReading.equals(that.modifiedReading) : that.modifiedReading == null && (tag != null ? tag.equals(that.tag) : that.tag == null);
    }

    @Override
    public int hashCode() {
        int result = modifiedReading != null ? modifiedReading.hashCode() : 0;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return reading + SPLIT_TAG + tag;
    }
}
