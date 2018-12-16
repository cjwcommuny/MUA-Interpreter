package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNone;

public class MuaJoinOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "join";
    public MuaJoinOperator() {
        super(FUNCTION_NAME, 2, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object1 = argumentList.get(0);
        MuaObject object2 = argumentList.get(1);
        if (object1.getClass() != MuaList.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
        }
        MuaList list = (MuaList) object1;
        list.add(object2);
        return new MuaNone();
    }
}
