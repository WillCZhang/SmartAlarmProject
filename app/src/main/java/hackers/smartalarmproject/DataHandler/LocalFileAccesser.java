package hackers.smartalarmproject.DataHandler;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Will on 2018/1/13.
 */

public class LocalFileAccesser {
    private static Context context;
    private static LocalFileAccesser localFileAccesser;

    public static LocalFileAccesser getInstance(Context givenContext) {
        context = givenContext;
        if (localFileAccesser == null)
            localFileAccesser = new LocalFileAccesser();
        return localFileAccesser;
    }

    public boolean writeFile(String tag, List<String> data) {
        return false;
    }

    public boolean readFile(String tag, List<String> data) {
        String fileName;
        if (tag.equals(AlarmListData.TAG))
            fileName = "";
        else
            fileName = "";

        String content = fileContent(fileName);
        return false;
    }

    private String fileContent(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(name), "UTF-8");
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
}
