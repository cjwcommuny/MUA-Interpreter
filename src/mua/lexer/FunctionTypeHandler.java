package mua.lexer;

import mua.exception.MuaException;
import mua.namespace.NamespaceStack;
import mua.object.MuaFunction;
import mua.object.MuaObject;
import mua.object.primitive.MuaList;

import java.util.List;

public class FunctionTypeHandler extends TypeHandler {
    private MuaFunction functor;
    @Override
    public boolean isThisType(String str) {
        functor = null;
        MuaObject objectGot = NamespaceStack.getInstance().getObject(str);
        return isLegalFunctionList(str, objectGot);
    }

    public boolean isLegalFunctionList(String name, MuaObject object) {
        if (object == null || object.getClass() != MuaList.class) {
            return false;
        }
        MuaList listObject = (MuaList) object;
        if (listObject.size() != 2) {
            return false;
        }
        MuaObject element1 = listObject.get(0);
        MuaObject element2 = listObject.get(1);
        if (element1.getClass() != MuaList.class || element2.getClass() != MuaList.class) {
            return false;
        }
        functor = new MuaFunction(name, (MuaList) element1, (MuaList) element2);
        return true;
    }

    @Override
    protected void addReturnObjectToList(List<MuaObject> returnList, String str) throws MuaException {
        returnList.add(functor);
    }
}
