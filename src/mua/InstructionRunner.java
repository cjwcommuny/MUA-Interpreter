package mua;

import mua.exception.MuaException;
import mua.exception.QuitInterpreterException;
import mua.object.MuaDereference;
import mua.object.MuaObject;
import mua.object.functor.ArgumentList;
import mua.object.functor.MuaFunctor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class InstructionRunner {
    private List<MuaObject> objectList;
    private Iterator<MuaObject> objectListIterator;
    List<String> resultList = new LinkedList<>();

    public InstructionRunner(List<MuaObject> objectList) {
        this.objectList = objectList;
    }

    public List<String> run() throws MuaException {
        //TODO: after execute a instruction just return it, instead run all of it
        objectListIterator = objectList.listIterator();
        while (objectListIterator.hasNext()) {
            MuaObject currentObject = objectListIterator.next();
            MuaObject resultObject = parseSingleObject(currentObject);
            resultList.add(resultObject.toString());
        }
        return resultList;
    }

    private MuaObject parseSingleObject(MuaObject currentObject) throws MuaException {
        try {
            if (currentObject.getClass().getSuperclass() == MuaFunctor.class) {
                MuaFunctor functor = (MuaFunctor) currentObject;
                ArgumentList argumentList = readArguments(functor.getArgumentNum());
                return functor.operate(argumentList);
            } else if (currentObject.getClass() == MuaDereference.class) {
                return ((MuaDereference) currentObject).operate();
            } else {
                return currentObject;
            }
        } catch (QuitInterpreterException e) {
            e.setResults(resultList);
            throw e;
        }
    }

    private ArgumentList readArguments(int argumentNum) throws MuaException {
        ArgumentList argumentList = new ArgumentList();
        if (argumentNum == -1) {
            //read a line
            while (objectListIterator.hasNext()) {
                argumentList.add(parseSingleObject(objectListIterator.next()));
            }
        } else {
            for (int i = 0; i < argumentNum; ++i) {
                //TODO: argument not enough
                argumentList.add(parseSingleObject(objectListIterator.next()));
            }
        }
        return argumentList;
    }
}
