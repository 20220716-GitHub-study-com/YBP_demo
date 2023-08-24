package bl.MsgServer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class BLserver
 */
@Stateless
@LocalBean
public class BLserver implements BLserverRemote, BLserverLocal {

	/**
	 * Default constructor.
	 */
	public BLserver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return "This is a message from Server. at:" + getCurrentDateTime();
	}

	private static String getCurrentDateTime() {
		// 获取当前日期和时间
		LocalDateTime now = LocalDateTime.now();

		// 定义日期时间格式
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// 格式化日期时间为字符串
		String formattedDateTime = now.format(formatter);

		return formattedDateTime;
	}

}
