package hackers.smartalarmproject.DataHandler;

import java.util.ArrayList;
import java.util.List;

import hackers.smartalarmproject.AlarmSystem.Alarm;

import static hackers.smartalarmproject.AlarmSystem.Alarm.formAlarm;

/**
 * Created by Will on 2018/1/13.
 */

public class AlarmListData {
    public static final String TAG = "ALD";
    private static List<String> tempList = new ArrayList<>();

    public static void loadAlarmList(List<Alarm> list) {
        LocalFileAccesser.getInstance().readFile(TAG, tempList);
        transform(list);
    }

    public static void storeAlarmList(List<Alarm> list) {
        LocalFileAccesser.getInstance().writeFile(TAG, tempList);
        transform(list);
    }

    private static void transform(List<Alarm> list) {
        if (list.size() == 0) {
            for (String s : tempList)
                list.add(formAlarm(s));
        } else {
            for (Alarm a : list)
                tempList.add(a.toString());
        }
    }
}
