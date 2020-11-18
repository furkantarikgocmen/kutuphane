package com.ftg.kutuphane.entitiy;

import com.ftg.kutuphane.enums.RoleId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

//@Builder
@Entity
@Table(name = "role", schema = "\"kutuphane_schema\"")
public class Role {
    @Id
    @NotEmpty(message = "Role id must not be empty")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Role name must not be empty")
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
