package mua;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaSymbolNotResolvableException;
import mua.lexer.FunctionTypeHandler;
import mua.namespace.NamespaceStack;
import mua.object.MuaFunction;
import mua.object.MuaObject;
import mua.object.operator.*;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNonDeterministic;
import sun.jvm.hotspot.debugger.cdbg.FunctionType;

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

        if (objectIsOperator) {
            MuaOperator operator = (MuaOperator) currentObject;
            ArgumentList argumentList = readArguments(operator.getArgumentNum(), operator.toString());
            MuaObject result = operator.operate(returnValue, argumentList);
            if (operator.getClass() == MuaOutputOperator.class
                    || operator.getClass() == MuaIfOperator.class
                    || operator.getClass() == MuaRepeatOperator.class) {
                returnValue = result;
            }
            return result;
        }
        boolean objectIsFunction = currentObject.getClass() == MuaFunction.class;
        if (objectIsFunction) {
            MuaFunction functor = (MuaFunction) currentObject;
            ArgumentList argumentList = readArguments(functor.getArgumentNum(), functor.toString());
            return functor.run(argumentList.toMuaList());
        }
        boolean objectIsNonDeterministic = currentObject.getClass() == MuaNonDeterministic.class;
        if (objectIsNonDeterministic) {
            MuaObject realObject = removeNonDeterministic(((MuaNonDeterministic) currentObject).getName());
            return parseSingleObject(realObject);
        }
        return currentObject;
    }

    private MuaObject removeNonDeterministic(String name) throws MuaException {
        //TODO
        MuaObject searchResult = NamespaceStack.getInstance().getObject(name);
        if (searchResult == null) {
            throw new MuaSymbolNotResolvableException(name);
        }
        if (searchResult.getClass() == MuaList.class) {
            FunctionTypeHandler typeHandler = new FunctionTypeHandler();
            if (typeHandler.isLegalFunctionList(name, searchResult)) {
                return typeHandler.returnObjectOfThisType(name).get(0);
            }
            throw new MuaSymbolNotResolvableException(name);
        }
        return searchResult;
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
