package repository;

import entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<TicketEntity,Integer> {

    List<TicketEntity> findAllByUserId(Integer id);
}
