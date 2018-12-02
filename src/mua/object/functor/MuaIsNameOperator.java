package mua.object.functor;

import mua.Interpreter;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaBool;
import mua.object.MuaObject;
import mua.object.MuaType;
import mua.object.MuaWord;

public class MuaIsNameOperator extends MuaFunctor {
    public static final String FUNC_NAME = "isname";
    private static final int ARGUMENT_NUM = 1;
    public MuaIsNameOperator() {
super(FUNC_NAME, ARGUMENT_NUM, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        checkArgumentNum(argumentList);
        MuaObject name = argumentList.get(0);
        if (name.getMuaType() != MuaType.word) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        //TODO
        //boolean objectFound = Interpreter.dataTable.getObject(((MuaWord) name).getValue()) != null;
        return null; //bad
    }
}
