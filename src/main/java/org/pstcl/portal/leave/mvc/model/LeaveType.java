package org.pstcl.portal.leave.mvc.model;

import org.pstcl.portal.leave.config.id.exposure.IdExposable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@IdExposable(name="LeaveType")
@Data
@Document(collection = "leave_type")
public class LeaveType {
	@Id
	private Integer id;
	private String leaveTypeName;
//	private CompetentPost competentPost;
}
