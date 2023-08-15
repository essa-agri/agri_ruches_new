package com.agri40.filahati.sensoring.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Stream.
 */
@Document(collection = "stream")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Stream implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("device_id")
    private String deviceId;


    @Field("params")
    private Map<String,?> params;

    @NotNull
    @Field("created_at")
    private Long createdAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Stream id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public Stream deviceId(String deviceId) {
        this.setDeviceId(deviceId);
        return this;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }


    public Stream params(Map<String, ?> params) {
        this.setParams(params);
        return this;
    }



    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Stream createdAt(Long createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stream)) {
            return false;
        }
        return id != null && id.equals(((Stream) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stream{" +
            "id=" + getId() +
            ", deviceId='" + getDeviceId() + "'" +
            ", params='" + getParams() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}
