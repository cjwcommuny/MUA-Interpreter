package mua.object.operator;

import mua.InterpreterController;
import mua.exception.MuaException;
import mua.namespace.NamespaceStack;
import mua.object.MuaObject;
import mua.object.primitive.MuaNone;

import java.util.List;

public class MuaPostAllOperator extends MuaOperator {
    public MuaPostAllOperator() {
        super("poall", 0, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        List<String> names = NamespaceStack.getInstance().listAllNameFromCurrentNamespace();
        for (String name: names) {
            InterpreterController.printOnConsole(name);
        }
        return new MuaNone();
    }
}
