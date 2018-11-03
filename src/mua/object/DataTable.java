package mua.object;

import java.util.Map;
import java.util.TreeMap;

public class DataTable {
    private Map<String, MuaObject> dataTable = new TreeMap<>();

    public MuaObject getObject(String name) {
        return dataTable.get(name);
    }

    public void updateObject(String name, MuaObject value) {
        dataTable.put(name, value);
    }

    public MuaObject remove(String name) {
        return dataTable.remove(name);
    }
}
