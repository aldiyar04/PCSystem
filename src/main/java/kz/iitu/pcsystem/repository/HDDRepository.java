package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.HDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HDDRepository extends JpaRepository<HDD, String> {
}
