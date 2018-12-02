package mua.namespace;

import mua.object.MuaObject;

import java.util.Map;
import java.util.TreeMap;

class Namespace {
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

    void remove(String name) {
        nameObjectMap.remove(name);
    }
}
