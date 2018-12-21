package testcase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import common.*;

public class PageNotFound_404 extends Setup {
	
	@Test
	public static void PageNotFound() throws InterruptedException {
	try {
			line = Common.GetURLList("PageNotFound", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < line.size(); i++) {
			String URL = line.get(i);
			driver.get(URL);
			String title = driver.getTitle();
			if (title.equals("Page Not Found")) {
				try {
					String message = driver.findElement(By.xpath("//span[@class='not_found_404']")).getText();
					log.warn("\t" + URL + "\t" + title + "\t" + message);
				} catch (Exception ex) {
					log.error("\t" + URL + "\t" + ex.toString());
				}
			} else {
				log.info("\t" + URL + "\t" + title);
			}
			Thread.sleep(2000);
		}
	}

	@Test
	public static void PageNotFoundwithHttpConn() throws InterruptedException {
		try {
			line = Common.GetURLList("PageNotFound", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < line.size(); i++) {
	        //Sometimes we may face exception "java.net.MalformedURLException". Keep the code in try catch block to continue the broken link analysis
	        try {
	        	String urlLink = line.get(i);
	 //Use URL Class - Create object of the URL Class and pass the urlLink as parameter 
	 URL link = new URL(urlLink);
	 // Create a connection using URL object (i.e., link)
	 HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
	 //Set the timeout for 2 seconds
	 httpConn.setConnectTimeout(2000);
	 //connect using connect method
	 httpConn.connect();
	 //use getResponseCode() to get the response code. 
	 if(httpConn.getResponseCode()== 200) { 
	 log.info(urlLink+" - "+httpConn.getResponseCode()+" - "+httpConn.getResponseMessage());
	 }
	 if(httpConn.getResponseCode()== 404) {
	 log.info(urlLink+" - "+httpConn.getResponseCode()+" - "+httpConn.getResponseMessage());
	 }
	 }
	 //getResponseCode method returns = IOException - if an error occurred connecting to the server. 
	 catch (Exception e) {
	 //e.printStackTrace();
	 }
		}
	}
	
}
