package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaNumber;

public class MuaFloorOperator extends MuaOperator implements ArithmeticOperator {
    private static final String FUNCTION_NAME = "int";
    public MuaFloorOperator() {
        super(FUNCTION_NAME, 1, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        double number = convertToDouble(object);
        return new MuaNumber(Math.floor(number));
    }
}
