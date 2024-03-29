package mua.lexer;

import mua.exception.MuaException;
import mua.namespace.NamespaceStack;
import mua.object.*;
import mua.object.operator.MuaOperator;
import mua.object.operator.MuaThingOperator;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

import java.util.LinkedList;
import java.util.List;

public abstract class TypeHandler  {
    public List<MuaObject> returnObjectOfThisType(String str) throws MuaException {
        List<MuaObject> returnList = new LinkedList<>();
        addReturnObjectToList(returnList, str);
        return returnList;
    }
    //this two function has to be used contiguously
    public abstract boolean isThisType(String str);

    protected abstract void addReturnObjectToList(List<MuaObject> returnList, String str) throws MuaException;
}



class WordTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return str.length() >= 1 && str.charAt(0) == '\"';
    }

    @Override
    protected void addReturnObjectToList(List<MuaObject> returnList, String str) {
        returnList.add(new MuaWord(str.substring(1)));
    }
}

class ListTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']';
    }

    @Override
    protected void addReturnObjectToList(List<MuaObject> returnList, String str) throws MuaException {
        returnList.add(constructMuaList(str));
    }

    private MuaList constructMuaList(String listStr) throws MuaException {
        String listStrWithoutBrace = listStr.substring(1, listStr.length() - 1);
        List<String> tokenList = Lexer.instructionToTokenList(listStrWithoutBrace);
        List<MuaObject> listElements = Lexer.evaluateTokenList(tokenList);
        return new MuaList(listElements);
    }
}

class DereferenceTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return str.length() >= 2 && str.charAt(0) == ':';
    }

    @Override
    protected void addReturnObjectToList(List<MuaObject> returnList, String str) {
        returnList.add(new MuaThingOperator());
        returnList.add(new MuaWord(str.substring(1)));
    }
}

class OperatorTypeHandler extends TypeHandler {
    private MuaOperator operator;
    @Override
    public boolean isThisType(String str) {
        operator = null;
        MuaObject objectGot = NamespaceStack.getInstance().getObject(str);
        if (objectGot == null || objectGot.getClass().getSuperclass() != MuaOperator.class) {
            return false;
        }
        operator = (MuaOperator) objectGot;
        return true;
    }

    @Override
    protected void addReturnObjectToList(List<MuaObject> returnList, String str) {
        returnList.add(operator);
    }
}

