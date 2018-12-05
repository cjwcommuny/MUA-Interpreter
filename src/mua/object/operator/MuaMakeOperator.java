package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.namespace.NamespaceStack;
import mua.object.primitive.MuaNone;
import mua.object.MuaObject;
import mua.object.primitive.MuaWord;

public class MuaMakeOperator extends MuaOperator {
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
            throws MuaArgumentTypeNotCompatibleException {
        MuaObject name = argumentList.get(0);
        MuaObject value = argumentList.get(1);
        if (name.getClass() != MuaWord.class) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
        //TODO: 使用多态实现make function 和make word
        NamespaceStack.getInstance().put(((MuaWord) name).getValue(), value);
        return new MuaNone();
    }
}
