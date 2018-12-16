package mua.namespace;

import mua.object.MuaObject;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Namespace implements Serializable {
    private Map<String, MuaObject> nameObjectMap;

    Namespace() {
        //TODO
        this.nameObjectMap = new TreeMap<>();
    }

    MuaObject get(String name) {
        return nameObjectMap.get(name);
    }

    void put(String name, MuaObject object) {
        nameObjectMap.put(name, object);
    }

    MuaObject remove(String name) {
        return nameObjectMap.remove(name);
    }

    Map<String, MuaObject> getNameObjectMap() {
        return nameObjectMap;
    }
}
