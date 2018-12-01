package mua.Parser;

import mua.exception.MuaBraceNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaSymbolNotResolvableException;
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
        //TODO: 穿进去的参数应该包含括号（如果是list的话）
        String[] subListArr = Parser.instructionToTokenList(listStrWithoutBrace);
        List<MuaObject> listToConstructMuaList = new ArrayList<>();
        for (String token: subListArr) {
            MuaObject muaObject = Parser.parseDataType(token);
            if (muaObject != null) {
                listToConstructMuaList.add(muaObject);
            } else {
                listToConstructMuaList.add(new MuaInstruction(token));
            }
        }
        return new MuaList(listToConstructMuaList);
    }
}
