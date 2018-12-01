package mua.Lexer;

import mua.exception.MuaException;
import mua.object.*;

import java.util.ArrayList;
import java.util.List;

public abstract class TypeHandler  {
    public abstract boolean isThisType(String str);
    public abstract MuaObject returnObjectOfThisType(String str) throws MuaException;
}

class NumericTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public MuaObject returnObjectOfThisType(String str) {
        return new MuaNumber(Double.parseDouble(str));
    }
}

class WordTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return str.length() >= 2 && str.charAt(0) == '\"';
    }

    @Override
    public MuaObject returnObjectOfThisType(String str) {
        return new MuaWord(str.substring(1));
    }
}

class BoolTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return "true".equals(str) || "false".equals(str);
    }

    @Override
    public MuaObject returnObjectOfThisType(String str) {
        return new MuaBool(Boolean.parseBoolean(str));
    }
}

class ListTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']';
    }

    @Override
    public MuaObject returnObjectOfThisType(String str) throws MuaException {
        return constructMuaList(str);
    }

    private MuaList constructMuaList(String listStr) throws MuaException {
        String listStrWithoutBrace = listStr.substring(1, listStr.length() - 1);
        String[] tokenList = Lexer.instructionToTokenList(listStrWithoutBrace);
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
    public MuaObject returnObjectOfThisType(String str) throws MuaException {
        return new MuaDereference(str.substring(1));
    }
}

//TODO
class OperatorTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return false;
    }

    @Override
    public MuaObject returnObjectOfThisType(String str) throws MuaException {
        return null;
    }
}