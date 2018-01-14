package hackers.smartalarmproject.DataHandler;

import java.util.ArrayList;
import java.util.List;

import hackers.smartalarmproject.AlarmSystem.ReadingMaterial;
import hackers.smartalarmproject.AlarmSystem.ReadingMaterialManager;

/**
 * Created by Will on 2018/1/13.
 */

public class ReadingMaterialData {
    private static List<String> materials = new ArrayList<>();
    public static final String TAG = "RMD";

    public static void loadReadingMaterial(List<ReadingMaterial> readingMaterials) {
        LocalFileAccesser.getInstance().readFile(TAG, materials);
        for (String s : materials) {
            String[] tempMaterial = s.split(ReadingMaterial.SPLIT_TAG);
            ReadingMaterial readingMaterial = new ReadingMaterial(tempMaterial[0], tempMaterial[1]);
            readingMaterials.add(readingMaterial);
        }
    }

    public static void storeRMData() {
        LocalFileAccesser.getInstance().writeFile(TAG, materials);
    }

    public static void addMaterial(String material) {
        materials.add(material);
        LocalFileAccesser.getInstance().writeFile(TAG, materials);
    }
}
