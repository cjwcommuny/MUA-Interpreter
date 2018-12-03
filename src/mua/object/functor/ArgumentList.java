package mua.object.functor;

import mua.object.MuaObject;

import java.util.ArrayList;
import java.util.List;

public class ArgumentList { //type alias
    private List<MuaObject> list;

    public List<MuaObject> getList() {
        return list;
    }

    public ArgumentList(List<MuaObject> list) {
        this.list = list;
    }

    public ArgumentList(int initialCapacity) {
        this.list = new ArrayList<>(initialCapacity);
    }

    public ArgumentList() {
        this.list = new ArrayList<>();
    }

    public MuaObject get(int i) {
        return list.get(i);
    }

    public MuaObject set(int i, MuaObject o) {
        return list.set(i, o);
    }

    public int size() {
        return list.size();
    }

    public boolean add(MuaObject o) {
        return list.add(o);
    }
}
