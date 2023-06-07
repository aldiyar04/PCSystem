package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.CPU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CPURepository extends JpaRepository<CPU, String> {
    @Query("SELECT c FROM CPU c LEFT JOIN c.products p GROUP BY c.id ORDER BY COUNT(p) DESC")
    Page<CPU> findAllSortedByProductCountDesc(Pageable pageable);
}
