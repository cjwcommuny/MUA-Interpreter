package mua.object;

import mua.exception.MuaArgumentTypeNotCompatibleException;

public abstract class MuaObject {
    private MuaType muaType;

    public MuaObject(MuaType muaType) {
        this.muaType = muaType;
    }

    public MuaType getMuaType() {
        return muaType;
    }

    public void setMuaType(MuaType muaType) {
        this.muaType = muaType;
    }

    public boolean lessThan(MuaObject obj) throws MuaArgumentTypeNotCompatibleException {
        throw new MuaArgumentTypeNotCompatibleException();
    }
}
