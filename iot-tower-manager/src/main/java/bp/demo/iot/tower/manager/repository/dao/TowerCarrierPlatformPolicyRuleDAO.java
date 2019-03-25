package bp.demo.iot.tower.manager.repository.dao;

import bp.demo.iot.tower.manager.repository.dao.pk.TowerCarrierPlatformPolicyRuleKeyDAO;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Objects;

@Table("Tower_Carrier_Platform_Policy_Rule")
public class TowerCarrierPlatformPolicyRuleDAO
        implements Serializable {

  @PrimaryKey
  private TowerCarrierPlatformPolicyRuleKeyDAO keyDAO;

  @Column(value = "is_active")
  private boolean active;

  public TowerCarrierPlatformPolicyRuleDAO() {
  }

  public TowerCarrierPlatformPolicyRuleDAO(TowerCarrierPlatformPolicyRuleKeyDAO keyDAO, boolean active) {
    this.keyDAO = keyDAO;
    this.active = active;
  }

  public TowerCarrierPlatformPolicyRuleKeyDAO getKeyDAO() {
    return keyDAO;
  }

  public void setKeyDAO(TowerCarrierPlatformPolicyRuleKeyDAO keyDAO) {
    this.keyDAO = keyDAO;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TowerCarrierPlatformPolicyRuleDAO that =
            (TowerCarrierPlatformPolicyRuleDAO) o;
    return isActive() == that.isActive() && getKeyDAO().equals(that.getKeyDAO());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKeyDAO(), isActive());
  }

  @Override
  public String toString() {
    return "TowerCarrierPlatformPolicyRuleDAO{" + "keyDAO=" + keyDAO + ", " +
            "active=" + active + '}';
  }
}
