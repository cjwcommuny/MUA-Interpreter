package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaException;
import mua.object.*;

public abstract class MuaOperator extends MuaObject {
    private String funcName;
    private int argumentNum;
    private boolean isBuiltIn = false;

    public abstract MuaObject operate(ArgumentList argumentList) throws MuaException;

    @Override
    public String toString() {
        return funcName;
    }

    public MuaOperator(String funcName, int argumentNum, boolean isBuiltIn) {
        this.funcName = funcName;
        this.argumentNum = argumentNum;
        this.isBuiltIn = isBuiltIn;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && getClass() == o.getClass());
    }

    public int getArgumentNum() {
        return argumentNum;
    }

    protected void checkArgumentNum(ArgumentList argumentList) throws MuaArgumentNumNotCompatibleException{
        if (argumentList.size() != argumentNum) {
            throw new MuaArgumentNumNotCompatibleException();
        }
    }
}
