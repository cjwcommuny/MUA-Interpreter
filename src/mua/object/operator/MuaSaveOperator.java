package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaSaveLoadException;
import mua.namespace.Namespace;
import mua.namespace.NamespaceStack;
import mua.object.MuaObject;
import mua.object.primitive.MuaNone;
import mua.object.primitive.MuaWord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MuaSaveOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "save";
    public MuaSaveOperator() {
        super(FUNCTION_NAME, 1, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() != MuaWord.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
        }
        String fileName = ((MuaWord) object).getValue();
        Namespace currentNamespace = NamespaceStack.getInstance().getCurrentNamespace();
        File file = new File(fileName);
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(currentNamespace);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new MuaSaveLoadException();
        }
        return new MuaNone();
    }
}
