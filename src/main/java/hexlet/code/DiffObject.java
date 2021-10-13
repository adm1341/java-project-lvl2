package hexlet.code;

import java.util.Objects;
import java.util.Optional;

public final class DiffObject {
    private final String key;
    private final String action;
    private final Optional val1;
    private final Optional val2;

    public String getAction() {
        return this.action;
    }

    public String getKey() {
        return this.key;
    }

    public Optional getVal1() {
        return this.val1;
    }

    public Optional getVal2() {
        return this.val2;
    }

    public DiffObject(String keyIn, String actionIn, Optional val1In, Optional val2In) {
        this.key = keyIn;
        this.action = actionIn;
        this.val1 = val1In;
        this.val2 = val2In;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiffObject that = (DiffObject) o;
        boolean b1 = key.equals(that.key) && Objects.equals(action, that.action);
        boolean b2 = Objects.equals(val1, that.val1) && Objects.equals(val2, that.val2);
        return b1 && b2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, action, val1, val2);
    }
}
