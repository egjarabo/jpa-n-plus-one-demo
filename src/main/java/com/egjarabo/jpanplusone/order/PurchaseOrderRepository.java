package com.egjarabo.jpanplusone.order;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("select po from PurchaseOrder po")
    List<PurchaseOrder> findAllNaive();

    @Query("select po from PurchaseOrder po join fetch po.customer")
    List<PurchaseOrder> findAllWithCustomerFetchJoin();

    @EntityGraph(attributePaths = "customer")
    @Query("select po from PurchaseOrder po")
    List<PurchaseOrder> findAllWithCustomerEntityGraph();
}
