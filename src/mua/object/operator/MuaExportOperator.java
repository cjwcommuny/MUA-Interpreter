package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.namespace.NamespaceStack;
import mua.object.MuaObject;
import mua.object.primitive.*;

public class MuaExportOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        Object object = argumentList.get(0);
        if (object.getClass() == MuaList.class) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        } else {
            NamespaceStack.getInstance().exportLocalName(object.toString());
            return new MuaNone();
        }
    }

    public MuaExportOperator() {
        super("export", 1, true);
    }
}
