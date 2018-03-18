package org.hcl.hclejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface PaymentHome extends EJBHome {
	PaymentRemote create() throws RemoteException, CreateException;
}
