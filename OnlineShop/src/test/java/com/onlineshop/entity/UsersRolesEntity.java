package com.onlineshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_roles", schema = "onlineshop", catalog = "")
@IdClass(UsersRolesEntityPK.class)
public class UsersRolesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_entity_id")
    private long userEntityId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "roles_id")
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
        UsersRolesEntity that = (UsersRolesEntity) o;
        return userEntityId == that.userEntityId && rolesId == that.rolesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEntityId, rolesId);
    }
}
