package org.hcl.ejb.clientsample;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.hcl.ejb.PaymentEJB;
import org.hcl.ejb.PaymentEJBService;

import com.sun.xml.internal.ws.api.message.Header;
import com.sun.xml.internal.ws.api.message.Headers;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        PaymentEJBService service1 = new PaymentEJBService();
//	        Stub stub = (Stub) service1.getPaymentEJBPort();
	        List<Header> list = new ArrayList<Header>();
	        Header header1 = Headers.create(new QName("Username"),"manager");
	        Header header2 = Headers.create(new QName("Password"),"Ejb!123456");
	        list.add(header1);
	        list.add(header2);
//	        stub.setOutboundHeaders(list);
//	        stub._setProperty(Stub.USERNAME_PROPERTY, "manager");
//	        stub._setProperty(Stub.PASSWORD_PROPERTY, "Ejb!123456");
	        
	        System.out.println("Create Web Service...");
	        PaymentEJB port1 = service1.getPaymentEJBPort();
	        BindingProvider bp = (BindingProvider) port1;
	        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "manager");
	        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "Ejb!123456");
//	        bp.setOutboundHeaders(list);
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.test());
	        System.out.println("Create Web Service...");
	        PaymentEJB port2 = service1.getPaymentEJBPort();
	        BindingProvider bp2 = (BindingProvider) port2;
	        bp2.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "manager");
	        bp2.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "Ejb!123456");
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.test());
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
