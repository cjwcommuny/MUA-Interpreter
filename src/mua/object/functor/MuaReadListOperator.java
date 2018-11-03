package mua.object.functor;

import mua.exception.MuaException;
import mua.object.MuaObject;

public class MuaReadListOperator extends MuaFunctor {
    //todo: not supported
    public static final String FUNC_NAME = "readlist";
    private static final int ARGUMENT_NUM = 0;

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        return null;
    }

    public MuaReadListOperator() {
        super(FUNC_NAME, ARGUMENT_NUM, true);
    }
}
