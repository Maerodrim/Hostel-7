package com.ssau.Hostel7.model.enumModel;

import com.ssau.Hostel7.constants.RoleNames;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Role {

    Admin(RoleNames.AdminRoleName, RoleNames.AdminRoleNamePrefixed),

    Commandant(RoleNames.CommandantRoleName, RoleNames.CommandantRoleNamePrefixed),

    Staff(RoleNames.StaffRoleName, RoleNames.StaffRoleNamePrefixed),

    SettleStaff(RoleNames.SettleStaffRoleName, RoleNames.SettleStaffRoleNamePrefixed),

    Resident(RoleNames.ResidentRoleName, RoleNames.ResidentRoleNamePrefixed),

    SettlingInDorms(RoleNames.SettlingInDormsRoleName, RoleNames.SettlingInDormsRoleNamePrefixed),

    Registered(RoleNames.RegisteredRoleName, RoleNames.RegisteredRoleNamePrefixed);

    @Getter
    private final String value;

    @Getter
    private final String prefixedValue;

}
