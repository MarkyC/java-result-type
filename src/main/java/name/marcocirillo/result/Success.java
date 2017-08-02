package name.marcocirillo.result;

import java.util.function.Function;

public final class Success<T> extends Result<T> {
    private T result;

    private Success(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public static <T> Success<T> of(T t) {
        return new Success<>(t);
    }

    @Override
    <U> U fold(Function<Success<T>, ? extends U> successFunction, Function<Failure<?>, ? extends U> failureFunction) {
        return successFunction.apply(this);
    }
}
