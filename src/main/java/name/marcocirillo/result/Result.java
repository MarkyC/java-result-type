package name.marcocirillo.result;

import java.util.function.Function;

abstract class Result<T> {
    abstract <U> U fold(
            Function<Success<T>, ? extends U> successFunction,
            Function<Failure<?>, ? extends U> failureFunction
    );

    boolean isSuccess() {
        return fold(
                success -> true,
                fail -> false
        );
    }

    <U> Result<U> map(Function<T, U> function) {
        return fold(
                success -> Success.of(function.apply(success.getResult())),
                fail -> { throw new UnsupportedOperationException("failureFunction should never be called in map()"); }
        );
    }

    Result<? extends Exception> mapError(Function<? super Exception, ? extends Exception> function) {
        return fold(
                success -> { throw new UnsupportedOperationException("successFunction should never be called in mapError()"); },
                fail ->  Failure.of(function.apply(fail.getError()))
        );
    }

    <U> Result<U> flatMap(Function<T, Result<U>> function) {
        return fold(
                success -> function.apply(success.getResult()),
                fail -> { throw new UnsupportedOperationException("failureFunction should never be called in flatMap()"); }
        );
    }

    <U> Result<U> flatMapError(Function<? super Exception, Result<U>> function) {
        return fold(
                success -> { throw new UnsupportedOperationException("successFunction should never be called in flatMapError()"); },
                fail -> function.apply(fail.getError())
        );
    }
}