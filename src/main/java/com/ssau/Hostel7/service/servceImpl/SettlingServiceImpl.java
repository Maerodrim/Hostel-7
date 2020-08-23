package com.ssau.Hostel7.service.servceImpl;

import com.ssau.Hostel7.dto.response.CheckInQueueResponseDto;
import com.ssau.Hostel7.dto.response.SettlingResponseDto;
import com.ssau.Hostel7.exception.EntityNotFoundException;
import com.ssau.Hostel7.helper.DtoUtils;
import com.ssau.Hostel7.helper.holders.ErrorMessagesHolder;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.repository.CheckInQueueRepository;
import com.ssau.Hostel7.repository.ProfileRepository;
import com.ssau.Hostel7.repository.SettlingInDormsRepository;
import com.ssau.Hostel7.service.SettlingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettlingServiceImpl implements SettlingService {

    private final SettlingInDormsRepository settlingInDormsRepository;

    private final CheckInQueueRepository checkInQueueRepository;

    private final ProfileRepository profileRepository;

    private final DtoUtils dtoUtils;

    private final ErrorMessagesHolder errorMessagesHolder;

    private final Logger logger = LoggerFactory.getLogger(SettlingServiceImpl.class);

    @Override
    @Transactional
    public SettlingResponseDto saveSettler(String mail) {
        Optional<Profile> profileOpt = profileRepository.findByMail(mail);
        Profile profile = profileOpt.orElseThrow(
                () -> new EntityNotFoundException(errorMessagesHolder.getEntityNotFound())
        );

        SettlingInDorms settler = dtoUtils.getSettling(
                Status.In_line,
                profile
        );

        SettlingInDorms saved = settlingInDormsRepository.save(settler);
        profile.setRole(Role.SettlingInDorms);
        profileRepository.save(profile);

        setInQueue(saved);
        return dtoUtils.getSettlingResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<CheckInQueueResponseDto> getSettlersQueue() {
        //TODO Пагинация!
        HashSet<CheckInQueue> queued = checkInQueueRepository.findByIsSettledIsFalseOrderByTime();

        return queued.stream()
                .map(dtoUtils::getCheckInQueueResponseDto)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public SettlingResponseDto getLastSettlerInQueue() {
        Optional<CheckInQueue> firstQueue = checkInQueueRepository.findFirstByIsSettledIsFalseOrderByTimeDesc();
        if (!firstQueue.isPresent()) {
            return null;
        }
        CheckInQueue checkInQueue = firstQueue.get();

        SettlingInDorms first = checkInQueue.getSettler();
        return dtoUtils.getSettlingResponseDto(first);
    }

    @Override
    @Transactional(readOnly = true)
    public SettlingResponseDto getSettlerById(UUID id) {
        Optional<SettlingInDorms> settlerOpt = settlingInDormsRepository.findById(id);
        SettlingInDorms settler = settlerOpt.orElseThrow(() -> {
            EntityNotFoundException throwed = new EntityNotFoundException(
                    errorMessagesHolder.getEntityNotFound()
            );
            logger.warn("Settler not found", throwed);
            return throwed;
        });

        return dtoUtils.getSettlingResponseDto(settler);
    }

    private CheckInQueue setInQueue(SettlingInDorms settler) {
        CheckInQueue checkInQueue = new CheckInQueue(
                null,
                settler,
                new Date(),
                false
        );
        //TODO возвращать номер в очереди
        return checkInQueueRepository.save(checkInQueue);
    }

}
