package bp.demo.iot.tower.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class RegisteredUser
        implements Serializable {

  private String carrierName;

  private String towerName;

  private String email;

  private UUID userId;

  private Date dob;

  private boolean active;

  private Date timeStamp;

  public RegisteredUser() {
  }

  public RegisteredUser(String carrierName, String towerName, String email,
                        UUID userId, Date dob, boolean active, Date timeStamp) {
    this.carrierName = carrierName;
    this.towerName = towerName;
    this.email = email;
    this.userId = userId;
    this.dob = dob;
    this.active = active;
    this.timeStamp = timeStamp;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
    RegisteredUser that = (RegisteredUser) o;
    return isActive() == that.isActive() &&
            getCarrierName().equals(that.getCarrierName()) &&
            getTowerName().equals(that.getTowerName()) &&
            getEmail().equals(that.getEmail()) &&
            getUserId().equals(that.getUserId()) &&
            getDob().equals(that.getDob()) &&
            getTimeStamp().equals(that.getTimeStamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCarrierName(), getTowerName(), getEmail(),
            getUserId(), getDob(), isActive(), getTimeStamp());
  }

  @Override
  public String toString() {
    return "RegisteredUser{" + "carrierName='" + carrierName + '\'' + ", " +
            "towerName='" + towerName + '\'' + ", email='" + email + '\'' +
            ", userId=" + userId + ", dob=" + dob + ", active=" + active + "," +
            " timeStamp=" + timeStamp + '}';
  }
}
