package com.bookshop.buslogic;


import java.io.Console;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import com.bookshop.domain.Customer;



public class TestSecurityClient {

private ClientCache cache ;
private Region<Integer, Customer> customers;
	

	public static void main(String[] args)
	{
		
		TestSecurityClient harness = new TestSecurityClient();
		harness.initializeClientCache();
		harness.readCustomer();
		harness.closeCache();
	}
	
	
	public void closeCache()
	{
		System.out.println("cache is: " + cache);
		cache.close();
	}

	public void initializeClientCache()
	{
/*		cache = new ClientCacheFactory().set("name", "ClientWorker")
				.set("cache-xml-file", "xml/clientCache.xml")
				.create();*/
		this.cache = GemFireClientCacheHelper.create(false);
		customers = cache.getRegion("Customer");

	    System.out.println("Customer Region = " + customers.getFullPath());

	}
	
	
	public void readCustomer()
	{

		Customer cust1 = customers.get(5598);

		System.out.println("Read a customer: " + cust1);
		
		

	}
	
}