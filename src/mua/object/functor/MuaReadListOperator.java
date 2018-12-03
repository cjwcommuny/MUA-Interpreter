package mua.object.functor;

import mua.Interpreter;
import mua.exception.MuaException;
import mua.object.primitive.MuaList;
import mua.object.MuaObject;

public class MuaReadListOperator extends MuaFunctor {
    //todo: not fully supported
    public static final String FUNC_NAME = "readlist";
    private static final int ARGUMENT_NUM = 0; //TODO:not limited

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        //TODO: error handling
        checkArgumentNum(argumentList);
        ArgumentList readArgumentList = Interpreter.readALineAsList();
        return new MuaList(readArgumentList.getList());
    }

    public MuaReadListOperator() {
        super(FUNC_NAME, ARGUMENT_NUM, true);
    }
}
