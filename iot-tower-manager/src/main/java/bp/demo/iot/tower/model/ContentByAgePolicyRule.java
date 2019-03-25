package bp.demo.iot.tower.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ContentByAgePolicyRule
        implements Serializable {

  private UUID id;

  private int startAge;

  private int endAge;

  private Set<String> contentSet;

  public ContentByAgePolicyRule() {
  }

  public ContentByAgePolicyRule(UUID id, int startAge, int endAge,
                                Set<String> contentSet) {
    this.id = id;
    this.startAge = startAge;
    this.endAge = endAge;
    this.contentSet = contentSet;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getStartAge() {
    return startAge;
  }

  public void setStartAge(int startAge) {
    this.startAge = startAge;
  }

  public int getEndAge() {
    return endAge;
  }

  public void setEndAge(int endAge) {
    this.endAge = endAge;
  }

  public Set<String> getContentSet() {
    return contentSet;
  }

  public void setContentSet(Set<String> contentSet) {
    this.contentSet = contentSet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContentByAgePolicyRule that = (ContentByAgePolicyRule) o;
    return getStartAge() == that.getStartAge() &&
            getEndAge() == that.getEndAge() &&
            getId().equals(that.getId()) &&
            getContentSet().equals(that.getContentSet());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getStartAge(), getEndAge(), getContentSet());
  }

  @Override
  public String toString() {
    return "ContentByAgePolicyRule{" +
            "id=" + id +
            ", startAge=" + startAge +
            ", endAge=" + endAge +
            ", contentSet=" + contentSet + '}';
  }

}
