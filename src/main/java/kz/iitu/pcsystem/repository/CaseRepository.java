package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface CaseRepository extends JpaRepository<Case, UUID>, JpaSpecificationExecutor<Case> {
    @Transactional
    @Query("SELECT c FROM Case c LEFT JOIN c.products p GROUP BY c.iid ORDER BY COUNT(p) DESC")
    Page<Case> findAllSortedByProductCountDesc(Pageable pageable);
}
