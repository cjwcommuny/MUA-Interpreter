package mua.object;

import mua.exception.MuaArgumentTypeNotCompatibleException;

public class MuaWord extends MuaObject {
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
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        String anotherStr = ((MuaWord) obj).getValue();
        return value.equals(anotherStr);
    }

    @Override
    public boolean lessThan(MuaObject obj) throws MuaArgumentTypeNotCompatibleException{
        if (getClass() != obj.getClass()) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        return value.compareTo(((MuaWord) obj).getValue()) < 0;
    }

}
