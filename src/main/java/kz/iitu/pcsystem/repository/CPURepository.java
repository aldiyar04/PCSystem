package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.CPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CPURepository extends JpaRepository<CPU, String> {
}
