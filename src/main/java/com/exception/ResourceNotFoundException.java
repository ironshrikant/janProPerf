package com.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Long fieldValue;

public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue){
//        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
this.resourceName= resourceName;
this.fieldName=fieldName;
this.fieldValue = fieldValue;
}

    @Override
    public String getMessage(){
    return String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue);
    }

}
