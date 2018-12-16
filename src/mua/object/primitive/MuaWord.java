package mua.object.primitive;

import mua.object.MuaObject;

public class MuaWord extends MuaPrimitiveType implements Comparable<MuaWord>{
    private String value;

    public MuaWord(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MuaWord word = (MuaWord) obj;
        return compareTo(word) == 0;
    }

    @Override
    public int compareTo(MuaWord o) {
        return getValue().compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return "\"" + getValue();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }
}
