package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply, String> {
}
