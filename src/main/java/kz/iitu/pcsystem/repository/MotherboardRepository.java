package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.Motherboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, UUID>, JpaSpecificationExecutor<Motherboard> {
    @Transactional
    @Query("SELECT c FROM Motherboard c LEFT JOIN c.products p GROUP BY c.iid ORDER BY COUNT(p) DESC")
    Page<Motherboard> findAllSortedByProductCountDesc(Pageable pageable);

    @Query("SELECT m FROM Motherboard m WHERE m.id = :customId")
    Optional<Motherboard> findByCustomId(String customId);
}

