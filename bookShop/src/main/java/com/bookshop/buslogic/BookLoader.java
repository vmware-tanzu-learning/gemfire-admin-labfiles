package com.bookshop.buslogic;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import com.bookshop.domain.BookMaster;

import java.io.*;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public class BookLoader
{
		static final String BOOKFILE="/BookMaster.csv";

		private ClientCache cache;
		private Region <Integer, BookMaster> books;
		/**
		 * @param args
		 */
		public static void main(String[] args)
		{
			BookLoader loader = new BookLoader();
			loader.initializeCache();
			loader.initializeBookMasterRegion();
			loader.populateBooks();
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
		
		public void initializeCache()
		{
			this.cache = GemFireClientCacheHelper.create(false);
		}
		
		public void initializeBookMasterRegion()
		{
			books = cache.getRegion("BookMaster");
			System.out.println("Got the BookMaster Region: " + books);
		}

		//  BookMaster(int itemNumber, String description, float retailCost,
		//			int yearPublished, String author, String title)
		public void populateBooks() {
			NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
			URL url = getClass().getResource(BOOKFILE);
			InputStream resource = getClass().getResourceAsStream(BOOKFILE);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(resource))) {
				String line;
				line = br.readLine();
				while ((line = br.readLine()) != null) {
					String[] lineArray = line.split(",");
					String[] dateArray=  lineArray[4].split("/");
					Number number = formatter.parse(lineArray[5]);
					BookMaster book = new BookMaster(new Integer(lineArray[0]), lineArray[3], number.floatValue(), new Integer(dateArray[2]), lineArray[2], lineArray[1]);
					books.put(book.getItemNumber(), book);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}

		
		public void populateBooks_old()
		{
			BookMaster book = new BookMaster(123, "Run on sentences and drivel on all things mundane", (float) 34.99, 2011, "Daisy Mae West", "A Treatise of Treatises");
			books.put(new Integer(123), book);
			System.out.println("Inserted a book: " + book);
			BookMaster book2 = new BookMaster(456, "A book about a dog", (float) 11.99, 1971, "Clarence Meeks", "Clifford the Big Red Dog");
			books.put(new Integer(456), book2);
			System.out.println("Inserted a book: " + book2);
			BookMaster book3 = new BookMaster(789, "Theoretical information about the structure of Operating Systems", (float)59.99, 2011, "Jim Heavisides", "Operating Systems: An Introduction");
			books.put(new Integer(789), book3);
			System.out.println("Inserted a book: " + book3);

		}
		
		

}
