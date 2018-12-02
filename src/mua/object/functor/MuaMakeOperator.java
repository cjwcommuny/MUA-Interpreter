package mua.object.functor;

import mua.Interpreter;
import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaDivideOrModuleZeroException;
import mua.object.MuaNone;
import mua.object.MuaObject;
import mua.object.MuaType;
import mua.object.MuaWord;

public class MuaMakeOperator extends MuaFunctor {
    public static final String FUNC_NAME = "make";
    private static final int ARGUMENT_NUM = 2;
    public MuaMakeOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException,
            MuaArgumentTypeNotCompatibleException {
        checkArgumentNum(argumentList);
        MuaObject name = argumentList.get(0);
        MuaObject value = argumentList.get(1);
        if (name.getMuaType() != MuaType.word) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        //Interpreter.dataTable.updateObject(((MuaWord) name).getValue(), value);
        return new MuaNone();
    }
}
