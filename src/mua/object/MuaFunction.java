package mua.object;

import mua.InstructionRunner;
import mua.exception.*;
import mua.namespace.NamespaceStack;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNonDeterministic;
import mua.object.primitive.MuaNone;
import mua.object.primitive.MuaWord;

import java.util.Iterator;

public class MuaFunction extends MuaObject {
    private String functionName;
    private MuaList formalParameters;
    private MuaList instructions;
    private MuaList actualParameters;

    public int getArgumentNum() {
        return formalParameters.size();
    }

    public MuaFunction(String functionName, MuaList formalParameters, MuaList instructions) {
        this.functionName = functionName;
        this.formalParameters = formalParameters;
        this.instructions = instructions;
    }

    private void setActualParameters(MuaObject actualParametersObject) throws MuaArgumentTypeNotCompatibleException {
        if (actualParametersObject.getClass() != MuaList.class) {
            throw new MuaArgumentTypeNotCompatibleException(functionName);
        }
        this.actualParameters = (MuaList) actualParametersObject;
    }

    public MuaObject run(MuaObject actualParametersObject) throws MuaException {
        setActualParameters(actualParametersObject);
        checkParameter();
        NamespaceStack.getInstance().namespaceBegin();
        addParameterToNamespace();
        InstructionRunner instructionRunner =
                new InstructionRunner(instructions.getList(), InstructionRunner.Mode.SCRIPT);
        try {
            instructionRunner.run();
        } catch (MuaStopExecutionException e) {
            //do nothing
        }
        MuaObject returnValue = instructionRunner.getReturnValue();
        NamespaceStack.getInstance().namespaceEnd();
        return returnValue;
    }

    private void addParameterToNamespace() throws MuaIllegalFunctionParameterException {
        Iterator<MuaObject> formalParameterIterator = formalParameters.listIterator();
        Iterator<MuaObject> actualParameterIterator = actualParameters.listIterator();
        while (formalParameterIterator.hasNext()) {
            MuaObject formalParameter = formalParameterIterator.next();
            MuaObject actualParameter = actualParameterIterator.next();
            //TODO: nondeterministic
            if (formalParameter.getClass() != MuaNonDeterministic.class) {
                throw new MuaIllegalFunctionParameterException(functionName);
            }
            String name = ((MuaNonDeterministic) formalParameter).getName();
            NamespaceStack.getInstance().put(name, actualParameter);
        }
    }

    private void checkParameter()
            throws MuaArgumentNumNotCompatibleException {
        if (formalParameters.size() != actualParameters.size()) {
            throw new MuaArgumentNumNotCompatibleException(functionName);
        }
    }

    @Override
    public String toString() {
        return functionName;
    }
}
