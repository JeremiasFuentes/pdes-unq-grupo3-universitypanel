package pdes.c1.universitypanel.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, String attribute, Object value) {
        super(String.format("%s not found with %s : '%s'", resource, attribute, value));
    }
}
