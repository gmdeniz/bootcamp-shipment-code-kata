package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public ShipmentSize getNextSize() {
        int ordinal = this.ordinal();
        if (ordinal < ShipmentSize.values().length - 1) {
            return ShipmentSize.values()[ordinal + 1];
        }
        else {
            return this; // returns itself if it's the largest size
        }
    }
}
