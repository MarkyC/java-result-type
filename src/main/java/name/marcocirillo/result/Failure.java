package name.marcocirillo.result;

import java.util.function.Function;

public final class Failure<E extends Exception> extends Result<E> {
    private E error;

    private Failure(E error) {
        this.error = error;
    }

    public E getError() {
        return error;
    }

    public static <E extends Exception> Failure<E> of(E e) {
        return new Failure<>(e);
    }

    @Override
    <U> U fold(Function<Success<E>, ? extends U> successFunction,
               Function<Failure<?>, ? extends U> failureFunction) {
        return failureFunction.apply(this);
    }
}
