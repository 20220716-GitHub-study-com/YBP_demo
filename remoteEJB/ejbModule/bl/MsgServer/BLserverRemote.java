package bl.MsgServer;

import javax.ejb.Remote;

@Remote
public interface BLserverRemote {
	public String getMsg();
}
