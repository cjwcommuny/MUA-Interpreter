package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaWord;

public class MuaFirstOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "first";
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() == MuaList.class) {
            return ((MuaList) object).getFirstElement();
        } else if (object.getClass() == MuaWord.class) {
            return ((MuaWord) object).getFirstElement();
        }
        throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
    }

    public MuaFirstOperator() {
        super(FUNCTION_NAME, 1, true);
    }
}
