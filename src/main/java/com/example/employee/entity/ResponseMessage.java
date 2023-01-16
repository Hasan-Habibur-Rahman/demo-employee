package com.example.employee.entity;

public enum ResponseMessage {
    SUCCESSFULLY_SAVED("Successfully Saved"),
    SUCCESSFULLY_UPDATED("Successfully Updated"),
    SUCCESSFULLY_DELETED("Successfully Deleted"),
    UPDATE_FAILED("Employee Update Failed"),
    NOT_FOUND("Not Found"),
    ENTITY_RETRIEVED_SUCCESS("Object Retrieved Successfully");

    private final String LABEL;

    ResponseMessage(final String LABEL) {
        this.LABEL = LABEL;
    }

    @Override
    public String toString() {
        return LABEL;
    }
}
