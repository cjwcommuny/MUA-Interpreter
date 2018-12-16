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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MuaLoadOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "load";
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() != MuaWord.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
        }
        String fileName = ((MuaWord) object).getValue();
        File file = new File(fileName);
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Namespace newNamespace = (Namespace) in.readObject();
            in.close();
            fileIn.close();
            NamespaceStack.getInstance().mergeNamespace(newNamespace);
        } catch (IOException | ClassNotFoundException e) {
            throw new MuaSaveLoadException();
        }
        return new MuaNone();
    }

    public MuaLoadOperator() {
        super(FUNCTION_NAME, 1, true);
    }
}
