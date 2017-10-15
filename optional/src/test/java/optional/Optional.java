package optional;

public class Optional<T> {
    private final T value;
    private final Optional<?> EMPTY = new Optional<java.lang.Object>();//типизирован обджектом без вопроса

    private Optional(T value){
        this.value = Objects.requireNotNull(value);;
    }

    public static <T> Optional<T> of(T value){

        return new Optional<>(value);
    }
    public static <T> Optional<T> empty(){
        return (Optional<T>) EMPTY;
    }

    public static <T> Optional<T> ofNullable(T value){
        return value == null ? empty() : of(value);
    }

    public boolean isPresent(){
        return value != null;
    }

    public void ifPresent(Consumer<T> action){
        if (isPresent()) {
            action.accept(value);
        }
    }

    public T get(){
        if (!isPresent()) {
            throw new NoSuchElementException("Haven't value");
        }
    }

    public T orElse(T defaultValue){
        return isPresent() ? value : defaultValue;
    }

    public T orElseGet(Supplier<T> supplier){
        return isPresent() ? value : supplier.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier){
        if (isPresent()) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        if (!isPresent()) {
            return predicate.test(value) ? this : empty();
        }
        else {
            return empty();
        }
    }

    public <R> Optional<R> map(Function<? super T, ? extends R> function) {
        if (isPresent()) {
            return ofNullable(mapper.apply(value));
        } else {
            return empty();
        }
    }

    public <R> Optional<R> flatMap(Function<? super T, ? extends R> function) {
        if (isPresent()) {
            return mapper.
        } else {
            return empty();
        }
    }
        // empty
    // of
    // ofNullable
    // isPresent
    // ifPresent
    // get
    // orElse
    // orElseGet
    // orElseThrow
    // filter
    // map
    // flatMap
}









