package mua.object;

public class MuaMessage extends MuaObject {
    enum MessageType {
        READ
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    private MessageType messageType;

    public MuaMessage(MessageType messageType) {
        super(MuaType.message);
        this.messageType = messageType;
    }
}
