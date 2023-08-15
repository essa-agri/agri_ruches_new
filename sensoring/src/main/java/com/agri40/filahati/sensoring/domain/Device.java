package com.agri40.filahati.sensoring.domain;

import com.agri40.filahati.sensoring.domain.enumeration.DeviceStatus;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Device.
 */
@Document(collection = "device")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("ruche_id")
    private String rucheId;

    @NotNull
    @Field("status")
    private DeviceStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Device id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Device name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRucheId() {
        return this.rucheId;
    }

    public Device rucheId(String rucheId) {
        this.setRucheId(rucheId);
        return this;
    }

    public void setRucheId(String rucheId) {
        this.rucheId = rucheId;
    }

    public DeviceStatus getStatus() {
        return this.status;
    }

    public Device status(DeviceStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        return id != null && id.equals(((Device) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Device{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", rucheId='" + getRucheId() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
