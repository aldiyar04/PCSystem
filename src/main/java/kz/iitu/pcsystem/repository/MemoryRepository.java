package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.Memory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, String> {
    @Transactional
    @Query("SELECT c FROM Memory c LEFT JOIN c.products p GROUP BY c.id ORDER BY COUNT(p) DESC")
    Page<Memory> findAllSortedByProductCountDesc(Pageable pageable);
}
