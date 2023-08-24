package remoteEJBClient;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import bl.MsgServer.BLserverRemote;

public class ClientApp {

	public ClientApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NamingException {

		System.out.println("client App is started!");

		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080"); // 或者使用你的 WildFly 服务器的主机和端口
		props.put("jboss.naming.client.ejb.context", true);

		InitialContext initContxt = new InitialContext(props); // throws NamingException

		// java:global/remoteEJB/BLserver!bl.MsgServer.BLserverRemote
		String name = "remoteEJB/BLserver!bl.MsgServer.BLserverRemote";
		BLserverRemote bean = (BLserverRemote) initContxt.lookup(name);
		String msg = bean.getMsg();
		System.out.println(msg);
	}

}
