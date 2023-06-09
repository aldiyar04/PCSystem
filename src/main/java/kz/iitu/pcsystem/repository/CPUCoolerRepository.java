package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.CPUCooler;
import kz.iitu.pcsystem.entity.Motherboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CPUCoolerRepository extends JpaRepository<CPUCooler, String>, JpaSpecificationExecutor<CPUCooler> {
    @Transactional
    @Query("SELECT c FROM CPUCooler c LEFT JOIN c.products p GROUP BY c.id ORDER BY COUNT(p) DESC")
    Page<CPUCooler> findAllSortedByProductCountDesc(Pageable pageable);
}
