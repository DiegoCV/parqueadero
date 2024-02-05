package com.nelumbo.parqueadero.util;

import java.util.Arrays;
import java.util.List;

import static com.nelumbo.parqueadero.util.Permission.*;

public enum Role {

    SOCIO(Arrays.asList(CREATE_REGISTERS, READ_PARK, READ_INDICATORS, READ_VEHICULOS)),

    ADMINISTRATOR(Arrays.asList(CREATE_USER, CREATE_PARK, READ_PARK, READ_ALL_PARKS, UPDATE_PARK,
            DELETE_PARK, READ_HISTORY_PARK, SEND_EMAIL, READ_INDICATORS, READ_VEHICULOS));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
