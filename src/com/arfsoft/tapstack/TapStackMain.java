package com.arfsoft.tapstack;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.webapp.WebAppContext;

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

	private static Context createContext() {
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setResourceBase(".");
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
