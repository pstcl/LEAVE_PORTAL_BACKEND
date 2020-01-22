package org.pstcl.portal.leave.mvc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.pstcl.portal.leave.mvc.model.LeaveApplication;
import org.pstcl.portal.leave.mvc.model.LeaveApplicationStatusModel;
import org.pstcl.portal.leave.mvc.model.LeaveStatus;
import org.pstcl.portal.leave.repository.LeaveApplicationRepository;
import org.pstcl.portal.leave.repository.LeaveStatusRepository;
import org.pstcl.portal.leave.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaveApplicationService {

	@Autowired
	private LeaveApplicationRepository leaveApplicationRepository;
	@Autowired
	private LeaveStatusRepository leaveStatusRepository;

	public LeaveApplication saveLeaveApplication(LeaveApplication leaveApplication) {

		return leaveApplicationRepository.save(leaveApplication);
	}

	public LeaveApplication getLeaveApplication(String leaveApplicationId)
	{
		LeaveApplication leaveApplication = null;
		if(null==leaveApplicationId)
		{
			leaveApplication=new LeaveApplication();
		} else {
			Optional<LeaveApplication> findById = leaveApplicationRepository.findById(leaveApplicationId);
			if(findById.isPresent())
			{
				leaveApplication= findById.get();

			}
		}


		return leaveApplication;
	}

	public List<LeaveApplication> getAllLeaveApplications() {
		return leaveApplicationRepository.findAll();
	}

	public LeaveApplication saveNewLeaveApplication(LeaveApplication leaveApplication) {

		LocalDate localDate=LocalDate.now();
		leaveApplication.setId(leaveApplication.getEmployee().getEmpId()+"_"+localDate.getYear()+"_"+localDate.getMonthValue()+"_"+localDate.getDayOfMonth());
		setNewLeaveApplicationStatus(leaveApplication);
		return leaveApplicationRepository.save(leaveApplication);
	}

	private void setNewLeaveApplicationStatus(LeaveApplication leaveApplication) {
		LeaveStatus leaveStatus=LeaveStatus.LeaveStatusSaved(leaveApplication);
		leaveApplication.setStatus(leaveStatus);
		leaveStatusRepository.save(leaveStatus);
	}

	public ResponseEntity<LeaveApplication> updateLeaveApplication(String id, LeaveApplication leaveApplication) {

		ResponseEntity<LeaveApplication> responseEntity=null;

		if(id.compareToIgnoreCase(leaveApplication.getId())==0)
		{

			if(null==leaveApplication.getStatus()||leaveApplication.getStatus().getStatusValue().compareTo(GlobalConstants.STATUS_VALUE_SAVED)==0)
			{

				leaveApplication=leaveApplicationRepository.save(leaveApplication);
				responseEntity= new ResponseEntity<LeaveApplication>(leaveApplication, new HttpHeaders(), HttpStatus.OK);

			}
			else
			{
				responseEntity=new  ResponseEntity<LeaveApplication>(leaveApplication, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);

			}

		}
		else
		{
			responseEntity=new  ResponseEntity<LeaveApplication>(leaveApplication, new HttpHeaders(), HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	public LeaveApplicationStatusModel getLeaveApplicationStatus(String leaveApplicationId) {
		LeaveApplicationStatusModel leaveApplicationStatusModel=new LeaveApplicationStatusModel();
		LeaveApplication leaveApplication=null;
		List<LeaveStatus> leaveStatusList=null;
		Optional<LeaveApplication> findById = leaveApplicationRepository.findById(leaveApplicationId);
		if(findById.isPresent())
		{
			leaveApplication= findById.get();
			leaveApplicationStatusModel.setLeaveApplication(leaveApplication);
			LeaveStatus leaveStatus= leaveApplication.getStatus();
			if(null!=leaveStatus)
			{
				leaveStatusList=new ArrayList<LeaveStatus>();
				while(null!=leaveStatus)
				{
					leaveStatusList.add(leaveStatus);
					leaveStatus=leaveStatus.getPreviousStatus();
				}
				leaveApplicationStatusModel.setLeaveStatusList(leaveStatusList);
			}



		}

		return leaveApplicationStatusModel;
	}

	@Transactional
	public LeaveApplicationStatusModel updateLeaveApplicationStatus(String leaveApplicationId,
			LeaveStatus leaveStatus) {

		Optional<LeaveApplication> findById = leaveApplicationRepository.findById(leaveApplicationId);
		if(findById.isPresent())
		{
			LeaveApplication leaveApplication= findById.get();
			LeaveStatus leaveStatusNew=LeaveStatus.LeaveStatusForwarded(leaveApplication, leaveApplication.getStatus(),leaveStatus.getMarkedTo());
			leaveStatusNew=leaveStatusRepository.save(leaveStatusNew);
			LeaveStatus leaveStatusOld=leaveApplication.getStatus();
			if(leaveStatusOld.getStatusValue().compareTo(GlobalConstants.STATUS_VALUE_SAVED)==0)
			{
				leaveStatusOld.setStatusValue(GlobalConstants.STATUS_VALUE_SUBMITTED);

			}
			else
			{
				leaveStatusOld.setStatusValue(GlobalConstants.STATUS_VALUE_FORWARDED_BY_THIS_OFFICE);
			}
			leaveStatusRepository.save(leaveStatusOld);
			leaveApplication.setStatus(leaveStatusNew);
			leaveApplicationRepository.save(leaveApplication);

		}
		return getLeaveApplicationStatus(leaveApplicationId);

	}

}
