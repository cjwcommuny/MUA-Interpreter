package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaSqrtNegativeException;
import mua.object.MuaObject;
import mua.object.primitive.MuaNumber;

public class MuaSqrtOperator extends MuaOperator {
    static final String FUNC_NAME = "sqrt";
    public MuaSqrtOperator() {
        super(FUNC_NAME, 1, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() != MuaNumber.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNC_NAME);
        }
        double number = ((MuaNumber) object).getValue();
        if (number < 0) {
            throw new MuaSqrtNegativeException();
        }
        return new MuaNumber(Math.sqrt(number));
    }
}
