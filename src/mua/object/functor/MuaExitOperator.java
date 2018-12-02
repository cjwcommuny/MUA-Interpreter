package mua.object.functor;

import mua.exception.MuaException;
import mua.object.MuaObject;

public class MuaExitOperator extends MuaFunctor {
    public MuaExitOperator() {
        super("exit", 0, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        //TODO
        return null;
    }
}
