package com.onlineshop;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UsersRolesEntityPK implements Serializable {
    @Column(name = "user_entity_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userEntityId;
    @Column(name = "roles_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rolesId;

    public long getUserEntityId() {
        return userEntityId;
    }

    public void setUserEntityId(long userEntityId) {
        this.userEntityId = userEntityId;
    }

    public long getRolesId() {
        return rolesId;
    }

    public void setRolesId(long rolesId) {
        this.rolesId = rolesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRolesEntityPK that = (UsersRolesEntityPK) o;
        return userEntityId == that.userEntityId && rolesId == that.rolesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEntityId, rolesId);
    }
}
