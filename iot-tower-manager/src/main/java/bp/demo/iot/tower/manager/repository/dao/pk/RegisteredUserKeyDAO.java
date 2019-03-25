package bp.demo.iot.tower.manager.repository.dao.pk;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

@PrimaryKeyClass
public class RegisteredUserKeyDAO
  implements Serializable {

  @PrimaryKeyColumn(name = "carrier_name", type = PrimaryKeyType.PARTITIONED)
  private String carrierName;

  @PrimaryKeyColumn(name = "email", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
  private String email;

  public RegisteredUserKeyDAO() {
  }

  public RegisteredUserKeyDAO(String carrierName, String email) {
    this.carrierName = carrierName;
    this.email = email;
  }

  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RegisteredUserKeyDAO that = (RegisteredUserKeyDAO) o;
    return getCarrierName().equals(that.getCarrierName()) &&
            getEmail().equals(that.getEmail());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCarrierName(), getEmail());
  }

  @Override
  public String toString() {
    return "RegisteredUserKeyDAO{" + "carrierName='" + carrierName + '\'' +
            ", email='" + email + '\'' + '}';
  }
}
