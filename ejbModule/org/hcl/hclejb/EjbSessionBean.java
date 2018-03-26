package org.hcl.hclejb;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.ejb.support.AbstractStatelessSessionBean;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class EjbSessionBean extends AbstractStatelessSessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669678827491930014L;

	@Override
	protected void onEjbCreate() throws CreateException {
		System.out.println("bean create!");
		processInjectionBasedOnCurrentContext(this);
//		ApplicationContext ac = new ClassPathXmlApplicationContext(ServicesConstants.SPRING);
		// billingService = (BillingService) getBeanFactory().getBean("billingService");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getEjb(Class<T> ejbName) {
//		appContext.refresh();
//		T t = (T) PortableRemoteObject.narrow(this.getSessionContext().lookup("billingService"), ejbName);
		T t = (T) this.getBeanFactory().getBean("billingService");
//		T t = (T) SpringContextUtil.getBean("billingService");
		return t;
	}
	
	public void processInjectionBasedOnCurrentContext(Object target) {  
        Assert.notNull(target, "Target object must not be null");  
//        WebApplicationContext cc = ContextLoader.getCurrentWebApplicationContext(); 
        BeanFactory beanFactory = this.getBeanFactory();
        if(beanFactory instanceof ApplicationContext) {
        	beanFactory = ((ApplicationContext) beanFactory).getAutowireCapableBeanFactory();
        }
        if (beanFactory != null) {  
            AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();  
//            ConfigurableListableBeanFactory factory = (ConfigurableListableBeanFactory) this.getBeanFactory();
//            ConfigurableListableBeanFactory factory = cc.getAutowireCapableBeanFactory();
            bpp.setBeanFactory(beanFactory);  
            bpp.processInjection(target);  
        }  
        else {  
//            if (logger.isDebugEnabled()) {  
//                logger.debug("Current WebApplicationContext is not available for processing of " +  
//                        ClassUtils.getShortName(target.getClass()) + ": " +  
//                        "Make sure this class gets constructed in a Spring web application. Proceeding without injection.");  
//            }  
        	System.out.println("Current WebApplicationContext is not available for processing of " +  
                        ClassUtils.getShortName(target.getClass()) + ": " +  
                        "Make sure this class gets constructed in a Spring web application. Proceeding without injection.");
        }  
    }

	@Override
	public void setSessionContext(SessionContext sessionContext) {
		super.setSessionContext(sessionContext);
		setBeanFactoryLocator(ContextSingletonBeanFactoryLocator.getInstance(ServicesConstants.SPRING));
		setBeanFactoryLocatorKey(ServicesConstants.PRIMARY_CONTEXT_ID);
	}

}
