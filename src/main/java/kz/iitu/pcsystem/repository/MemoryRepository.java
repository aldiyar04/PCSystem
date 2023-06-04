package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, String> {
}
