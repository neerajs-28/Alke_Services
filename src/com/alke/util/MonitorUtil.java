package com.alke.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MonitorUtil {

	
	public static final Map<Integer, String> STATUS ;
	
	static {
        Map<Integer, String> status = new HashMap<Integer, String>();
        status.put(1, "NOT STARTED");
        status.put(2, "IN PROGRESS");
        status.put(3, "COMPLETED");
        STATUS = Collections.unmodifiableMap(status);
    }
	
}
