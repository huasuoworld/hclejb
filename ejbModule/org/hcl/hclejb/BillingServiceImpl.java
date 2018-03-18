package org.hcl.hclejb;

import org.springframework.stereotype.Service;

@Service("billingService")
public class BillingServiceImpl implements BillingService {

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "hello ejb and spring";
	}

}
