# SmartAlarmProject

This is an Android alarm system, which rings can be turned off only when the user reads a reading material 
and achieves a certain accuracy.

The application is still under developing. The backend system is functional, but we need working on UI design.

The link to the core Java implementation is:
https://github.com/WillCZhang/SmartAlarmProject/tree/master/app/src/main/java/hackers/smartalarmproject

The original reading material can be found in this link
https://github.com/WillCZhang/SmartAlarmProject/blob/master/app/src/main/assets/readingmaterialdata. 

Adding reading materials is as the following:

1. Initialize a new `List<ReadingMaterial>` object, and name it `readingMaterials`
2. Creating a `ReadingMaterial` object requires both the actural reading material as in type `String` 
and a label as in type `String`, then you can instantiate a `ReadingMaterial` object with `new ReadingMaterial(reading_material, label)`.
3. Since our builtin data uses a list storing reading material data, 
we use the tag `!LISTSPILT` indicating how should it parse a string to a list.
Using this code to store the material list in a singal string.

```
StringBuilder sb = new StringBuilder();
for (ReadingMaterial rm : readingMaterials) {
    String tempS = rm.toString() + "!LISTSPILT";
    sb.append(tempS);
}
String result = sb.toString();
```
4. Write string to local file can use this code, and store 
```
final String PROJ_LOCATION = "";
try {
    FileWriter fw = new FileWriter(PROJ_LOCATION + "/app/src/main/assets/readingmaterialdata");
    fw.write(sb.toString());
    fw.close();
} catch (IOException e) {
    e.printStackTrace();
}
```

