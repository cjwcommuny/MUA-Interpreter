package mua.object;

import mua.exception.MuaArgumentTypeNotCompatibleException;

public class MuaWord extends MuaObject implements Comparable<MuaWord>{
    private String value;

    public MuaWord(String value) {
        super(MuaType.word);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(MuaWord o) {
        return getValue().compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return "\"" + getValue();
    }
}
