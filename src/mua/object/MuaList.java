package mua.object;

import java.util.List;

public class MuaList extends MuaObject {
    private List<MuaObject> list;

    public MuaList(List<MuaObject> list) {
        super(MuaType.list);
        this.list = list;
    }
}
