package mua.object.primitive;

public class MuaNone extends MuaPrimitiveType {
    public MuaNone() {

    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass());
    }

    @Override
    public String toString() {
        return "";
    }
}
