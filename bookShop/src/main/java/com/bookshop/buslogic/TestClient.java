package com.bookshop.buslogic;


import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import com.bookshop.domain.Address;
import com.bookshop.domain.Customer;



public class TestClient {

private ClientCache cache ;
private Region<Integer, Customer> customers;
	
	public static void main(String[] args)
	{
		TestClient harness = new TestClient();
		harness.initializeClientCache();
		harness.createCustomer();
		harness.closeCache();
	}
	
	
	public void closeCache()
	{
		System.out.println("cache is: " + cache);
		cache.close();
	}

	public void initializeClientCache()
	{
		this.cache = GemFireClientCacheHelper.create(false);

		/*cache = new ClientCacheFactory().set("name", "ClientWorker")
				.set("cache-xml-file", "xml/clientCache.xml")
				.create();*/
		
		customers = cache.getRegion("Customer");

	    System.out.println("Customer Region = " + customers.getFullPath());

	}
	
	
	public void createCustomer()
	{

		Customer cust1 = new Customer(new Integer(5543), "Lula",
                "Wax", null,	null);
		cust1.setPrimaryAddress(new Address("123 Main St.", null, null,  
				"Topeka","KS", "50505", "US", "423-555-3322", "HOME"));
		cust1.addOrder(new Integer(17699));
		customers.put(new Integer(5543), cust1);
		System.out.println("Inserted a customer: " + cust1);
		
		

	}
	
}