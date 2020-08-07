package com.ssau.Hostel7.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.helper.MigrationHelper;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.repository.*;
import com.ssau.Hostel7.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("ResidentController")
public class ResidentController {

    private HostelResidentRepository hostelResidentRepository;
    private SettlingInDormsRepository settlingInDormsRepository;
    private CheckInQueueRepository checkInQueueRepository;
    private MigrationHelper migrationHelper = new MigrationHelper();

    Logger log = LoggerFactory.getLogger(ResidentController.class);

    @Autowired
    private ResidentController(HostelResidentRepository hostelResidentRepository,
                               SettlingInDormsRepository settlingInDormsRepository,
                               CheckInQueueRepository checkInQueueRepository) {
        this.hostelResidentRepository = hostelResidentRepository;
        this.settlingInDormsRepository = settlingInDormsRepository;
        this.checkInQueueRepository = checkInQueueRepository;
    }


    @JsonView(View.HostelResident.class)
    @PostMapping("residentRegistration")
    public HostelResident residentRegistration(@RequestParam SettlingInDorms settlingInDorms, @RequestParam String studentIDNumber,
                                               @RequestParam String сontractNumber, @RequestParam Integer numberRoom,
                                               @RequestParam Integer numberHostel) {
        HostelResident hostelResident = new HostelResident(
                null,
                settlingInDorms.getName(),
                settlingInDorms.getSurname(),
                settlingInDorms.getPatronymic(),
                settlingInDorms.getLogin(),
                settlingInDorms.getGender(),
                Role.Resident,
                studentIDNumber,
                сontractNumber,
                settlingInDorms.getGroupNumber(),
                settlingInDorms.getPassword()
        );

        log.trace("Registration resident {}",hostelResident);

        hostelResident = hostelResidentRepository.save(hostelResident);

        CheckInQueue checkInQueue = checkInQueueRepository.findBySettlingInDorms(settlingInDorms);
        checkInQueue.setStatus(true);
        checkInQueueRepository.save(checkInQueue);

        migrationHelper.startMigration(hostelResident.getIdHostelResident(),numberRoom,numberHostel);

        settlingInDorms.setStatus(Status.Occupied);
        settlingInDormsRepository.save(settlingInDorms);

        return hostelResident;
    }
}
