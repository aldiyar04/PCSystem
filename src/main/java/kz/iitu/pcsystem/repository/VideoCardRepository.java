package kz.iitu.pcsystem.repository;

import kz.iitu.pcsystem.entity.VideoCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoCardRepository extends JpaRepository<VideoCard, String> {
}
