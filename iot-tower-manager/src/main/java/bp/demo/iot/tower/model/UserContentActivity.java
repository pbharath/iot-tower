package bp.demo.iot.tower.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserContentActivity
  implements Serializable {

  private UUID userId;

  private String carrierName;

  private String platformName;

  private String longitude;

  private String latitude;

  private String content_type;

  private String deviceId;

  public UserContentActivity() {
  }

  public UserContentActivity(UUID userId, String carrierName, String platformName,
                             String longitude, String latitude,
                             String content_type, String deviceId) {
    this.userId = userId;
    this.carrierName = carrierName;
    this.platformName = platformName;
    this.longitude = longitude;
    this.latitude = latitude;
    this.content_type = content_type;
    this.deviceId = deviceId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getContent_type() {
    return content_type;
  }

  public void setContent_type(String content_type) {
    this.content_type = content_type;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserContentActivity that = (UserContentActivity) o;
    return getUserId().equals(that.getUserId()) &&
            getCarrierName().equals(that.getCarrierName()) &&
            getPlatformName().equals(that.getPlatformName()) &&
            getLongitude().equals(that.getLongitude()) &&
            getLatitude().equals(that.getLatitude()) &&
            getContent_type().equals(that.getContent_type()) &&
            getDeviceId().equals(that.getDeviceId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getCarrierName(), getPlatformName(),
            getLongitude(), getLatitude(), getContent_type(), getDeviceId());
  }

  @Override
  public String toString() {
    return "UserContentActivity{" +
            "userId=" + userId +
            ", carrierName='" + carrierName + '\'' +
            ", platformName='" + platformName + '\'' +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", content_type='" + content_type + '\'' +
            ", deviceId='" + deviceId + '\'' + '}';
  }
}
