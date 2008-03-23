package com.mattayres.tapstack.pages;

import java.util.List;

import org.apache.tapestry.ioc.annotations.Inject;
import org.hibernate.Session;

import com.mattayres.tapstack.entities.Hello;

public class HelloAction {
	@Inject
	private Session session;

	private Hello current;

	public void onAction() {
		Hello hello = new Hello();
		hello.setMessage("Hello World");
		session.save(hello);
	}

	@SuppressWarnings("unchecked")
	public List getList() {
		return session.createCriteria(Hello.class).list();
	}

	public Hello getCurrent() {
		return current;
	}

	public void setCurrent(Hello current) {
		this.current = current;
	}
}
