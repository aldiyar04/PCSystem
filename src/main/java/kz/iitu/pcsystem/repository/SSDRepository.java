package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.SSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SSDRepository extends JpaRepository<SSD, String> {
}
