package mua.object.operator;

import mua.exception.*;
import mua.namespace.NamespaceStack;
import mua.object.MuaObject;
import mua.object.primitive.MuaWord;

public class MuaThingOperator extends MuaOperator {
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
            throws MuaArgumentTypeNotCompatibleException, MuaObjectNotExistException {
        MuaObject name = argumentList.get(0);
        if (name.getClass() != MuaWord.class) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
        //TODO: error handling
        MuaObject result = NamespaceStack.getInstance().getObject(((MuaWord) name).getValue());
        if (result == null) {
            throw new MuaObjectNotExistException(name.toString());
        }
        return result;
    }
}
