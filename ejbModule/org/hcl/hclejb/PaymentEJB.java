package org.hcl.hclejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.jboss.ws.api.annotation.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless
@Interceptors(SpringBeanAutowiringInterceptor.class)
@WebService(targetNamespace="http://org.hcl")
@WebContext(contextRoot="webservices",authMethod="BASIC")
@Remote(IPayment.class)
public class PaymentEJB extends EjbSessionBean implements IPayment {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1223207101434059821L;
	@Autowired
	@Qualifier("billingService")
	private BillingService billingService;

	@Override
	public String test() {
//		billingService = this.getEjb(BillingService.class);
		String str = billingService.test();
		System.out.println(str);
		return str;
	}

}
