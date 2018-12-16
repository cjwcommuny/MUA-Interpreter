package mua.object.primitive;

import mua.object.MuaObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MuaList extends MuaPrimitiveType {
    private List<MuaObject> list;

    public MuaList(List<MuaObject> list) {
        this.list = list;
    }

    public List<MuaObject> getList() {
        return list;
    }

    public MuaObject get(int i) {
        return list.get(i);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public int size() {
        return list.size();
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

    public Iterator<MuaObject> listIterator() {
        return list.listIterator();
    }

    public MuaList merge(MuaList anotherList) {
        List<MuaObject> newList = new LinkedList<>(list);
        newList.addAll(anotherList.getList());
        return new MuaList(newList);
    }

    public MuaList joinLast(MuaObject object) {
        List<MuaObject> newList = new LinkedList<>(list);
        newList.add(object);
        return new MuaList(newList);
    }

    public void add(MuaObject object) {
        list.add(object);
    }

    public MuaList joinFirst(MuaObject object) {
        LinkedList<MuaObject> newList = new LinkedList<>(list);
        newList.addFirst(object);
        return new MuaList(newList);
    }

    public MuaObject getFirstElement() {
        return list.get(0);
    }

    public MuaObject getLastElement() {
        return list.get(list.size() - 1);
    }

    public MuaList butFirst() {
        LinkedList<MuaObject> newList = new LinkedList<>(list);
        newList.removeFirst();
        return new MuaList(newList);
    }

    public MuaList butLast() {
        LinkedList<MuaObject> newList = new LinkedList<>(list);
        newList.removeLast();
        return new MuaList(newList);
    }
}
