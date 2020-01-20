package com.techieonthenet.exception;

public class UserDefinedException extends RuntimeException {

    public static final String ORDER_APPROVAL_LEVEL_1_APPROVER_NOT_PRESENT = "Level 1 Approval not configured for your Group. Please contact administrator.";
    public static final String ORDER_APPROVAL_LEVEL_2_APPROVER_NOT_PRESENT = "Level 2 Approval not configured for your Group. Please contact administrator.";
    public static final String UNAUTHORIZED_ACTION = "Something Went Wrong !! You are not authorized to perform this action. Please contact administrator.";
    public static final String NO_ACTION_NEEDED = "No Action Needed .";
    public static final String MRP_GREATER_THAN_SELLING_PRICE = "MRP Greater than Selling Price.";
    private String errorCode;


    public UserDefinedException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public UserDefinedException(String message) {
        super(message);
    }
}
