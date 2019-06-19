package repository;

import entity.TipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipRepo extends JpaRepository<TipEntity,Integer> {


}
