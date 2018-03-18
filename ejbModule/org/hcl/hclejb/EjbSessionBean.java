package org.hcl.hclejb;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.rmi.PortableRemoteObject;

import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.ejb.support.AbstractStatelessSessionBean;

public class EjbSessionBean extends AbstractStatelessSessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669678827491930014L;

	@Override
	protected void onEjbCreate() throws CreateException {
		System.out.println("bean create!");
//		ApplicationContext ac = new ClassPathXmlApplicationContext(ServicesConstants.SPRING);
		// billingService = (BillingService) getBeanFactory().getBean("billingService");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getEjb(Class<T> ejbName) {
//		T t = (T) PortableRemoteObject.narrow(this.getSessionContext().lookup("billingService"), ejbName);
		T t = (T) this.getBeanFactory().getBean("billingService");
		return t;
	}

	@Override
	public void setSessionContext(SessionContext sessionContext) {
		super.setSessionContext(sessionContext);
		setBeanFactoryLocator(ContextSingletonBeanFactoryLocator.getInstance(ServicesConstants.SPRING));
		setBeanFactoryLocatorKey(ServicesConstants.PRIMARY_CONTEXT_ID);
	}

}
