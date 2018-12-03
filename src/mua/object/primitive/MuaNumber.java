package mua.object.primitive;

import mua.object.MuaObject;

public class MuaNumber extends MuaObject implements Comparable<MuaNumber> {
    private double value;
    //private final static double EPSILON = 0.001; //need to change

    public MuaNumber(double value) {
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        double anotherNum = ((MuaNumber) obj).getValue();
        return value == anotherNum; //TODO: handle float number comparison
    }

    @Override
    public int compareTo(MuaNumber o) {
        return (int) (value - o.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(getValue());
    }
}
