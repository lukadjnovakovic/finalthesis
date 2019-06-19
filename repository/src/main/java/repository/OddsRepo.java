package repository;

import entity.OddsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OddsRepo extends JpaRepository<OddsEntity,Integer> {

    List<OddsEntity> findAllByGame_Id(int id);
}
