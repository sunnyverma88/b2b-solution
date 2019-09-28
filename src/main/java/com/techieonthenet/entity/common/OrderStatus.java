package com.techieonthenet.entity.common;


public enum OrderStatus {

    PENDING_APPROVAL,
    APPROVED_PENDING_SHIPMENT,
    SHIPPED,
    DELIVERED;

    @Override
    public String toString() {
        return super.toString();
    }
}
