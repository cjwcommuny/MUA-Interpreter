package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public interface BoolOperator {
    default boolean convertToBool(MuaObject obj) throws MuaArgumentTypeNotCompatibleException {
        if (obj.getClass() == MuaWord.class) {
            return new MuaBool((MuaWord) obj).getValue();
        } else if (obj.getClass() == MuaBool.class) {
            return ((MuaBool) obj).getValue();
        } else {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
    }
}
