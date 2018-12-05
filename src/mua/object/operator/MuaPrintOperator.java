package mua.object.operator;

import mua.InterpreterController;
import mua.exception.MuaException;
import mua.object.primitive.MuaNone;
import mua.object.MuaObject;

public class MuaPrintOperator extends MuaOperator {
    public static final String FUNC_NAME = "print";
    private static final int ARGUMENT_NUM = 1;
    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) {
        MuaObject message = argumentList.get(0);
        InterpreterController.printOnConsole(message);
        //TODO: should be added to result list instead of directly print on console
        return new MuaNone();
    }

    public MuaPrintOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }
}
