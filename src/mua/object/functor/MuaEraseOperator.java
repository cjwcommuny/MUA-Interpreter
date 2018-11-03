package mua.object.functor;

import mua.Interpreter;
import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaNone;
import mua.object.MuaObject;
import mua.object.MuaType;
import mua.object.MuaWord;

public class MuaEraseOperator extends MuaFunctor {
    public static final String FUNC_NAME = "erase";
    private static final int ARGUMENT_NUM = 1;
    public MuaEraseOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        checkArgumentNum(argumentList);
        MuaObject name = argumentList.get(0);
        if (name.getMuaType() != MuaType.word) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        Interpreter.dataTable.remove(((MuaWord) name).getValue());
        return new MuaNone();
    }
}
