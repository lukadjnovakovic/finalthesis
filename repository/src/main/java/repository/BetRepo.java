package repository;

import entity.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepo extends JpaRepository<BetEntity,Integer> {
}
