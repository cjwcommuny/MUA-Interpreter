package mua.object.functor;

import mua.Interpreter;
import mua.exception.MuaException;
import mua.object.MuaList;
import mua.object.MuaObject;

import java.util.Scanner;

public class MuaReadListOperator extends MuaFunctor {
    //todo: not fully supported
    public static final String FUNC_NAME = "readlist";
    private static final int ARGUMENT_NUM = 0;

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        checkArgumentNum(argumentList);
        //TODO
//        Scanner s = new Scanner(Interpreter.getInputStream());
//        String listStr = '[' + s.nextLine() + ']';
//        return Interpreter.constructMuaList(listStr);
        return null;
    }

    public MuaReadListOperator() {
        super(FUNC_NAME, ARGUMENT_NUM, true);
    }
}
