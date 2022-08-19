package io.github.gldiazcardenas.commons.bean;

/**
 * Domain bean which defines an object of the business model.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public abstract class Domain<ID> extends Identifiable<ID> {

    /**
     * Domain bean attributes.
     */
    public interface Attributes extends Identifiable.Attributes {

    }

}
