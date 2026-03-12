package com.egjarabo.jpanplusone.order;

public record PurchaseOrderResponse(
        Long id,
        String description,
        String customerName
) {
}
