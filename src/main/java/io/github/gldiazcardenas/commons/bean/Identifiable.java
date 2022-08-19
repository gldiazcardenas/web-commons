package io.github.gldiazcardenas.commons.bean;

import java.util.Objects;

/**
 * Base object which is identified uniquely by an ID of certain type.
 *
 * @param <ID> The type of ID.
 * @author Gabriel Diaz, 17/12/2020.
 */
abstract class Identifiable<ID> {

    /**
     * The attributes of the object.
     */
    public interface Attributes {
        String ID = "id";
    }

    public abstract ID getId();

    public abstract void setId(ID id);

    @Override
    public int hashCode() {
        return Objects.hashCode((getId() == null) ? 0 : getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Identifiable<?> other = (Identifiable<?>) obj;
        if (getId() == null) {
            return other.getId() == null;
        }

        return getId().equals(other.getId());
    }

}
