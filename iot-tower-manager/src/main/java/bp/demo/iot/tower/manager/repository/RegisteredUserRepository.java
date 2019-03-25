package bp.demo.iot.tower.manager.repository;

import bp.demo.iot.tower.manager.repository.dao.RegisteredUserDAO;
import bp.demo.iot.tower.manager.repository.dao.pk.RegisteredUserKeyDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegisteredUserRepository
  extends CassandraRepository<RegisteredUserDAO, RegisteredUserKeyDAO> {

  @Query("SELECT * FROM IotTowerSpace.Registered_User " +
          "WHERE carrier_name=?0 AND user_id=?1 LIMIT 1")
  List<RegisteredUserDAO> findByCarrierUser(String carrierName,
                                                UUID userId);
}
