package com.ssau.Hostel7.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.repository.HostelResidentRepository;
import com.ssau.Hostel7.repository.SettlingInDormsRepository;
import com.ssau.Hostel7.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ResidentController")
public class ResidentController
{

    private HostelResidentRepository hostelResidentRepository;
    private SettlingInDormsRepository settlingInDormsRepository;

    @Autowired
    private ResidentController(HostelResidentRepository hostelResidentRepository,
                               SettlingInDormsRepository settlingInDormsRepository)
    {
        this.hostelResidentRepository = hostelResidentRepository;
        this.settlingInDormsRepository = settlingInDormsRepository;
    }


    @JsonView(View.HostelResident.class)
    @PostMapping("residentRegistration")
    public void residentRegistration(@RequestParam Integer idSettlingInDorms)
    {
        SettlingInDorms settlingInDorms = settlingInDormsRepository.findByIdSettlingInDorms(idSettlingInDorms);
        HostelResident hostelResident = new HostelResident();
        hostelResidentRepository.save(hostelResident);
        settlingInDorms.setStatus(Status.Occupied);
        settlingInDormsRepository.save(settlingInDorms);
    }
}
