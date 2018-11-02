package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaDivideOrModuleZeroException;
import mua.object.*;

import java.util.ArrayList;
import java.util.Collection;

public abstract class MuaFunctor extends MuaObject {
    private String funcName;
    private boolean isBuiltIn = false;
    //private ArgumentList argumentList = new ArgumentList();

    protected class ArgumentList extends ArrayList<MuaObject> { //type alias
        public ArgumentList(int initialCapacity) {
            super(initialCapacity);
        }

        public ArgumentList() {
        }

        public ArgumentList(Collection<? extends MuaObject> c) {
            super(c);
        }
    }

    public MuaFunctor(String funcName, boolean isBuiltIn) {
        super(MuaType.functor);
        this.funcName = funcName;
        this.isBuiltIn = isBuiltIn;
    }

    public abstract int getArgumentNum();

    public abstract MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException, MuaDivideOrModuleZeroException;
}
