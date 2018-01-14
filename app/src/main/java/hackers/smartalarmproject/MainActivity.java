package hackers.smartalarmproject;

import android.app.AlarmManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hackers.smartalarmproject.AlarmSystem.Alarm;
import hackers.smartalarmproject.AlarmSystem.ReadingMaterial;
import hackers.smartalarmproject.AlarmSystem.ReadingMaterialManager;
import hackers.smartalarmproject.DataHandler.AlarmListData;
import hackers.smartalarmproject.DataHandler.LocalFileAccesser;
import hackers.smartalarmproject.UIAdapter.AlarmListAdapter;

public class MainActivity extends AppCompatActivity {
    private Toolbar topBar;
    private FloatingActionButton addNewAlarm;
    private ListView alarmListView;
    private AlarmListAdapter alarmListAdapter;
    private List<Alarm> alarmList;
    private AlarmManager alarmManager;

    private LocalFileAccesser localFileAccesser;
    private ReadingMaterialManager readingMaterialManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniData();
        iniViews();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    private void iniData() {
        localFileAccesser = LocalFileAccesser.getInstance(this);

        readingMaterialManager = ReadingMaterialManager.getInstance();

        alarmList = new ArrayList<>();
        AlarmListData.loadAlarmList(alarmList);
        alarmListAdapter = new AlarmListAdapter(this, alarmList);
    }

    private void iniViews() {
        topBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topBar);

        addNewAlarm = (FloatingActionButton) findViewById(R.id.fab);
        addNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        alarmListView = (ListView) findViewById(R.id.alarmList);
        alarmListView.setAdapter(alarmListAdapter);
    }

    public void addAlart(Alarm alarm) {
        alarmList.add(alarm);
        alarmListAdapter.notifyDataSetChanged();
        AlarmListData.storeAlarmList(alarmList);
    }

    public void addReadingMaterial(String readingMaterial, String tag) {
        readingMaterialManager.addMaterial(readingMaterial, tag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
