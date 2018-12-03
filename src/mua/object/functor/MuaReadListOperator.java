package mua.object.functor;

import mua.Interpreter;
import mua.exception.MuaException;
import mua.object.MuaList;
import mua.object.MuaObject;

import java.util.Scanner;

public class MuaReadListOperator extends MuaFunctor {
    //todo: not fully supported
    public static final String FUNC_NAME = "readlist";
    private static final int ARGUMENT_NUM = -1; //TODO:not limited

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        //TODO: error handling
        return new MuaList(argumentList.getList());
    }

    public MuaReadListOperator() {
        super(FUNC_NAME, ARGUMENT_NUM, true);
    }
}
