package com.egjarabo.jpanplusone.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PurchaseOrderFetchJoinPerformanceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    private Statistics stats;

    @BeforeEach
    void setup() {
        // Obtenemos el objeto de estadísticas una sola vez
        Session session = entityManager.unwrap(Session.class);
        this.stats = session.getSessionFactory().getStatistics();
        this.stats.setStatisticsEnabled(true);
    }

    @Test
    void analyzeFetchJoinPerformance() {
        // 1. Limpiamos métricas anteriores
        stats.clear();

        // 2. Ejecutamos y medimos tiempo
        long startTime = System.nanoTime();
        List<PurchaseOrderResponse> results = purchaseOrderService.findAllWithFetchJoin();
        long endTime = System.nanoTime();

        // 3. Extraemos los datos tratados
        long queryCount = stats.getPrepareStatementCount();
        double totalTimeMs = (endTime - startTime) / 1_000_000.0;

        // 4. Reporte por consola formateado
        printReport("findAllWithFetchJoin", queryCount, totalTimeMs, results.size());
    }

    private void printReport(String method, long queries, double time, int items) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ANÁLISIS DE RENDIMIENTO: " + method);
        System.out.println("=".repeat(60));
        System.out.println(String.format("Elementos procesados     : %d", items));
        System.out.println(String.format("Consultas SQL ejecutadas : %d", queries));
        System.out.println(String.format("Tiempo total en DB       : %.4f ms", time));

        if (queries > 1) {
            System.out.println("\n *** ALERTA DE RENDIMIENTO: ****");
            System.out.println(String.format("Se han detectado %d consultas adicionales (N+1).", queries - 1));
            System.out.println("Cada consulta extra añade latencia de red en producción.");
        } else {
            System.out.println("\n RENDIMIENTO ÓPTIMO: Se ha recuperado todo en una sola query.");
        }
        System.out.println("=".repeat(60) + "\n");
    }
}
