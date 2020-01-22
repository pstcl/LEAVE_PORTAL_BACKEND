package org.pstcl.portal.leave.mvc.controller;

import java.util.List;

import org.apache.coyote.Response;


import org.pstcl.portal.leave.mvc.model.LeaveApplication;
import org.pstcl.portal.leave.mvc.model.LeaveApplicationStatusModel;
import org.pstcl.portal.leave.mvc.model.LeaveStatus;
import org.pstcl.portal.leave.mvc.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/leave-application")
public class LeaveApplicationController {

	@Autowired
	LeaveApplicationService service;

	@GetMapping
	@CrossOrigin(allowCredentials="true")
	public ResponseEntity< List<LeaveApplication>> getAllLeaveApplications() {
		List<LeaveApplication> list = service.getAllLeaveApplications();

		return new ResponseEntity<List<LeaveApplication>>(list, new org.springframework.http.HttpHeaders(), org.springframework.http.HttpStatus.OK);
	}

	@GetMapping("/status/{id}")
	@CrossOrigin(allowCredentials="true")
	public ResponseEntity<LeaveApplicationStatusModel> getLeaveApplicationStatusById(@PathVariable("id") String id) 
	{
		LeaveApplicationStatusModel entity = service.getLeaveApplicationStatus(id);

		return new ResponseEntity<LeaveApplicationStatusModel>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	@PutMapping("/status/{id}")
	@CrossOrigin(allowCredentials="true")
	public ResponseEntity<LeaveApplicationStatusModel> updateLeaveApplicationStatus(@PathVariable("id") String leaveApplicationId,@RequestBody LeaveStatus leaveStatus)
	{
		LeaveApplicationStatusModel entity= service.updateLeaveApplicationStatus(leaveApplicationId,leaveStatus);   
		return new ResponseEntity<LeaveApplicationStatusModel>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	
	
	@GetMapping("/{id}")
	@CrossOrigin(allowCredentials="true")
	public ResponseEntity<LeaveApplication> getLeaveApplicationById(@PathVariable("id") String id) 
	{
		LeaveApplication entity = service.getLeaveApplication(id);

		return new ResponseEntity<LeaveApplication>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(allowCredentials="true")
	public ResponseEntity<LeaveApplication> createLeaveApplication(@RequestBody LeaveApplication leaveApplication)
	{
		LeaveApplication updated = service.saveNewLeaveApplication(leaveApplication);
		return new ResponseEntity<LeaveApplication>(updated, new HttpHeaders(), HttpStatus.OK);
	}
	@PutMapping("/{id}")
	@CrossOrigin(allowCredentials="true")
	public ResponseEntity<LeaveApplication> updateLeaveApplication(@PathVariable String id,@RequestBody LeaveApplication leaveApplication)
	{
		return service.updateLeaveApplication(id,leaveApplication);           

	}



}
