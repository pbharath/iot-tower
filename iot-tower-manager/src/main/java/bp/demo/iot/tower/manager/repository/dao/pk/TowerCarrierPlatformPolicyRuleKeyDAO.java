package bp.demo.iot.tower.manager.repository.dao.pk;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

@PrimaryKeyClass
public class TowerCarrierPlatformPolicyRuleKeyDAO
        implements Serializable {

  @PrimaryKeyColumn(name = "tower_name", type = PrimaryKeyType.PARTITIONED)
  private String towerName;

  @PrimaryKeyColumn(name = "carrier_name", ordinal = 0)
  private String carrierName;

  @PrimaryKeyColumn(name = "platform_name", ordinal = 1)
  private String platformName;

  public TowerCarrierPlatformPolicyRuleKeyDAO() {
  }

  public TowerCarrierPlatformPolicyRuleKeyDAO(String towerName, String carrierName, String platformName) {
    this.towerName = towerName;
    this.carrierName = carrierName;
    this.platformName = platformName;
  }

  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getTowerName() {
    return towerName;
  }

  public void setTowerName(String towerName) {
    this.towerName = towerName;
  }

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TowerCarrierPlatformPolicyRuleKeyDAO that = (TowerCarrierPlatformPolicyRuleKeyDAO) o;
    return getTowerName().equals(that.getTowerName()) &&
            getCarrierName().equals(that.getCarrierName()) &&
            getPlatformName().equals(that.getPlatformName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTowerName(), getCarrierName(), getPlatformName());
  }

  @Override
  public String toString() {
    return "TowerCarrierPlatformPolicyRuleKeyDAO{" +
            "carrierName='" + carrierName + '\'' +
            ", towerName='" + towerName + '\'' +
            ", platformName='" + platformName + '\'' + '}';
  }
}