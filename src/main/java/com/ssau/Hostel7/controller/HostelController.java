package com.ssau.Hostel7.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ssau.Hostel7.constants.UrlsConstants.HOSTEL;

@RestController
@RequestMapping(HOSTEL)
@RequiredArgsConstructor
public class HostelController {

}
