package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaWord;

public class MuaButFirstOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "butfirst";
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() == MuaList.class) {
            return ((MuaList) object).butFirst();
        } else if (object.getClass() == MuaWord.class) {
            return ((MuaWord) object).butFirst();
        }
        throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
    }

    public MuaButFirstOperator() {
        super(FUNCTION_NAME, 1, true);
    }
}
