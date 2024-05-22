package base.exceptionHandler;

public class InvalidInputException extends RuntimeException {
    private static final String errorMsg = "Allowed range is 10-20";
    private int input;


    public InvalidInputException(int input) {
        super(errorMsg);
        this.input = input;
    }

    public int getInput() {
        return input;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
