package org.pstcl.portal.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LeavePortalApplication {

	//test
	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(LeavePortalApplication.class, args);
//		LeaveTypeRepository leaveTypeRepository= applicationContext.getBean(LeaveTypeRepository.class);
//		LeaveApplicationRepository applicationRepository= applicationContext.getBean(LeaveApplicationRepository.class);
//		List<Leave> list=new ArrayList<Leave>();
//		leaveTypeRepository.findAll().forEach(leaveType->{Leave  leave=new Leave(leaveType);list.add(leave);});
//		applicationRepository.findAll().forEach(leaveApplication->{leaveApplication.setLeaveDetails(list);applicationRepository.save(leaveApplication);});
	}

}
