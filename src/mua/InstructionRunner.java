package mua;

import mua.exception.MuaException;
import mua.exception.QuitInterpreterException;
import mua.object.MuaObject;
import mua.object.functor.ArgumentList;
import mua.object.functor.MuaFunctor;

import java.util.Iterator;
import java.util.List;

public class InstructionRunner {
    private List<MuaObject> objectList;
    private Iterator<MuaObject> objectListIterator;

    InstructionRunner(List<MuaObject> objectList) {
        this.objectList = objectList;
    }

    void run() throws MuaException {
        objectListIterator = objectList.listIterator();
        while (objectListIterator.hasNext()) {
            MuaObject currentObject = objectListIterator.next();
            MuaObject resultObject = parseSingleObject(currentObject);
            Interpreter.printOnConsole(resultObject);
        }
    }

    private MuaObject parseSingleObject(MuaObject currentObject) throws MuaException {
        try {
            boolean objectIsFunctor = currentObject.getClass().getSuperclass() == MuaFunctor.class;
            if (objectIsFunctor) {
                MuaFunctor functor = (MuaFunctor) currentObject;
                ArgumentList argumentList = readArguments(functor.getArgumentNum());
                return functor.operate(argumentList);
            } else {
                return currentObject;
            }
        } catch (QuitInterpreterException e) {
            //TODO
            throw e;
        }
    }

    private ArgumentList readArguments(int argumentNum) throws MuaException {
        ArgumentList argumentList = new ArgumentList();
        for (int i = 0; i < argumentNum; ++i) {
            //TODO: argument not enough
            argumentList.add(parseSingleObject(objectListIterator.next()));
        }
        return argumentList;
    }
}
