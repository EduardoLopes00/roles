package com.codechallenge.roles.utils.mockObjects;

import com.codechallenge.roles.data.models.Role;

import java.util.UUID;

public class RoleMocks {
    public static final UUID TESTER_UUID = UUID.fromString("cb2d11d1-e863-437a-9a24-81193b2b5853");
    public static final UUID DEVELOPER_UUID = UUID.fromString("3b75ca86-810a-477a-9424-9b5dc0e6fe59");
    public static final UUID PRODUCT_OWNER_UUID = UUID.fromString("a10ef22b-cb0d-4314-94e9-311508ba9e5b");

    public static Role DEVELOPER_ROLE() {
        return Role.builder()
                .id(DEVELOPER_UUID)
                .name("Developer").build();
    }

    public static Role PRODUCT_OWNER_ROLE() {
        return Role.builder()
                .id(PRODUCT_OWNER_UUID)
                .name("Product Owner").build();
    }

    public static Role TESTER_ROLE() {
        return Role.builder()
                .id(TESTER_UUID)
                .name("Tester").build();
    }
}
