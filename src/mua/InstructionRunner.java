package mua;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaFunction;
import mua.object.MuaObject;
import mua.object.operator.ArgumentList;
import mua.object.operator.MuaOperator;

import java.util.Iterator;
import java.util.List;

public class InstructionRunner {
    private Mode mode;
    private List<MuaObject> objectList;
    private Iterator<MuaObject> objectListIterator;
    private MuaObject returnValue;

    public MuaObject getReturnValue() {
        return returnValue;
    }

    public InstructionRunner(List<MuaObject> objectList, Mode mode) {
        this.objectList = objectList;
        this.mode = mode;
    }

    public void run() throws MuaException {
        objectListIterator = objectList.listIterator();
        while (objectListIterator.hasNext()) {
            MuaObject currentObject = objectListIterator.next();
            MuaObject resultObject = parseSingleObject(currentObject);
            if (mode == Mode.INTERACTIVE) {
                InterpreterController.printOnConsole(resultObject);
            }
        }
    }

    private MuaObject parseSingleObject(MuaObject currentObject) throws MuaException {
        boolean objectIsOperator = currentObject.getClass().getSuperclass() == MuaOperator.class;
        boolean objectIsFunction = currentObject.getClass() == MuaFunction.class;
        if (objectIsOperator) {
            MuaOperator operator = (MuaOperator) currentObject;
            ArgumentList argumentList = readArguments(operator.getArgumentNum(), operator.toString());
            return operator.operate(returnValue, argumentList);
        }

        if (objectIsFunction) {
            MuaFunction functor = (MuaFunction) currentObject;
            ArgumentList argumentList = readArguments(functor.getArgumentNum(), functor.toString());
            return functor.run(argumentList.toMuaList());
        }
        return currentObject;
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

    public enum Mode {
        //print every output
        INTERACTIVE,
        //only print `print` output
        SCRIPT
    }
}
