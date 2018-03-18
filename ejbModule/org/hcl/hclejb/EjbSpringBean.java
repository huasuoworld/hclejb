package org.hcl.hclejb;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

public class EjbSpringBean extends SpringBeanAutowiringInterceptor {
	
	private ApplicationContext ac;

	public BeanFactory getBeanFactory(Object target) {
		ac = new ClassPathXmlApplicationContext(ServicesConstants.SPRING);
		return ac;
	}
}
