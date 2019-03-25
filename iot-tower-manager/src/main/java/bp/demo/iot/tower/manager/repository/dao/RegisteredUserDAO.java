package bp.demo.iot.tower.manager.repository.dao;

import bp.demo.iot.tower.manager.repository.dao.pk.RegisteredUserKeyDAO;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Table("Registered_User")
public class RegisteredUserDAO
  implements Serializable {

  @PrimaryKey
  private RegisteredUserKeyDAO keyDAO;

  @Column(value = "tower_name")
  private String towerName;

  @Column(value = "user_id")
  private UUID userId;

  @Column(value = "date_of_birth")
  private Date dob;

  @Column(value = "is_active")
  private boolean active;

  @Column(value = "latest_timeStamp")
  private Date timeStamp;

  public RegisteredUserDAO() {
  }

  public RegisteredUserDAO(RegisteredUserKeyDAO keyDAO, String towerName,
                           UUID userId, Date dob, boolean active,
                           Date timeStamp) {
    this.keyDAO = keyDAO;
    this.towerName = towerName;
    this.userId = userId;
    this.dob = dob;
    this.active = active;
    this.timeStamp = timeStamp;
  }

  public RegisteredUserKeyDAO getKeyDAO() {
    return keyDAO;
  }

  public void setKeyDAO(RegisteredUserKeyDAO keyDAO) {
    this.keyDAO = keyDAO;
  }

  public String getTowerName() {
    return towerName;
  }

  public void setTowerName(String towerName) {
    this.towerName = towerName;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RegisteredUserDAO that = (RegisteredUserDAO) o;
    return isActive() == that.isActive() && getKeyDAO().equals(that.getKeyDAO()) && getTowerName().equals(that.getTowerName()) && getUserId().equals(that.getUserId()) && getDob().equals(that.getDob()) && getTimeStamp().equals(that.getTimeStamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKeyDAO(), getTowerName(), getUserId(), getDob(),
            isActive(), getTimeStamp());
  }

  @Override
  public String toString() {
    return "RegisteredUserDAO{" + "keyDAO=" + keyDAO + ", towerName='" + towerName + '\'' + ", userId=" + userId + ", dob=" + dob + ", active=" + active + ", timeStamp=" + timeStamp + '}';
  }
}
