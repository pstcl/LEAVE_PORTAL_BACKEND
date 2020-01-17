package org.pstcl.portal.leave.mvc.controller;

import java.util.List;

import org.apache.coyote.Response;


import org.pstcl.portal.leave.mvc.model.LeaveApplication;
import org.pstcl.portal.leave.mvc.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leaveApplications")
public class LeaveApplicationController {

	@Autowired
    LeaveApplicationService service;
 
    @GetMapping
    public ResponseEntity< List<LeaveApplication>> getAllLeaveApplications() {
        List<LeaveApplication> list = service.getAllLeaveApplications();
 
        return new ResponseEntity<List<LeaveApplication>>(list, new org.springframework.http.HttpHeaders(), org.springframework.http.HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<LeaveApplication> getLeaveApplicationById(@PathVariable("id") String id) 
                                                     {
        LeaveApplication entity = service.getLeaveApplication(id);
 
        return new ResponseEntity<LeaveApplication>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<LeaveApplication> createLeaveApplication(LeaveApplication leaveApplication)
                                                    {
        LeaveApplication updated = service.createLeaveApplication(leaveApplication);
        return new ResponseEntity<LeaveApplication>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<LeaveApplication> updateLeaveApplication(LeaveApplication leaveApplication)
                                                    {
        LeaveApplication updated = service.updateLeaveApplication(leaveApplication);
        return new ResponseEntity<LeaveApplication>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
  
 
}
