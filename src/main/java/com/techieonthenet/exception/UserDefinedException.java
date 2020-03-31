package com.techieonthenet.exception;

/**
 * The type User defined exception.
 */
public class UserDefinedException extends RuntimeException {

    /**
     * The constant ORDER_APPROVAL_LEVEL_1_APPROVER_NOT_PRESENT.
     */
    public static final String ORDER_APPROVAL_LEVEL_1_APPROVER_NOT_PRESENT = "Level 1 Approval not configured for your Group. Please contact administrator.";
    /**
     * The constant ORDER_APPROVAL_LEVEL_2_APPROVER_NOT_PRESENT.
     */
    public static final String ORDER_APPROVAL_LEVEL_2_APPROVER_NOT_PRESENT = "Level 2 Approval not configured for your Group. Please contact administrator.";
    /**
     * The constant UNAUTHORIZED_ACTION.
     */
    public static final String UNAUTHORIZED_ACTION = "Something Went Wrong !! You are not authorized to perform this action. Please contact administrator.";
    /**
     * The constant NO_ACTION_NEEDED.
     */
    public static final String NO_ACTION_NEEDED = "No Action Needed .";
    /**
     * The constant MRP_GREATER_THAN_SELLING_PRICE.
     */
    public static final String MRP_GREATER_THAN_SELLING_PRICE = "MRP Price Greater than Selling Price.";
    public static final String ORDER_STATUS_NOT_APPROVED = "Order Status should be APPROVED to perform this action.";

    public static final String MINIMUM_ITEMS_IN_ORDER = "Order must have 1 Item , Please cancel the order in this case.";


    /**
     * Instantiates a new User defined exception.
     *
     * @param message   the message
     * @param cause     the cause
     * @param errorCode the error code
     */
    public UserDefinedException(String message, Throwable cause, String errorCode) {
        super(message, cause);
    }

    /**
     * Instantiates a new User defined exception.
     *
     * @param message the message
     */
    public UserDefinedException(String message) {
        super(message);
    }
}
