package mua.object.functor;

import mua.Interpreter;
import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaNone;
import mua.object.MuaObject;
import mua.object.MuaType;

public class MuaPrintOperator extends MuaFunctor {
    public static final String FUNC_NAME = "print";
    private static final int ARGUMENT_NUM = 1;
    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        checkArgumentNum(argumentList);
        MuaObject message = argumentList.get(0);
        //TODO
        //Interpreter.getOutpuStream().println(message);
        return new MuaNone();
    }

    public MuaPrintOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }
}
