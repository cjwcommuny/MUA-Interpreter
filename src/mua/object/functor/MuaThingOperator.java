package mua.object.functor;

import mua.Interpreter;
import mua.exception.*;
import mua.object.MuaObject;
import mua.object.MuaType;
import mua.object.MuaWord;

public class MuaThingOperator extends MuaFunctor {
    public static final String FUNC_NAME = "thing";
    private static final int ARGUMENT_NUM = 1;
    public MuaThingOperator() {
        super(FUNC_NAME, ARGUMENT_NUM, true);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaException {
        checkArgumentNum(argumentList);
        MuaObject name = argumentList.get(0);
        if (name.getMuaType() != MuaType.word) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        MuaObject resultFound = Interpreter.dataTable.getObject(((MuaWord) name).getValue());
        if (resultFound == null) {
            throw new MuaObjectNotExistException();
        }
        return resultFound;
    }
}
