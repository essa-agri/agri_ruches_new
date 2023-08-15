package com.agri40.filahati.management.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Ruche.
 */
@Document(collection = "ruche")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Ruche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("ruche_name")
    private String rucheName;

    //@NotNull
    @Field("indentifiant")
    private String indentifiant;

    @NotNull
    @Field("description")
    private String description;

    @Field("rucher_id")
    private String rucherId;

    @Field("device_id")
    private String deviceId;

    public Ruche() {}

    public Ruche(String rucheName, String indentifiant, String description) {
        this.rucheName = rucheName;
        this.indentifiant = indentifiant;
        this.description = description;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Ruche id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRucheName() {
        return this.rucheName;
    }

    public Ruche rucheName(String rucheName) {
        this.setRucheName(rucheName);
        return this;
    }

    public void setRucheName(String rucheName) {
        this.rucheName = rucheName;
    }

    public String getIndentifiant() {
        return this.indentifiant;
    }

    public Ruche indentifiant(String indentifiant) {
        this.setIndentifiant(indentifiant);
        return this;
    }

    public void setIndentifiant(String indentifiant) {
        this.indentifiant = indentifiant;
    }

    public String getDescription() {
        return this.description;
    }

    public Ruche description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRucherId() {
        return this.rucherId;
    }

    public Ruche rucherId(String rucherId) {
        this.setRucherId(rucherId);
        return this;
    }

    public void setRucherId(String rucherId) {
        this.rucherId = rucherId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public Ruche deviceId(String deviceId) {
        this.setDeviceId(deviceId);
        return this;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ruche)) {
            return false;
        }
        return id != null && id.equals(((Ruche) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ruche{" +
            "id=" + getId() +
            ", rucheName='" + getRucheName() + "'" +
            ", indentifiant='" + getIndentifiant() + "'" +
            ", description='" + getDescription() + "'" +
            ", rucherId='" + getRucherId() + "'" +
            ", deviceId='" + getDeviceId() + "'" +
            "}";
    }
}
