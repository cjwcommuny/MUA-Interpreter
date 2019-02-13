package mua.object.primitive;

import mua.object.MuaObject;

public class MuaNonDeterministic extends MuaObject {
    private String name;

    public MuaNonDeterministic(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
