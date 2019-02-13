package mua.object.primitive;

import mua.object.MuaObject;

public class MuaBool extends MuaPrimitiveType {
    private boolean value;

    public MuaBool(boolean value) {
        this.value = value;
    }

    public MuaBool(MuaWord word) {
        this.value = Boolean.parseBoolean(word.getValue());
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        boolean anotherBool = ((MuaBool) obj).getValue();
        return value == anotherBool;
    }

    @Override
    public String toString() {
        return Boolean.toString(getValue());
    }
}
