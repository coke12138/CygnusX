package ServerTools;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TIMEer {
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
					 format2 = new SimpleDateFormat(" (yyyy/MM/dd HH:mm:ss).");

	public String getTIME() {
		return format2.format(new Date());
	}
}
