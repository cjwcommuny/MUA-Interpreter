package mua.object.operator;

import mua.exception.MuaException;
import mua.namespace.NamespaceStack;
import mua.object.MuaObject;
import mua.object.primitive.MuaNone;

public class MuaExportOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        NamespaceStack.getInstance().exportLocalName();
        return new MuaNone();
    }

    public MuaExportOperator() {
        super("export", 0, true);
    }
}
