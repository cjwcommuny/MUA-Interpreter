package mua.object;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class MuaBool extends MuaObject {
    private boolean value;

    public MuaBool(boolean value) {
        super(MuaType.bool);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
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
