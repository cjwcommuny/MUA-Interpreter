package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.MuaObject;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public interface ArithmeticOperator {
    default double convertToDouble(MuaObject obj) throws MuaArgumentTypeNotCompatibleException {
        if (obj.getClass() == MuaWord.class) {
            return new MuaNumber((MuaWord) obj).getValue();
        } else if (obj.getClass() == MuaNumber.class) {
            return ((MuaNumber) obj).getValue();
        } else {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
    }
}
