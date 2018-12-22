package mua.lexer;

import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaNonDeterministic;

import java.util.List;

public class NonDeterministicTypeHandler extends TypeHandler {
    @Override
    public boolean isThisType(String str) {
        return true;
    }

    @Override
    protected void addReturnObjectToList(List<MuaObject> returnList, String str) throws MuaException {
        returnList.add(new MuaNonDeterministic(str));
    }

}
