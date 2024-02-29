package nsu.task_2_1_1.exceptions;

public class OnlyOneThreadAcceptedException extends Throwable {

    public OnlyOneThreadAcceptedException() {
        super();
    }

    public OnlyOneThreadAcceptedException(String message) {
        super(message);
    }
}
