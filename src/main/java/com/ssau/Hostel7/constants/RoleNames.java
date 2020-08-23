package com.ssau.Hostel7.constants;

import com.ssau.Hostel7.dto.security.CustomUserDetails;

public interface RoleNames {

    String AdminRoleName = "Admin";
    
    String AdminRoleNamePrefixed = CustomUserDetails.ROLE_PREFIX + AdminRoleName;
    
    String CommandantRoleName = "Commandant";
    
    String CommandantRoleNamePrefixed = CustomUserDetails.ROLE_PREFIX + CommandantRoleName;
    
    String StaffRoleName = "Staff";
    
    String StaffRoleNamePrefixed = CustomUserDetails.ROLE_PREFIX + StaffRoleName;
    
    String SettleStaffRoleName = "SettleStaff";
    
    String SettleStaffRoleNamePrefixed = CustomUserDetails.ROLE_PREFIX + SettleStaffRoleName;
    
    String ResidentRoleName = "Resident";
    
    String ResidentRoleNamePrefixed = CustomUserDetails.ROLE_PREFIX + ResidentRoleName;
    
    String SettlingInDormsRoleName = "SettlingInDorms";
    
    String SettlingInDormsRoleNamePrefixed = CustomUserDetails.ROLE_PREFIX + SettlingInDormsRoleName;
    
    String RegisteredRoleName = "Registered";
    
    String RegisteredRoleNamePrefixed = CustomUserDetails.ROLE_PREFIX + RegisteredRoleName;
    
}
