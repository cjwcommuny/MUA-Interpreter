package mua.lexer;

import mua.lexer.TypeHandler;
import mua.object.MuaObject;
import mua.object.primitive.MuaNumber;

import java.util.List;

public class NumericTypeHandler extends TypeHandler {
    String number;

    @Override
    public boolean isThisType(String str) {
        number = null;
        String regx = "-?\\d+(\\.\\d+)?";
        boolean isNumber = str.matches(regx);
        number = str;
        if (str.length() >= 2 && str.charAt(0) == '\"') {
            boolean isWordNumber = str.substring(1).matches(regx);
            isNumber |= isWordNumber;
            number = str.substring(1);
        }
        return isNumber;
    }

    @Override
    protected void addReturnObjectToList(List<MuaObject> returnList, String str) {
        returnList.add(new MuaNumber(Double.parseDouble(number)));
    }
}