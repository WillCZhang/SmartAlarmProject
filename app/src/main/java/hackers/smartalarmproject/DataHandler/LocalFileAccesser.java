package hackers.smartalarmproject.DataHandler;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Will on 2018/1/13.
 */

public class LocalFileAccesser {
    public static final String ALD = "alarmlistdata";
    public static final String RMD = "readingmaterialdata";
    public static final String LIST_SPLIT = "!LISTSPILT";

    private static Context context;
    private static LocalFileAccesser localFileAccesser;

    public static LocalFileAccesser getInstance(Context givenContext) {
        context = givenContext;
        if (localFileAccesser == null)
            localFileAccesser = new LocalFileAccesser();
        return localFileAccesser;
    }

    public static LocalFileAccesser getInstance() {
        if (localFileAccesser == null)
            localFileAccesser = new LocalFileAccesser();
        return localFileAccesser;
    }

    public void writeFile(String tag, List<String> data) {
        String fileName;
        if (tag.equals(AlarmListData.TAG))
            fileName = ALD;
        else
            fileName = RMD;

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : data) {
            String temp = s + LIST_SPLIT;
            stringBuilder.append(temp);
        }

        writeFile(fileName, stringBuilder.toString());
    }

    public void readFile(String tag, List<String> data) {
        String fileName;
        if (tag.equals(AlarmListData.TAG))
            fileName = ALD;
        else
            fileName = RMD;

        String s = fileContent(fileName);
        String[] temp = s.split(LIST_SPLIT);
        for (int i = 0; i < temp.length; i++)
            data.add(temp[i]);
    }

    private String fileContent(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.openFileInput(name), "UTF-8");
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = br.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = br.readLine();
            }
            br.close();
            inputStreamReader.close();
        } catch (IOException e) {

        }
        return stringBuilder.toString();
    }

    private void writeFile(String name, String data) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(name, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Log.i("ERROR", "FileNotFoundException when refreshEachSection");
        } catch (IOException e) {
            Log.i("ERROR", "IOException when refreshEachSection");
        }
    }
}
