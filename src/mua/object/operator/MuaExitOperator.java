package mua.object.operator;

import mua.exception.MuaException;
import mua.exception.QuitInterpreterException;
import mua.object.MuaObject;

public class MuaExitOperator extends MuaOperator {
    public MuaExitOperator() {
        super("exit", 0, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws QuitInterpreterException {
        throw new QuitInterpreterException();
    }
}
