package hackers.smartalarmproject;

import android.app.AlarmManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hackers.smartalarmproject.AlarmSystem.Alarm;
import hackers.smartalarmproject.AlarmSystem.ReadingMaterial;
import hackers.smartalarmproject.AlarmSystem.ReadingMaterialManager;
import hackers.smartalarmproject.DataHandler.AlarmListData;
import hackers.smartalarmproject.DataHandler.LocalFileAccesser;
import hackers.smartalarmproject.UIAdapter.AlarmListAdapter;
import hackers.smartalarmproject.VoiceRecognition.VoiceHandler;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE_SPEECH_INPUT = 100;

    private Toolbar topBar;
    private FloatingActionButton addNewAlarm;
    private ListView alarmListView;
    private AlarmListAdapter alarmListAdapter;
    private List<Alarm> alarmList;
    private AlarmManager alarmManager;

    private LocalFileAccesser localFileAccesser;
    private ReadingMaterialManager readingMaterialManager;

    private Intent voiceRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniData();
        iniViews();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        ReadingMaterial temp = ReadingMaterialManager.getInstance().getReadingMaterialByIndex(0);
        voiceRec = VoiceHandler.getVoiceHandlerIntent(temp);
        startVoiceRecognition();
//        voiceRec = new Intent(this, ReadingView.class);
//        startActivity(voiceRec);
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

    private void startVoiceRecognition() {
        assert voiceRec != null;
        try {
            startActivityForResult(voiceRec, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "???",
                    Toast.LENGTH_SHORT).show();
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.i("","");
                }
                break;
            }

        }
    }
}
