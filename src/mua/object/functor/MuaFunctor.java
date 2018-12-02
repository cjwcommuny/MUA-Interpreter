package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaDivideOrModuleZeroException;
import mua.exception.MuaException;
import mua.object.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MuaFunctor extends MuaObject {
    private String funcName;

    @Override
    public String toString() {
        return funcName;
    }

    private int argumentNum;
    private boolean isBuiltIn = false;

    //private MuaType[] argumentTypes;
    //private ArgumentList argumentList = new ArgumentList();



    public MuaFunctor(String funcName, int argumentNum, boolean isBuiltIn) {
        super(MuaType.functor);
        this.funcName = funcName;
        this.argumentNum = argumentNum;
        this.isBuiltIn = isBuiltIn;
        //this.argumentTypes = argumentTypes;
    }

    public int getArgumentNum() {
        return argumentNum;
    }

    public abstract MuaObject operate(ArgumentList argumentList)
            throws MuaException;
    /*
    public MuaType[] getArgumentTypes() {
        return argumentTypes;
    }
    */

    protected void checkArgumentNum(ArgumentList argumentList) throws MuaArgumentNumNotCompatibleException{
        if (argumentList.size() != argumentNum) {
            throw new MuaArgumentNumNotCompatibleException();
        }
    }
}
