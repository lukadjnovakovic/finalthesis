package repository;

import entity.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepo extends JpaRepository<BetEntity,Integer> {

    List<BetEntity> findAllByTicket(int id);
}
