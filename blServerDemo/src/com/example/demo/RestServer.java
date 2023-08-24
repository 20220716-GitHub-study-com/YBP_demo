package com.example.demo;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.jboss.naming.remote.client.InitialContextFactory;

import bl.MsgServer.BLserverRemote;

/**
 * Servlet implementation class RestServer
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RestServer" })
public class RestServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestServer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		System.out.println(request.getRequestURI());

		response.setContentType("text/plain");
//		response.getWriter().write("Hello, World!");
		String remoteMsg = test();
//		String remoteMsg = callEjb();
		response.getWriter().write(remoteMsg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String test() {
		String result = "An internal error has occurred...!";
		try {
			// 设置 JNDI 属性
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080"); // 或者使用您的服务器主机和端口

			InitialContext initialContext = new InitialContext(props);

			// 设置您的 EJB JNDI 名称
			String jndiName = "remoteEJB/BLserver!bl.MsgServer.BLserverRemote";

			// 进行 JNDI 查找获取远程 EJB 接口
			BLserverRemote ejb = (BLserverRemote) initialContext.lookup(jndiName);

			// 调用远程 EJB 的方法
			result = ejb.getMsg();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String callEjb() {
		String remoteMsg = null;
		try {
			remoteMsg = EJBClient.callRemoteEJB("telegram");
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			remoteMsg = e.getMessage();
		}
		return remoteMsg;
	}
}
