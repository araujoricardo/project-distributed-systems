package util;

import java.io.Serializable;

public class MsgReq implements Serializable {
    private int value;
    private String operation;
    private String evenOdd;

    public MsgReq(String operation, int value, String evenOdd) {
        this.value = value;
        this.operation = operation;
        this.evenOdd = evenOdd;
    }

    public int getValue() {
        return value;
    }

    public String getEvenOdd() {
        return evenOdd;
    }

    public String getOperation() {
        return operation;
    }
}
