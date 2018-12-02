package mua.object;

import mua.exception.MuaException;
import mua.exception.MuaSymbolNotResolvableException;
import mua.namespace.NamespaceStack;

public class MuaDereference extends MuaObject {
    //TODO: merge this type to functor
    private String word;

    public MuaDereference(String word) {
        super(MuaType.dereference);
        this.word = word;
    }

    public MuaObject operate() throws MuaException {
        MuaObject object = NamespaceStack.getInstance().getObject(word);
        if (object == null) {
            throw new MuaSymbolNotResolvableException();
        }
        return object;
    }
}
