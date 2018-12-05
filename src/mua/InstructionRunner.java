package mua;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.QuitInterpreterException;
import mua.object.MuaObject;
import mua.object.operator.ArgumentList;
import mua.object.operator.MuaOperator;

import java.util.Iterator;
import java.util.List;

class InstructionRunner {
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
            InterpreterController.printOnConsole(resultObject);
        }
    }

    private MuaObject parseSingleObject(MuaObject currentObject) throws MuaException {
        boolean objectIsOperator = currentObject.getClass().getSuperclass() == MuaOperator.class;
        if (objectIsOperator) {
            MuaOperator operator = (MuaOperator) currentObject;
            ArgumentList argumentList = readArguments(operator.getArgumentNum(), operator.toString());
            return operator.operate(argumentList);
        } else {
            return currentObject;
        }
    }

    private ArgumentList readArguments(int argumentNum, String operator) throws MuaException {
        ArgumentList argumentList = new ArgumentList();
        for (int i = 0; i < argumentNum; ++i) {
            if (objectListIterator.hasNext()) {
                argumentList.add(parseSingleObject(objectListIterator.next()));
            } else {
                throw new MuaArgumentNumNotCompatibleException(operator);
            }
        }
        return argumentList;
    }
}
