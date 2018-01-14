package hackers.smartalarmproject.DataHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 2018/1/13.
 */

public class ReadingMaterialData {
    private static List<String> materials = new ArrayList<>();
    public static final String TAG = "RMD";

    public static boolean LoadReadingMaterial() {
        LocalFileAccesser.readFile(TAG, materials);
        return false;
    }

    public static boolean addMaterial(String material) {
        materials.add(material);
        LocalFileAccesser.writeFile(TAG, materials);
        return false;
    }
}
