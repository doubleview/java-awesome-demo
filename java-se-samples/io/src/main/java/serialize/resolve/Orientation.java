package serialize.resolve;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Orientation
        implements Serializable {
    public static final Orientation HORIZONTAL = new Orientation(1);
    public static final Orientation VERTICAL = new Orientation(2);
    private int value;

    private Orientation(int value) {
        this.value = value;
    }

    // Ϊö��������readResolve()����
    private Object readResolve() throws ObjectStreamException {
        if (value == 1) {
            return HORIZONTAL;
        }
        if (value == 2) {
            return VERTICAL;
        }
        return null;
    }
}
