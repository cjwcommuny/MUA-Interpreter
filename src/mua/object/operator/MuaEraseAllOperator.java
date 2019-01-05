package mua.object.operator;

import mua.exception.MuaException;
import mua.namespace.NamespaceStack;
import mua.object.MuaObject;
import mua.object.primitive.MuaNone;

public class MuaEraseAllOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        NamespaceStack.getInstance().eraseAllNameFromCurrentNamespace();
        return new MuaNone();
    }

    public MuaEraseAllOperator() {
        super("erall", 0, true);
    }
}
