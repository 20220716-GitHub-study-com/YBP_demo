package com.example.demo;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import bl.MsgServer.BLserverRemote;

public class EJBClient {

	public static String callRemoteEJB(String telegram) throws NamingException {

		// Configure JNDI context properties
		Properties props = new Properties();
//		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080"); // Change if needed
		props.put("jboss.naming.client.ejb.context", true);

		// Create JNDI context
		Context context = new InitialContext(props);

		// Lookup the EJB using JNDI name
//		String jndiName1 = "java:global/remoteEJB/BLserver!bl.MsgServer.BLserver";
//		String jndiName2 = "java:app/remoteEJB/BLserver!bl.MsgServer.BLserver";
//		String jndiName3 = "java:module/BLserver!bl.MsgServer.BLserver";
//		String jndiName4 = "java:global/remoteEJB/BLserver!bl.MsgServer.BLserverRemote";// OK
//		String jndiName5 = "java:app/remoteEJB/BLserver!bl.MsgServer.BLserverRemote";// OK
//		String jndiName6 = "java:module/BLserver!bl.MsgServer.BLserverRemote";
//		String jndiName7 = "java:jboss/exported/remoteEJB/BLserver!bl.MsgServer.BLserverRemote";// OK
//		String jndiName8 = "java:global/remoteEJB/BLserver!bl.MsgServer.BLserverLocal";
//		String jndiName9 = "java:app/remoteEJB/BLserver!bl.MsgServer.BLserverLocal";
//		String jndiName10 = "java:module/BLserver!bl.MsgServer.BLserverLocal";

//		String jndiName = "java:global/remoteEJB/BLserver!bl.MsgServer.BLserverRemote";
//		String jndiName = "java:app/remoteEJB/BLserver!bl.MsgServer.BLserverRemote";
		String jndiName = "java:jboss/exported/remoteEJB/BLserver!bl.MsgServer.BLserverRemote";

		BLserverRemote remoteBean = null;
		remoteBean = (BLserverRemote) context.lookup(jndiName);

		// Call methods on the remote EJB
		String message = remoteBean.getMsg();
		System.out.println("EJB Message: " + message);

		// Close the context
		context.close();

		return message;
	}
}
