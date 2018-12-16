package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaNumber;

public class MuaRandomOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "random";
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() != MuaNumber.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
        }
        double upperLimit = ((MuaNumber) object).getValue();
        return new MuaNumber(Math.random() * upperLimit);
    }

    public MuaRandomOperator() {
        super(FUNCTION_NAME, 1, true);
    }
}
