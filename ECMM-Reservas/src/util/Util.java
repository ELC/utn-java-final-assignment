package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String convertTimeToString(Date date) {
		DateFormat df = new SimpleDateFormat("hh:mm:ss");
		return df.format(date);
	}
}
