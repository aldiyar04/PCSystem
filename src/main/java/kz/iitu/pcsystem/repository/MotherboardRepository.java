package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, String> {
}
