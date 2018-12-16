package mua.object.operator;

import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaList;

import java.util.LinkedList;
import java.util.List;

public class MuaSentenceOperator extends MuaOperator {
    public MuaSentenceOperator() {
        super("sentence", 2, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object1 = argumentList.get(0);
        MuaObject object2 = argumentList.get(1);
        if (object1.getClass() == MuaList.class && object2.getClass() != MuaList.class) {
            return ((MuaList) object1).joinLast(object2);
        } else if (object1.getClass() != MuaList.class && object2.getClass() == MuaList.class) {
            return ((MuaList) object2).joinFirst(object1);
        } else if (object1.getClass() == MuaList.class && object2.getClass() == MuaList.class) {
            return ((MuaList) object1).merge((MuaList) object2);
        } else {
            List<MuaObject> list = new LinkedList<>();
            list.add(object1);
            list.add(object2);
            return new MuaList(list);
        }
    }
}
