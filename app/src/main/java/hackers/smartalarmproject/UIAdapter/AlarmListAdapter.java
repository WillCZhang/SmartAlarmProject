package hackers.smartalarmproject.UIAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextClock;

import java.util.List;

import hackers.smartalarmproject.AlarmSystem.Alarm;
import hackers.smartalarmproject.R;

/**
 * Created by Will on 2018/1/13.
 */

public class AlarmListAdapter extends ArrayAdapter<Alarm> {
    private Context context;
    private List<Alarm> alarms;

    private static class ViewHolder {
        TextClock alarmTime;
        Switch alarmSwitch;
    }

    public AlarmListAdapter(@NonNull Context context, List<Alarm> alarmList) {
        super(context, -1, alarmList);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        // change
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.alarm_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.alarmSwitch = (Switch) convertView.findViewById(R.id.alarmSwitch);
            viewHolder.alarmTime = (TextClock) convertView.findViewById(R.id.alarmTime);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        setData(viewHolder, position);
        return convertView;
    }

    private void setData(ViewHolder viewHolder, int position) {

    }
}
