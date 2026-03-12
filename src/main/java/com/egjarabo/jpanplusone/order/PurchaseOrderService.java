package com.egjarabo.jpanplusone.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public List<PurchaseOrderResponse> findAllNaive() {
        return purchaseOrderRepository.findAllNaive()
                .stream()
                .map(order -> new PurchaseOrderResponse(
                        order.getId(),
                        order.getDescription(),
                        order.getCustomer().getName()
                ))
                .toList();
    }

    public List<PurchaseOrderResponse> findAllWithFetchJoin() {
        return purchaseOrderRepository.findAllWithCustomerFetchJoin()
                .stream()
                .map(order -> new PurchaseOrderResponse(
                        order.getId(),
                        order.getDescription(),
                        order.getCustomer().getName()
                ))
                .toList();
    }

    public List<PurchaseOrderResponse> findAllWithEntityGraph() {
        return purchaseOrderRepository.findAllWithCustomerEntityGraph()
                .stream()
                .map(order -> new PurchaseOrderResponse(
                        order.getId(),
                        order.getDescription(),
                        order.getCustomer().getName()
                ))
                .toList();
    }
}
