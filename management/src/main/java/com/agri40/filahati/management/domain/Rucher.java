package com.agri40.filahati.management.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Rucher.
 */
@Document(collection = "rucher")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Rucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("rucher_name")
    private String rucheName;

    @NotNull
    @Field("adresse")
    private String adresse;

    @NotNull
    @Field("description")
    private String description;

    @NotNull
    @Field("user_id")
    private String userId;

    @Field("list_ruches")
    private List<Ruche> listRuches;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Rucher id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRucheName() {
        return this.rucheName;
    }

    public Rucher rucheName(String rucheName) {
        this.setRucheName(rucheName);
        return this;
    }

    public void setRucheName(String rucheName) {
        this.rucheName = rucheName;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Rucher adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return this.description;
    }

    public Rucher description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return this.userId;
    }

    public Rucher userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Ruche> getListRuches() {
        return this.listRuches;
    }

    /*
    public Rucher listRuches(String listRuches) {

        this.setListRuches(listRuches);
        return this;
    }
*/
    public void setListRuches(List<Ruche> listRuches) {
        this.listRuches = listRuches;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rucher)) {
            return false;
        }
        return id != null && id.equals(((Rucher) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Rucher{" +
            "id=" + getId() +
            ", rucheName='" + getRucheName() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", description='" + getDescription() + "'" +
            ", userId='" + getUserId() + "'" +
            ", listRuches='" + getListRuches() + "'" +
            "}";
    }
}
