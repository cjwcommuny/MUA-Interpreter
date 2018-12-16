package mua.object.operator;

import mua.exception.MuaException;
import mua.exception.MuaStopExecutionException;
import mua.object.MuaObject;

public class MuaStopOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaStopExecutionException {
        throw new MuaStopExecutionException();
    }

    public MuaStopOperator() {
        super("stop", 0, true);
    }
}
