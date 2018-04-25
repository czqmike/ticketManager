package cn.edu.hbue.util;

import java.time.format.DateTimeFormatter;

/**
 * @author czqmike
 * 共有的DateTimeFormatter，用于格式化LocalDateTime
 */
public class DateUtil {
	final public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
}
