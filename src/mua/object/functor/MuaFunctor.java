package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaDivideOrModuleZeroException;
import mua.object.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MuaFunctor extends MuaObject {
    private String funcName;
    private boolean isBuiltIn = false;
    //private MuaType[] argumentTypes;
    //private ArgumentList argumentList = new ArgumentList();



    public MuaFunctor(String funcName, boolean isBuiltIn) {
        super(MuaType.functor);
        this.funcName = funcName;
        this.isBuiltIn = isBuiltIn;
        //this.argumentTypes = argumentTypes;
    }

    public abstract int getArgumentNum();

    public abstract MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException, MuaDivideOrModuleZeroException;
    /*
    public MuaType[] getArgumentTypes() {
        return argumentTypes;
    }
    */
}
