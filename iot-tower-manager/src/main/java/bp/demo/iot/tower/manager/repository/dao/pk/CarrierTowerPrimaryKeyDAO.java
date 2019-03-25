package bp.demo.iot.tower.manager.repository.dao.pk;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

@PrimaryKeyClass
public class CarrierTowerPrimaryKeyDAO
  implements Serializable {

  @PrimaryKeyColumn(name = "carrier_name", type = PrimaryKeyType.PARTITIONED)
  private String carrierName;

  @PrimaryKeyColumn(name = "tower_name", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
  private String towerName;

  public CarrierTowerPrimaryKeyDAO() {
  }

  public CarrierTowerPrimaryKeyDAO(final String carrierName,
                                   final String towerName) {
    this.towerName = towerName;
    this.carrierName = carrierName;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CarrierTowerPrimaryKeyDAO that = (CarrierTowerPrimaryKeyDAO) o;
    return getCarrierName().equals(that.getCarrierName()) &&
            getTowerName().equals(that.getTowerName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCarrierName(), getTowerName());
  }
}
