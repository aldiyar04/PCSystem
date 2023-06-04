package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.CPUCooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CPUCoolerRepository extends JpaRepository<CPUCooler, String> {
}
