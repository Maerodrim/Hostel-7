package com.ssau.Hostel7.service.servceImpl;

import com.ssau.Hostel7.constants.RoleNames;
import com.ssau.Hostel7.dto.request.RegisterResidentRequestDto;
import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;
import com.ssau.Hostel7.exception.AlreadySettledException;
import com.ssau.Hostel7.exception.EntityNotFoundException;
import com.ssau.Hostel7.helper.DtoUtils;
import com.ssau.Hostel7.helper.holders.ErrorMessagesHolder;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.repository.CheckInQueueRepository;
import com.ssau.Hostel7.repository.HostelResidentRepository;
import com.ssau.Hostel7.repository.ProfileRepository;
import com.ssau.Hostel7.repository.RoomRepository;
import com.ssau.Hostel7.repository.SettlingInDormsRepository;
import com.ssau.Hostel7.service.HostelResidentService;
import com.ssau.Hostel7.service.MigrationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HostelResidentServiceImpl implements HostelResidentService {

    private final HostelResidentRepository hostelResidentRepository;

    private final CheckInQueueRepository checkInQueueRepository;

    private final SettlingInDormsRepository settlingInDormsRepository;

    private final RoomRepository roomRepository;

    private final ProfileRepository profileRepository;

    private final MigrationService migrationService;

    private final DtoUtils dtoUtils;

    private final ErrorMessagesHolder errorMessages;

    private final RoleHierarchy roleHierarchy;

    private final Logger logger = LoggerFactory.getLogger(HostelResidentServiceImpl.class);

    @Override
    @Transactional
    @PreAuthorize("hasRole('" + RoleNames.StaffRoleNamePrefixed + "')")
    public HostelResidentResponseDto registerResident(RegisterResidentRequestDto dto, String roomId) {
        UUID settlerId = UUID.fromString(dto.getSettlerId());
        Optional<SettlingInDorms> settlerOpt = settlingInDormsRepository.findById(settlerId);
        SettlingInDorms settler = settlerOpt.orElseThrow(() ->
                new EntityNotFoundException(errorMessages.getEntityNotFound())
        );
        Profile profile = settler.getProfile();
        validateResidentNotExistForProfile(profile);

        UUID roomIdUUID = UUID.fromString(roomId);
        Optional<Room> roomOptional = roomRepository.findById(roomIdUUID);
        Room room = roomOptional.orElseThrow(() ->
                new EntityNotFoundException(errorMessages.getEntityNotFound())
        );

        HostelResident hostelResident = new HostelResident(null, null, dto.getContractIdNumber(), null, null, profile);
        logger.trace("Registration resident {}", hostelResident);

        HostelResident saved = hostelResidentRepository.save(hostelResident);

        CheckInQueue checkInQueue = checkInQueueRepository.findBySettler(settler);
        checkInQueue.setIsSettled(true);
        checkInQueueRepository.save(checkInQueue);

        migrationService.startMigration(saved, room);

        settler.setStatus(Status.Occupied);
        settlingInDormsRepository.save(settler);

        Role profileRole = getProfileRole(profile, Role.Resident);
        profile.setRole(profileRole);
        profileRepository.save(profile);

        return dtoUtils.getHostelResidentResponseDto(saved);
    }

    private void validateResidentNotExistForProfile(Profile profile) {
        Optional<HostelResident> resident = hostelResidentRepository.findByProfile(profile);
        if (resident.isPresent()) {
            throw new AlreadySettledException(errorMessages.getAlreadySettled());
        }
    }

    /**
     * @param profile Профиль, для которого требуется роль.
     * @param role Требуемая роль.
     * @return Возвращает требуемую роль, если она выше той, что уже установлена профилю. В противном случае
     * возвращает текущую роль профиля.
     */
    private Role getProfileRole(Profile profile, Role role) {
        Role profileRole = profile.getRole();
        GrantedAuthority authority = new SimpleGrantedAuthority(profileRole.getPrefixedValue());
        Collection<? extends GrantedAuthority> reachableGrantedAuthorities
                = roleHierarchy.getReachableGrantedAuthorities(Collections.singleton(authority));

        Optional<? extends GrantedAuthority> foundedAuthority = reachableGrantedAuthorities.stream()
                .filter(it -> it.getAuthority().equals(role.getPrefixedValue()))
                .findFirst();

        if (foundedAuthority.isPresent()) {
            return profileRole;
        } else {
            return role;
        }
    }

}
