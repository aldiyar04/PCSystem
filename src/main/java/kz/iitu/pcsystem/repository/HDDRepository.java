package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.HDD;
import kz.iitu.pcsystem.entity.Motherboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HDDRepository extends JpaRepository<HDD, String>, JpaSpecificationExecutor<HDD> {
    @Transactional
    @Query("SELECT c FROM HDD c LEFT JOIN c.products p GROUP BY c.id ORDER BY COUNT(p) DESC")
    Page<HDD> findAllSortedByProductCountDesc(Pageable pageable);
}
