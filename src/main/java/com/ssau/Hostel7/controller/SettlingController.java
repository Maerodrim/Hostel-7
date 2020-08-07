package com.ssau.Hostel7.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Gender;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.repository.CheckInQueueRepository;
import com.ssau.Hostel7.repository.SettlingInDormsRepository;
import com.ssau.Hostel7.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping("SettlingController")
public class SettlingController {

    private SettlingInDormsRepository settlingInDormsRepository;
    private CheckInQueueRepository checkInQueueRepository;

    Logger log = LoggerFactory.getLogger(SettlingController.class);

    @Autowired
    private SettlingController(SettlingInDormsRepository settlingInDormsRepository,
                               CheckInQueueRepository checkInQueueRepository) {
        this.settlingInDormsRepository = settlingInDormsRepository;
        this.checkInQueueRepository = checkInQueueRepository;
    }


    @JsonView(View.HostelResident.class)
    @PostMapping("registrationSettlingInDorms")
    public SettlingInDorms registrationSettlingInDorms(@RequestParam String groupNumber,
                                                   @RequestParam String name, @RequestParam String surname,
                                                   @RequestParam String patronymic, @RequestParam String password,
                                                   @RequestParam String login, @RequestParam Gender gender) {
        SettlingInDorms settlingInDorms = new SettlingInDorms(null, name, surname,
                patronymic, login, gender, Role.SettlingInDorms,
                Status.In_line, groupNumber, password);

        log.trace("Registration settling in dorms {}",settlingInDorms);

        settlingInDorms = settlingInDormsRepository.save(settlingInDorms);

        CheckInQueue checkInQueue = new CheckInQueue(null, settlingInDorms.getIdSettlingInDorms(),
                Time.valueOf(String.valueOf(LocalDateTime.now())), false);
        checkInQueueRepository.save(checkInQueue);

        return settlingInDorms;
    }

    @JsonView(View.CheckInQueue.class)
    @GetMapping("getQueueSettlingInDorms")
    public HashSet<CheckInQueue> getQueueSettlingInDorms() {
        return checkInQueueRepository.findByStatusFalse();
    }

    @JsonView(View.CheckInQueue.class)
    @GetMapping("getSettlingInDorms")
    public SettlingInDorms getSettlingInDorms(@RequestParam UUID idSettlingInDorms) {
        return settlingInDormsRepository.findByIdSettlingInDorms(idSettlingInDorms);
    }
}
