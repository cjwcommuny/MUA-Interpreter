package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.namespace.NamespaceStack;
import mua.object.primitive.MuaBool;
import mua.object.MuaObject;
import mua.object.primitive.MuaWord;

public class MuaIsNameOperator extends MuaOperator {
    public static final String FUNC_NAME = "isname";
    private static final int ARGUMENT_NUM = 1;
    public MuaIsNameOperator() {
super(FUNC_NAME, ARGUMENT_NUM, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaArgumentTypeNotCompatibleException {
        MuaObject name = argumentList.get(0);
        if (name.getClass() != MuaWord.class) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
        boolean objectFound = NamespaceStack.getInstance().getObject(((MuaWord) name).getValue()) != null;
        return new MuaBool(objectFound);
    }
}
