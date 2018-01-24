package hackers.smartalarmproject.AlarmSystem;

import java.util.ArrayList;
import java.util.List;

import hackers.smartalarmproject.DataHandler.ReadingMaterialData;

/**
 * Created by Will on 2018/1/13.
 */

public class ReadingMaterialManager {
    private static ReadingMaterialManager ourInstance;
    private List<ReadingMaterial> readingMaterials;

    public static ReadingMaterialManager getInstance() {
        if (ourInstance == null)
            ourInstance = new ReadingMaterialManager();
        return ourInstance;
    }

    public boolean addMaterial(String material, String tag) {
        ReadingMaterial readingMaterial = new ReadingMaterial(material, tag);
        if (readingMaterials.contains(readingMaterial))
            return false;
        readingMaterials.add(readingMaterial);
        ReadingMaterialData.addMaterial(readingMaterial);
        return true;
    }

    public ReadingMaterial getReadingMaterialByIndex(int index) {
        return readingMaterials.get(index);
    }

    private ReadingMaterialManager() {
        readingMaterials = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        ReadingMaterialData.loadReadingMaterial(readingMaterials);
    }
}
