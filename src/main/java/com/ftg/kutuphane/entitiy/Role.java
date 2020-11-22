package com.ftg.kutuphane.entitiy;

import com.ftg.kutuphane.enums.RoleId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Builder
@Entity
@Table(name = "role", schema = "\"kutuphane_schema\"")
public class Role {
    @Id
    @Column(name = "id")
    @NotEmpty(message = "Role id must not be empty")
    @NotNull(message = "Id must not be null")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Role name must not be empty")
    @NotNull(message = "Name must not be null")
    private String name;

    public Role(RoleId roleId, @NotEmpty(message = "Role name must not be empty") String name) {
        this.id = roleId.getRoleId();
        this.name = name;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(RoleId roleId) {
        this.id = roleId.getRoleId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
