package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaIllegalExpressionException;
import mua.namespace.NamespaceStack;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaNone;
import mua.object.MuaObject;
import mua.object.primitive.MuaNumber;
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

        if (name.getClass() != MuaWord.class &&
                name.getClass() != MuaBool.class &&
                name.getClass() != MuaNumber.class) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
        MuaWord word;
        if (name.getClass() == MuaWord.class) {
            word = (MuaWord) name;
        } else if (name.getClass() == MuaBool.class) {
            word = new MuaWord((MuaBool) name);
        } else {
            word = new MuaWord((MuaNumber) name);
        }
        MuaObject result = NamespaceStack.getInstance().getObject(word.getValue());
        if (result != null && result.getClass().getSuperclass() == MuaOperator.class) {
            MuaOperator operator = (MuaOperator) result;
            if (operator.isBuiltIn()) {
                throw new MuaIllegalExpressionException("Cannot make builtin name: " + name, MuaException.Level.ERROR);
            }
        }
        NamespaceStack.getInstance().put(word.getValue(), value);
        return new MuaNone();
    }
}
