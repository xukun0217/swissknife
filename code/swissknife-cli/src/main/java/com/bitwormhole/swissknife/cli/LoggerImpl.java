package com.bitwormhole.swissknife.cli;

import java.io.PrintStream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;

public class LoggerImpl implements Logger {

	private final PrintStream out;

	public LoggerImpl(PrintStream out) {
		this.out = out;
	}

	interface Prefix {

		String debug = "[DEBUG] ";
		String error = "[ERROR] ";
		String warn_ = "[WARN]  ";
		String trace = "[TRACE] ";
		String info_ = "[INFO]  ";

	}

	@Override
	public void catching(Throwable arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void catching(Level arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Message arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Object arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(String arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Marker arg0, Message arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Marker arg0, Object arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Marker arg0, String arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Message arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Object arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(String arg0, Object... arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(String arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Marker arg0, Message arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Marker arg0, Object arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Marker arg0, String arg1, Object... arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void debug(Marker arg0, String arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void entry() {
		throw new RuntimeException("no impl");

	}

	@Override
	public void entry(Object... arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Message arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Object arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(String arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Marker arg0, Message arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Marker arg0, Object arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Marker arg0, String arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Message arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Object arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(String arg0, Object... arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(String arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Marker arg0, Message arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Marker arg0, Object arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Marker arg0, String arg1, Object... arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void error(Marker arg0, String arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void exit() {
		throw new RuntimeException("no impl");

	}

	@Override
	public <R> R exit(R arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Message arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Object arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(String arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Marker arg0, Message arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Marker arg0, Object arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Marker arg0, String arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Message arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Object arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(String arg0, Object... arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(String arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Marker arg0, Message arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Marker arg0, Object arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Marker arg0, String arg1, Object... arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void fatal(Marker arg0, String arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public Level getLevel() {
		throw new RuntimeException("no impl");

	}

	@Override
	public MessageFactory getMessageFactory() {
		throw new RuntimeException("no impl");

	}

	@Override
	public String getName() {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Message arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Object arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(String text) {
		this.out.println(Prefix.info_ + text);
	}

	@Override
	public void info(Marker arg0, Message arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Marker arg0, Object arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Marker arg0, String arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Message arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Object arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(String arg0, Object... arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(String arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Marker arg0, Message arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Marker arg0, Object arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Marker arg0, String arg1, Object... arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void info(Marker arg0, String arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isDebugEnabled() {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isDebugEnabled(Marker arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isEnabled(Level arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isEnabled(Level arg0, Marker arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isErrorEnabled() {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isErrorEnabled(Marker arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isFatalEnabled() {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isFatalEnabled(Marker arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isInfoEnabled() {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isInfoEnabled(Marker arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isTraceEnabled() {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isTraceEnabled(Marker arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isWarnEnabled() {
		throw new RuntimeException("no impl");

	}

	@Override
	public boolean isWarnEnabled(Marker arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Message arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Object arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, String arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Marker arg1, Message arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Marker arg1, Object arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Marker arg1, String arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Message arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Object arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, String arg1, Object... arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, String arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Marker arg1, Message arg2, Throwable arg3) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Marker arg1, Object arg2, Throwable arg3) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Marker arg1, String arg2, Object... arg3) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void log(Level arg0, Marker arg1, String arg2, Throwable arg3) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void printf(Level arg0, String arg1, Object... arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void printf(Level arg0, Marker arg1, String arg2, Object... arg3) {
		throw new RuntimeException("no impl");

	}

	@Override
	public <T extends Throwable> T throwing(T arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public <T extends Throwable> T throwing(Level arg0, T arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Message arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Object arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(String arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Marker arg0, Message arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Marker arg0, Object arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Marker arg0, String arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Message arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Object arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(String arg0, Object... arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(String arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Marker arg0, Message arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Marker arg0, Object arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Marker arg0, String arg1, Object... arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void trace(Marker arg0, String arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Message arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Object arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(String arg0) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Marker arg0, Message arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Marker arg0, Object arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Marker arg0, String arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Message arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Object arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(String arg0, Object... arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(String arg0, Throwable arg1) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Marker arg0, Message arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Marker arg0, Object arg1, Throwable arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Marker arg0, String arg1, Object... arg2) {
		throw new RuntimeException("no impl");

	}

	@Override
	public void warn(Marker arg0, String arg1, Throwable arg2) {
		throw new RuntimeException("no impl");
	}

}
