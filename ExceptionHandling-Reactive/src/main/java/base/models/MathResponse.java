package base.models;

import java.util.Date;

public class MathResponse {
    private int result;
    private Date date;

    public MathResponse(int result, Date date) {
        this.result = result;
        this.date = date;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getResult() {
        return result;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "MathResponse{" +
                "result=" + result +
                ", date=" + date +
                '}';
    }
}
