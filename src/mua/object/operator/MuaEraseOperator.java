package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaObjectNotExistException;
import mua.namespace.NamespaceStack;
import mua.object.primitive.MuaNone;
import mua.object.MuaObject;
import mua.object.primitive.MuaWord;

public class MuaEraseOperator extends MuaOperator {
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
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException, MuaObjectNotExistException {
        MuaObject name = argumentList.get(0);
        if (name.getClass() != MuaWord.class) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
        MuaObject previousObject = NamespaceStack.getInstance().remove(((MuaWord) name).getValue());
        if (previousObject == null) {
            throw new MuaObjectNotExistException(name.toString());
        }
        return new MuaNone();
    }
}
