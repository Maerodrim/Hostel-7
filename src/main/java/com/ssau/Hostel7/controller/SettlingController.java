package com.ssau.Hostel7.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.repository.CheckInQueueRepository;
import com.ssau.Hostel7.repository.HostelResidentRepository;
import com.ssau.Hostel7.repository.SettlingInDormsRepository;
import com.ssau.Hostel7.view.View;
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
    @Autowired
    private SettlingController(SettlingInDormsRepository settlingInDormsRepository,
                               CheckInQueueRepository checkInQueueRepository)
    {
        this.settlingInDormsRepository = settlingInDormsRepository;
        this.checkInQueueRepository = checkInQueueRepository;
    }


    @JsonView(View.HostelResident.class)
    @PostMapping("residentSettlingInDorms")
    public SettlingInDorms residentSettlingInDorms(@RequestParam String groupNumber,
                                                   @RequestParam String name, @RequestParam String surname,
                                                   @RequestParam String patronymic, @RequestParam String password)
    {
        SettlingInDorms settlingInDorms = new SettlingInDorms(null, name, surname,
                patronymic, Role.SettlingInDorms,
                Status.In_line, groupNumber, password);
        settlingInDorms = settlingInDormsRepository.save(settlingInDorms);

        CheckInQueue checkInQueue = new CheckInQueue(null,settlingInDorms.getIdSettlingInDorms(),
                Time.valueOf(String.valueOf(LocalDateTime.now())),false);
        checkInQueueRepository.save(checkInQueue);

        return settlingInDorms;
    }

    @JsonView(View.CheckInQueue.class)
    @GetMapping("getQueueSettlingInDorms")
    public HashSet<CheckInQueue> getQueueSettlingInDorms()
    {
        return checkInQueueRepository.findByStatusFalse();
    }

    @JsonView(View.CheckInQueue.class)
    @GetMapping("getSettlingInDorms")
    public SettlingInDorms getSettlingInDorms(@RequestParam UUID idSettlingInDorms)
    {
        return settlingInDormsRepository.findByIdSettlingInDorms(idSettlingInDorms);
    }
}
