package mua.object.functor;

import mua.exception.MuaDivideOrModuleZeroException;
import mua.object.MuaBool;
import mua.object.MuaNone;
import mua.object.MuaNumber;
import mua.object.MuaObject;

public enum MuaOperatorType {
    //add
    ADD("+") {
        @Override
        MuaObject apply(MuaObject obj1, MuaObject obj2) {
            double op1, op2;
            op1 = ((MuaNumber) obj1).getValue();
            op2 = ((MuaNumber) obj2).getValue();
            return new MuaNumber(op1 + op2);
        }
    },

    //sub
    SUB("-") {
        @Override
        MuaObject apply(MuaObject obj1, MuaObject obj2) {
            double op1, op2;
            op1 = ((MuaNumber) obj1).getValue();
            op2 = ((MuaNumber) obj2).getValue();
            return new MuaNumber(op1 - op2);
        }
    },

    //multiply
    MUL("*") {
        @Override
        MuaObject apply(MuaObject obj1, MuaObject obj2) {
            double op1, op2;
            op1 = ((MuaNumber) obj1).getValue();
            op2 = ((MuaNumber) obj2).getValue();
            return new MuaNumber(op1 * op2);
        }
    },

    //divide
    DIV("/") {
        @Override
        MuaObject apply(MuaObject obj1, MuaObject obj2) throws MuaDivideOrModuleZeroException {
            double op1, op2;
            op1 = ((MuaNumber) obj1).getValue();
            op2 = ((MuaNumber) obj2).getValue();
            if (op2 <= epsilon && op2 > -epsilon) {
                throw new MuaDivideOrModuleZeroException();
            }
            return new MuaNumber(op1 / op2);
        }
    },

    //modulo
    MOD("%") {
        @Override
        MuaObject apply(MuaObject obj1, MuaObject obj2) throws MuaDivideOrModuleZeroException {
            double op1, op2;
            op1 = ((MuaNumber) obj1).getValue();
            op2 = ((MuaNumber) obj2).getValue();
            if (op2 <= epsilon && op2 > -epsilon) {
                throw new MuaDivideOrModuleZeroException();
            }
            return new MuaNumber(op1 % op2);
        }
    },

    //equal
    EQUAL("eq") {
        @Override
        MuaObject apply(MuaObject obj1, MuaObject obj2) {
            return new MuaBool(obj1.equals(obj2));
        }
    },

    LESS_THAN("lt") {
        @Override
        MuaObject apply(MuaObject obj1, MuaObject obj2) throws MuaDivideOrModuleZeroException {
            return new MuaBool(obj1.lessThan(obj2));
        }
    }

    String operatorName;
    double epsilon = 0.001;
    abstract MuaObject apply(MuaObject obj1, MuaObject obj2) throws MuaDivideOrModuleZeroException;

    MuaOperatorType(String operatorName) {
        this.operatorName = operatorName;
    }
}
