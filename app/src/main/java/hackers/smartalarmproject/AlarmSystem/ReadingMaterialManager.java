package hackers.smartalarmproject.AlarmSystem;

import java.util.ArrayList;
import java.util.List;

import hackers.smartalarmproject.DataHandler.ReadingMaterialData;

/**
 * Created by Will on 2018/1/13.
 */

class ReadingMaterialManager {
    private static ReadingMaterialManager ourInstance;
    private List<ReadingMaterial> readingMaterials;

    static ReadingMaterialManager getInstance() {
        if (ourInstance == null)
            ourInstance = new ReadingMaterialManager();
        return ourInstance;
    }

    private ReadingMaterialManager() {
        readingMaterials = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        boolean successed = ReadingMaterialData.LoadReadingMaterial();
        if (!successed) {
            // TODO
        }
    }
}
