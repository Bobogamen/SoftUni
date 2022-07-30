package com.onlineshop;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses", schema = "onlineshop", catalog = "")
public class AddressesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "address_line")
    private String addressLine;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "town")
    private String town;
    @Basic
    @Column(name = "user_entity_id")
    private Long userEntityId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Long getUserEntityId() {
        return userEntityId;
    }

    public void setUserEntityId(Long userEntityId) {
        this.userEntityId = userEntityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressesEntity that = (AddressesEntity) o;
        return id == that.id && Objects.equals(addressLine, that.addressLine) && Objects.equals(name, that.name) && Objects.equals(town, that.town) && Objects.equals(userEntityId, that.userEntityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressLine, name, town, userEntityId);
    }
}
