package com.bookshop.buslogic;

import com.bookshop.domain.Address;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import com.bookshop.domain.Customer;

import java.io.*;
import java.text.ParseException;


public class CustomerLoader
{
	static final String CUSTOMER_FILE="/Customer.csv";

	private ClientCache cache;
	private Region <Integer, Customer> customers;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CustomerLoader loader = new CustomerLoader();
		loader.getCache();
		loader.getCustomerRegion();
		loader.populateCustomers();
		loader.closeCache();


	}

	public void setCache (ClientCache cache)
	{
		this.cache = cache;
	}

	public void closeCache()
	{
		cache.close();
	}

	public void getCache()
	{
		this.cache = GemFireClientCacheHelper.create(false);

	}

	public void getCustomerRegion()
	{
		customers = cache.getRegion("Customer");
		System.out.println("Got the Customer Region: " + customers);
	}


	public void populateCustomers() {
		InputStream resource = getClass().getResourceAsStream(CUSTOMER_FILE);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource))) {
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] lineArray = line.split(",");
				String[] dateArray=  lineArray[4].split("/");
				Address addr = new Address(lineArray[3],null, null, lineArray[4], lineArray[5], lineArray[6], lineArray[7], lineArray[8], null);
				Customer cust = new Customer(new Integer(lineArray[0]), lineArray[1], lineArray[2], addr);
				customers.put(cust.getCustomerNumber(), cust);
				//BookMaster book = new BookMaster(new Integer(lineArray[0]), lineArray[3], number.floatValue(), new Integer(dateArray[2]), lineArray[2], lineArray[1]);
				//books.put(book.getItemNumber(), book);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void populateCustomers_old()
	{

		Customer cust1 = new Customer(5598, "Kari", "Powell", ""+44444);
		cust1.addOrder(new Integer(17699));
		cust1.addOrder (new Integer(18009));
		cust1.addOrder (new Integer(18049));
		customers.put(new Integer(5598), cust1);
		System.out.println("Inserted a customer: " + cust1);



		Customer cust2 = new Customer (new Integer(5543), "Lula", "Wax", ""+12345);
		cust2.addOrder(new Integer(17699));
		customers.put(new Integer(5543), cust2);
		System.out.println("Inserted a customer: " + cust2);


		Customer cust3 = new Customer (new Integer(6024), "Trenton", "Garcia", ""+88888);
		customers.put(new Integer(6024), cust3);
		System.out.println("Inserted a customer: " + cust3);

	}
}
