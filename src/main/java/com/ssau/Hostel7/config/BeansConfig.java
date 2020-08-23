package com.ssau.Hostel7.config;

import com.ssau.Hostel7.model.enumModel.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class BeansConfig {

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        String stringRepresentation = String.format(
                "%s > %s > %s > %s > %s > %s > %s",
                Role.Admin.getPrefixedValue(),
                Role.Commandant.getPrefixedValue(),
                Role.Staff.getPrefixedValue(),
                Role.SettleStaff.getPrefixedValue(),
                Role.Resident.getPrefixedValue(),
                Role.SettlingInDorms.getPrefixedValue(),
                Role.Registered.getPrefixedValue()
        );

        hierarchy.setHierarchy(stringRepresentation);
        return hierarchy;
    }

}
