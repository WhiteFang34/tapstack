package com.arfsoft.tapstack;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author Matt Ayres
 */
public class TapStackMain {
	public static void main(String[] args) throws Exception {
		configureLogging();

		int port;
		try {
			port = Integer.parseInt(System.getProperty("jetty.port"));
		} catch (RuntimeException e) {
			port = 8080;
		}

		Server webServer = new Server(port);
		webServer.setHandler(createContext());
		webServer.start();
	}

	private static Handler createContext() {
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setResourceBase("src/main/webapp");
		return context;
	}

	private static void configureLogging() {
		Layout layout = new PatternLayout("%d{HH:mm:ss} %-5p %-17c{3} - %m%n");
		Appender appender = new ConsoleAppender(layout);
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.addAppender(appender);
		rootLogger.setLevel(Level.INFO);
	}
}
