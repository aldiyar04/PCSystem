package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.image.ImageProjection;
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

    @Query("SELECT c.image FROM CPU c WHERE c.id = :id")
    ImageProjection findImageById(String id);

    @Query("SELECT c FROM CPU c JOIN FETCH c.image")
    List<CPU> findAllWithImage();
}
