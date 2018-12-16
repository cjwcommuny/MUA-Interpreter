package mua.object.operator;

import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaList;

import java.util.LinkedList;
import java.util.List;

public class MuaListOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object1 = argumentList.get(0);
        MuaObject object2 = argumentList.get(1);
        List<MuaObject> list = new LinkedList<>();
        list.add(object1);
        list.add(object2);
        return new MuaList(list);
    }

    public MuaListOperator() {
        super("list", 2, true);
    }
}
