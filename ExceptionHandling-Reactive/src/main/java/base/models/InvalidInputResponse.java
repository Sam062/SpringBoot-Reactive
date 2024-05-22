package base.models;

import java.util.Date;

public class InvalidInputResponse {

    private int value;
    private String errMsg;
    private Date date;

    public InvalidInputResponse(int value, String errMsg, Date date) {
        this.value = value;
        this.errMsg = errMsg;
        this.date = date;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "InvalidInputResponse{" +
                "value=" + value +
                ", errMsg='" + errMsg + '\'' +
                ", date=" + date +
                '}';
    }
}
