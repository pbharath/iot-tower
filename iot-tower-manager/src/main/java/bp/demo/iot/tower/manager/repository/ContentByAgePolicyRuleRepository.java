package bp.demo.iot.tower.manager.repository;

import bp.demo.iot.tower.manager.repository.dao.ContentByAgePolicyRuleDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContentByAgePolicyRuleRepository
        extends CassandraRepository<ContentByAgePolicyRuleDAO, UUID> {

  @Query("SELECT * FROM IotTowerSpace.Content_By_Age_Policy_Rule " +
          "WHERE start_age<=?0 AND end_age>=?1 LIMIT 1")
  List<ContentByAgePolicyRuleDAO> findByAgeRange(int startAge, int endAge);
}
