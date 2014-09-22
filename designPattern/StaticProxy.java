package designPattern;

import java.util.logging.Level;
import java.util.logging.Logger;

//代理角色
public class StaticProxy implements Hello{
	private Logger _logger = Logger.getLogger(this.getClass().getName());
	private Hello helloImpl;
	
	public StaticProxy(Hello helloImpl){
		this.helloImpl = helloImpl;
	}
	
	public void log(String message){
		_logger.log(Level.INFO, message);
	}
	
	public String greet(String who){
		log("starting");
		String hello = helloImpl.greet(who);
		log("stopping");
		return hello;
	}
}

//抽象角色
interface Hello{
	String greet(String who);
}

//真实角色
class HelloImpl implements Hello{
	public String greet(String who){
		System.out.println("greeting method is invoked...");
		return "hell0,"+who;
	}
}