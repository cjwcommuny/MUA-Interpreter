package mua.object.primitive;

import mua.object.MuaObject;

import java.util.List;
import java.util.ListIterator;

public class MuaList extends MuaObject {
    private List<MuaObject> list;

    public MuaList(List<MuaObject> list) {
        this.list = list;
    }

    public List<MuaObject> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        List<MuaObject> muaList = ((MuaList) obj).getList();
        ListIterator<MuaObject> iterator1 = list.listIterator();
        ListIterator<MuaObject> iterator2 = muaList.listIterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }
        boolean sizeNotEqual = iterator1.hasNext() || iterator2.hasNext();
        return !sizeNotEqual;
    }
}
