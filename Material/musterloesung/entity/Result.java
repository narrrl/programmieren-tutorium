package musterloesung.entity;

public class Result<T> {

    private final T output;

    private final boolean isError;

    private Result(final T output, final boolean isError) {
        this.isError = isError;
        this.output = output;
    }

    public static <T> Result<T> asError(final T output) {
        return new Result<>(output, true);
    }

    public static <T> Result<T> asSuccess(final T output) {
        return new Result<>(output, false);
    }

    public T getOutput() {
        return this.output;
    }

    public boolean isError() {
        return isError;
    }

    @Override
    public String toString() {
        return this.output.toString();
    }
}
