package bp.demo.iot.tower.manager.repository;

import bp.demo.iot.tower.manager.repository.dao.TowerCarrierPlatformPolicyRuleDAO;
import bp.demo.iot.tower.manager.repository.dao.pk.TowerCarrierPlatformPolicyRuleKeyDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TowerCarrierPlatformPolicyRuleRepository
        extends CassandraRepository<TowerCarrierPlatformPolicyRuleDAO, TowerCarrierPlatformPolicyRuleKeyDAO> {

  @Query("SELECT * FROM IotTowerSpace.Tower_Carrier_Platform_Policy_Rule " +
          "WHERE tower_name=?0 AND carrier_name=?1 " + "AND platform_name=?2")
  List<TowerCarrierPlatformPolicyRuleDAO> findByCompositePrimaryKey(final String tower_name, final String carrier_name, final String platform_name);

}
