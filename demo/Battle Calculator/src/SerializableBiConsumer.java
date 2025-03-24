import java.io.Serializable;
import java.util.function.BiConsumer;

public class SerializableBiConsumer<T, U> implements BiConsumer<T, U>, Serializable {
    private static final long serialVersionUID = 1L;
    private final BiConsumer<T, U> original;

    public SerializableBiConsumer(BiConsumer<T, U> original) {
        this.original = original;
    }

    @Override
    public void accept(T t, U u) {
        original.accept(t, u);
    }
}
