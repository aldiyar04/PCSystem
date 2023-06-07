package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.Motherboard;
import kz.iitu.pcsystem.entity.PowerSupply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply, UUID>, JpaSpecificationExecutor<PowerSupply> {
    @Transactional
    @Query("SELECT c FROM PowerSupply c LEFT JOIN c.products p GROUP BY c.iid ORDER BY COUNT(p) DESC")
    Page<PowerSupply> findAllSortedByProductCountDesc(Pageable pageable);
}
