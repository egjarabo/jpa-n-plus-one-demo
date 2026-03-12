package com.egjarabo.jpanplusone.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/api/orders/naive")
    public List<PurchaseOrderResponse> getOrdersNaive() {
        return purchaseOrderService.findAllNaive();
    }

    @GetMapping("/api/orders/fetch-join")
    public List<PurchaseOrderResponse> getOrdersWithFetchJoin() {
        return purchaseOrderService.findAllWithFetchJoin();
    }

    @GetMapping("/api/orders/entity-graph")
    public List<PurchaseOrderResponse> getOrdersWithEntityGraph() {
        return purchaseOrderService.findAllWithEntityGraph();
    }
}
