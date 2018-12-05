package mua.object.operator;

import mua.InterpreterController;
import mua.exception.MuaException;
import mua.object.*;

public class MuaReadOperator extends MuaOperator {
    public static final String FUNC_NAME = "read";
    private static final int ARGUMENT_NUM = 0;

    public MuaReadOperator() {
        super(FUNC_NAME, ARGUMENT_NUM, true);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        //TODO: error handling
        checkArgumentNum(argumentList);
        return InterpreterController.readToken();
    }
}
