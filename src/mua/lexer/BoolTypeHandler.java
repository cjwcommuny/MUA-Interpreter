package mua.lexer;

import mua.object.MuaObject;
import mua.object.primitive.MuaBool;

import java.util.List;

public class BoolTypeHandler extends TypeHandler {
    private String bool;

    @Override
    public boolean isThisType(String str) {
        bool = null;
        boolean isBool = "true".equals(str) || "false".equals(str);
        bool = str;
        if (str.length() >= 2 && str.charAt(0) == '\"') {
            String strWithoutStarter = str.substring(1);
            isBool |= "true".equals(strWithoutStarter) || "false".equals(strWithoutStarter);
            bool = strWithoutStarter;
        }
        return isBool;
    }

    @Override
    protected void addReturnObjectToList(List<MuaObject> returnList, String str) {
        returnList.add(new MuaBool(Boolean.parseBoolean(bool)));
    }
}