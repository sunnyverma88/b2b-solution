package com.techieonthenet.entity.common;


/**
 * The enum Order status.
 */
public enum OrderStatus {

    /**
     * Pending approval order status.
     */
    PENDING_APPROVAL,
    /**
     * Approved pending shipment order status.
     */
    APPROVED_PENDING_SHIPMENT,
    /**
     * Shipped order status.
     */
    SHIPPED,

    REJECTED,
    /**
     * Delivered order status.
     */
    DELIVERED;

    @Override
    public String toString() {
        return super.toString();
    }
}
