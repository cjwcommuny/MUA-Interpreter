package mua.object.operator;

import mua.InterpreterController;
import mua.exception.MuaException;
import mua.object.primitive.MuaList;
import mua.object.MuaObject;

public class MuaReadListOperator extends MuaOperator {
    //todo: not fully supported
    public static final String FUNC_NAME = "readlist";
    private static final int ARGUMENT_NUM = 0; //TODO:not limited

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        ArgumentList readArgumentList = InterpreterController.readALineAsList();
        return new MuaList(readArgumentList.getList());
    }

    public MuaReadListOperator() {
        super(FUNC_NAME, ARGUMENT_NUM, true);
    }
}
