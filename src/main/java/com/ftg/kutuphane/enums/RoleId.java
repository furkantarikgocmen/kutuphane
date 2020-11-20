package com.ftg.kutuphane.enums;

public enum RoleId {
    ADMIN(1),
    MODERATOR(2),
    USER(3);

    private final int roleId;

    RoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
