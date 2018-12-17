package mua.object.primitive;

import mua.exception.MuaEmptyListOrWordException;
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

    public MuaWord getFirstElement() throws MuaEmptyListOrWordException {
        if (value.length() == 0) {
            throw new MuaEmptyListOrWordException();
        }
        return new MuaWord(Character.toString(value.charAt(0)));
    }

    public MuaWord getLastElement() throws MuaEmptyListOrWordException {
        if (value.length() == 0) {
            throw new MuaEmptyListOrWordException();
        }
        return new MuaWord(Character.toString(value.charAt(value.length() -1)));
    }

    public MuaWord butFirst() throws MuaEmptyListOrWordException {
        if (value.length() == 0) {
            throw new MuaEmptyListOrWordException();
        }
        return new MuaWord(value.substring(1));
    }

    public MuaWord butLast() throws MuaEmptyListOrWordException {
        if (value.length() == 0) {
            throw new MuaEmptyListOrWordException();
        }
        return new MuaWord(value.substring(0, value.length() - 1));
    }
}
