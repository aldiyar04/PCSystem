package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, String> {
}
