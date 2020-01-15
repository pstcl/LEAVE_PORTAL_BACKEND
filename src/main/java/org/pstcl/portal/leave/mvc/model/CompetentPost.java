package org.pstcl.portal.leave.mvc.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


//This data will be picked from Cadre, Posting transfer portal.
//Both the post and incumbent Employee will be recieved.
//Meanwhile the fields are dummy;

@Data
@Document
public class CompetentPost {
	
	private Integer id;

	private Employee postedEmployee;
	
}
