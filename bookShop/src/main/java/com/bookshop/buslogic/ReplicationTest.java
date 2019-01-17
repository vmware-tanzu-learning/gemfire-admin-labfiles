package com.bookshop.buslogic;


import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import com.bookshop.domain.BookMaster;



public class ReplicationTest
{

	private ClientCache cache;
	private Region <Integer, BookMaster> books;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ReplicationTest test = new ReplicationTest();
		test.initializeCache();
		test.initializeBookMasterRegion();
		test.findBook();
		test.closeCache();
		

	}
	
	public void closeCache()
	{
		cache.close();
	}
	
	public void initializeCache()
	{
		this.cache = GemFireClientCacheHelper.create(false);
/*
/*		this.cache = new ClientCacheFactory()
        .set("name", "ClientWorker")
        .set("cache-xml-file", "xml/clientCache.xml")
        .create();*/
	}
	
	public void initializeBookMasterRegion()
	{
		books = cache.getRegion("BookMaster");
		System.out.println("Got the BookMaster Region: " + books);
	}
	
	public void findBook()
	{
		BookMaster theBook = (BookMaster)books.get(new Integer(123));
		System.out.println("Book retrieved: " + theBook);
		theBook = (BookMaster)books.get(new Integer(456));
		System.out.println("Book retrieved: " + theBook);
		theBook = (BookMaster)books.get(new Integer(789));
		System.out.println("Book retrieved: " + theBook);
		}

}
