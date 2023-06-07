package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Query("SELECT p FROM Product p where p.component.id = :componentId and p.componentType = :componentType")
    List<Product> findAllByComponentId(String componentId, String componentType);
}
