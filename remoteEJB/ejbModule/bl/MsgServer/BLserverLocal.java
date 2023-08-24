package bl.MsgServer;

import javax.ejb.Local;

@Local
public interface BLserverLocal {
	public String getMsg();
}
