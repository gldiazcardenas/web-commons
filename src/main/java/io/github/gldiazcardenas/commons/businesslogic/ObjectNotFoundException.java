package io.github.gldiazcardenas.commons.businesslogic;

public class ObjectNotFoundException extends RuntimeException {

    private final Object objectId;

    public ObjectNotFoundException(Object objectId) {
        super(String.format("Object not found with id '%s'", objectId));
        this.objectId = objectId;
    }

    public Object getObjectId() {
        return objectId;
    }

}
