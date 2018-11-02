package mua.object;

import mua.exception.MuaArgumentTypeNotCompatibleException;

public class MuaNumber extends MuaObject {
    private double value;
    //private final static double EPSILON = 0.001; //need to change

    public MuaNumber(double value) {
        super(MuaType.number);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        double anotherNum = ((MuaNumber) obj).getValue();
        return value == anotherNum; //might need to be change
    }

    @Override
    public boolean lessThan(MuaObject obj) throws MuaArgumentTypeNotCompatibleException{
        if (getClass() != obj.getClass()) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        return value < ((MuaNumber) obj).getValue();
    }
}
