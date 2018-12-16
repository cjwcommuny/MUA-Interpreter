package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaIllegalExpressionException;
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
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException, MuaIllegalExpressionException {
        MuaObject name = argumentList.get(0);
        MuaObject value = argumentList.get(1);
        MuaObject result = NamespaceStack.getInstance().getObject(((MuaWord) name).getValue());
        if (result != null && result.getClass().getSuperclass() == MuaOperator.class) {
            MuaOperator operator = (MuaOperator) result;
            if (operator.isBuiltIn()) {
                throw new MuaIllegalExpressionException("Cannot make builtin name: " + name, MuaException.Level.ERROR);
            }
        }
        if (name.getClass() != MuaWord.class) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
        //TODO: 使用多态实现make function 和make word
        NamespaceStack.getInstance().put(((MuaWord) name).getValue(), value);
        return new MuaNone();
    }
}
