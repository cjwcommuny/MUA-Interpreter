package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public interface CompareOperator {
    default double compareTo(MuaObject arg1, MuaObject arg2) throws MuaArgumentTypeNotCompatibleException {
        if (arg1.getClass() == MuaNumber.class) {
            MuaNumber op1 = (MuaNumber) arg1;
            if (arg2.getClass() == MuaNumber.class) {
                MuaNumber op2 = (MuaNumber) arg2;
                return op1.getValue() - op2.getValue();
            } else if (arg2.getClass() == MuaBool.class) {
                MuaBool op2 = (MuaBool) arg2;
                return new MuaWord(op1).getValue().compareTo(new MuaWord(op2).getValue());
            } else if (arg2.getClass() == MuaWord.class) {
                MuaWord op2 = (MuaWord) arg2;
                return new MuaWord(op1).getValue().compareTo(op2.getValue());
            } else {
                throw new MuaArgumentTypeNotCompatibleException(this.toString());
            }
        } else if (arg1.getClass() == MuaBool.class) {
            MuaBool op1 = (MuaBool) arg1;
            if (arg2.getClass() == MuaNumber.class) {
                MuaNumber op2 = (MuaNumber) arg2;
                return new MuaWord(op1).getValue().compareTo(new MuaWord(op2).getValue());
            } else if (arg2.getClass() == MuaBool.class) {
                MuaBool op2 = (MuaBool) arg2;
                return new MuaWord(op1).getValue().compareTo(new MuaWord(op2).getValue());
            } else if (arg2.getClass() == MuaWord.class) {
                MuaWord op2 = (MuaWord) arg2;
                return new MuaWord(op1).getValue().compareTo(op2.getValue());
            } else {
                throw new MuaArgumentTypeNotCompatibleException(this.toString());
            }
        } else if (arg1.getClass() == MuaWord.class) {
            MuaWord op1 = (MuaWord) arg1;
            if (arg2.getClass() == MuaNumber.class) {
                MuaNumber op2 = (MuaNumber) arg2;
                return op1.getValue().compareTo(new MuaWord(op2).getValue());
            } else if (arg2.getClass() == MuaBool.class) {
                MuaBool op2 = (MuaBool) arg2;
                return op1.getValue().compareTo(new MuaWord(op2).getValue());
            } else if (arg2.getClass() == MuaWord.class) {
                MuaWord op2 = (MuaWord) arg2;
                return op1.getValue().compareTo(op2.getValue());
            } else {
                throw new MuaArgumentTypeNotCompatibleException(this.toString());
            }
        } else {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
    }
}
