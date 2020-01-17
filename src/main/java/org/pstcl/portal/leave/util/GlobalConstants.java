package org.pstcl.portal.leave.util;

import java.util.HashMap;
import java.util.Map;

public class GlobalConstants {
	
	public GlobalConstants()
	{
		
	}
	static
	{
		GlobalConstants.map.put(100, "SAVED");
		GlobalConstants.map.put(200, "SUBMITTED");
		GlobalConstants.map.put(300, "TITLE_VERIFICATION_DONE");
		GlobalConstants.map.put(400, "PENDING_APPROVAL_AFTER_TITLE_VERIFICATION");
		GlobalConstants.map.put(1000, "APPROVED");
	}

	public static Map<Integer, String> map = new HashMap<>();
	
}
