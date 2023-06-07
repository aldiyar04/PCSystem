package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.SSD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface SSDRepository extends JpaRepository<SSD, UUID>, JpaSpecificationExecutor<SSD> {
    @Transactional
    @Query("SELECT c FROM SSD c LEFT JOIN c.products p GROUP BY c.iid ORDER BY COUNT(p) DESC")
    Page<SSD> findAllSortedByProductCountDesc(Pageable pageable);
}
