package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import controller.Controller;

public class SpeedTest {
	static long end;
	static long start;
	static List websites;
	static int processors = 256;
	public static void main(String[] args) {
		websites = new ArrayList();
		populateWebsites();
		populateWebsites();
		//populateWebsites();
		//populateWebsites();
		
		System.out.println(websites.size());
		start = System.currentTimeMillis();
		WebCrawlerTest wc = new WebCrawlerTest(websites,true);
		
		//AllWebsiteProcessor test = new AllWebsiteProcessor(websites, true);
		
		
	}
	private static void populateWebsites() {
		websites.add("http://google.co.uk");
		websites.add("http://prodev.comyr.com/");
		websites.add("http://maxmakedesign.co.uk");
		//websites.add("http://stackoverflow.com");
		websites.add("http://google.co.uk");
		websites.add("http://facebook.com");
		//websites.add("http://amazon.com");
		websites.add("http://google.co.uk");
		websites.add("http://purplemath.com");
		websites.add("http://copy.com");
		websites.add("http://dropbox.com");
		websites.add("http://bbc.com");
		//websites.add("http://stackoverflow.com");
		websites.add("http://google.co.uk");
		websites.add("http://www.mkyong.com");
		websites.add("http://msxml.excite.com");
		websites.add("http://www.codingame.com");
		websites.add("http://codecombat.com");
		websites.add("https://vimeo.com");
		websites.add("http://www.mageewp.com/");
		websites.add("https://www.siteground.com/");
		websites.add("http://demodern.com/");
		websites.add("http://www.sql.su/");
		
		websites.add("http://google.co.uk");
		websites.add("http://prodev.comyr.com/");
		websites.add("http://maxmakedesign.co.uk");
		//websites.add("http://stackoverflow.com");
		websites.add("http://google.co.uk");
		websites.add("http://facebook.com");
		websites.add("http://google.co.uk");
		//websites.add("http://amazon.com");
		websites.add("http://purplemath.com");
		websites.add("http://copy.com");
		websites.add("http://dropbox.com");
		websites.add("http://bbc.com");
		//websites.add("http://stackoverflow.com");
		websites.add("http://google.co.uk");
		websites.add("http://www.mkyong.com");
		websites.add("http://msxml.excite.com");
		websites.add("http://www.codingame.com");
		websites.add("http://codecombat.com");
		websites.add("https://vimeo.com");
		websites.add("http://www.mageewp.com/");
		websites.add("https://www.siteground.com/");
		websites.add("http://demodern.com/");
		websites.add("http://www.sql.su/");
		
		websites.add("http://google.co.uk");
		websites.add("http://prodev.comyr.com/");
		websites.add("http://maxmakedesign.co.uk");
		//websites.add("http://stackoverflow.com");
		websites.add("http://google.co.uk");
		websites.add("http://facebook.com");
		websites.add("http://google.co.uk");
		//websites.add("http://amazon.com");
		websites.add("http://purplemath.com");
		websites.add("http://copy.com");
		websites.add("http://dropbox.com");
		websites.add("http://bbc.com");
		//websites.add("http://stackoverflow.com");
		websites.add("http://google.co.uk");
		websites.add("http://www.mkyong.com");
		websites.add("http://msxml.excite.com");
		websites.add("http://www.codingame.com");
		websites.add("http://codecombat.com");
		websites.add("https://vimeo.com");
		websites.add("http://www.mageewp.com/");
		websites.add("https://www.siteground.com/");
		websites.add("http://demodern.com/");
		websites.add("http://www.sql.su/");

		websites.add("http://google.co.uk");
		websites.add("http://prodev.comyr.com/");
		websites.add("http://maxmakedesign.co.uk");
		//websites.add("http://stackoverflow.com");
		websites.add("http://google.co.uk");
		websites.add("http://facebook.com");
		websites.add("http://google.co.uk");
		//websites.add("http://amazon.com");
		websites.add("http://purplemath.com");
		websites.add("http://copy.com");
		websites.add("http://dropbox.com");
		websites.add("http://bbc.com");
		//websites.add("http://stackoverflow.com");
		websites.add("http://google.co.uk");
		websites.add("http://www.mkyong.com");
		websites.add("http://msxml.excite.com");
		websites.add("http://www.codingame.com");
		websites.add("http://codecombat.com");
		websites.add("https://vimeo.com");
		websites.add("http://www.mageewp.com/");
		websites.add("https://www.siteground.com/");
		websites.add("http://demodern.com/");
		websites.add("http://www.sql.su/");

		websites.add("http://google.co.uk");
		websites.add("http://prodev.comyr.com/");
		websites.add("http://maxmakedesign.co.uk");
		websites.add("http://google.co.uk");
		
		//websites.add("http://stackoverflow.com");
		websites.add("http://facebook.com");
		websites.add("http://google.co.uk");
		//websites.add("http://amazon.com");
		websites.add("http://purplemath.com");
		websites.add("http://copy.com");
		websites.add("http://dropbox.com");
		websites.add("http://bbc.com");
		websites.add("http://google.co.uk");
		//websites.add("http://stackoverflow.com");
		websites.add("http://www.mkyong.com");
		websites.add("http://msxml.excite.com");
		websites.add("http://www.codingame.com");
		websites.add("http://codecombat.com");
		websites.add("https://vimeo.com");
		websites.add("http://www.mageewp.com/");
		websites.add("https://www.siteground.com/");
		websites.add("http://demodern.com/");
		websites.add("http://www.sql.su/");
		
	}
}
	
	class WebCrawlerTest {
		private String search;
		private String searchW;
		private boolean deepSearch, started=false;
		private int processors;
		//private static Controller controller;
		private long startTime, endTime;
		private AllWebsiteProcessorTest webProcessor;
		private ExecutorService executor;

		List<String> websites = new ArrayList<String>();
		List<String> emails = new ArrayList<String>();
		List<String> fullLinks = new ArrayList<String>();

		public WebCrawlerTest(List<String> websites, boolean deepSearch) {
			//this.search = search;
			//this.searchW = searchW;
			this.websites = websites;
			this.deepSearch = deepSearch;
			//controller = getControllerInstance();
			
			if(!started){
				startTime = System.currentTimeMillis();
				//processors = Runtime.getRuntime().availableProcessors();
				//System.out.println("starting threads with this many: " + processors);
				//writeStatusText("Number of processors in system: " + processors);
				//writeStatusText("\nCreating: " + processors + " Threads");
				executor = Executors.newFixedThreadPool(SpeedTest.processors);
				started=true;
			}
			//I searchW is not empty, search single website
			
				//writeStatusText("\nScanning Single Website: " + searchW);
				//writeStatusText("\nDeep Search: " + deepSearch);
				for(String site : websites){
					executor.submit(webProcessor = new AllWebsiteProcessorTest(site, deepSearch));
				}
				
				//webProcessor.run();
				
				executor.shutdown();
				try {
					executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("ONLY DOING IT ONCE");
				long end = System.currentTimeMillis();
				System.out.println((end-SpeedTest.start)/1000.0 + " Time");
				
				//writeStatusText("\nComplete");			
		}
	}
	
/*class SingleWebsiteProcessorTest implements Runnable{
	String link;
	SingleWebsiteProcessorTest(String link){
		this.link = link;
	}

	public void run() {
		//System.out.println("HERE");
		try {
			Document document2 = Jsoup.connect(link).get();
			AllWebsiteProcessorTest.grabEmails(document2.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//grabEmails(document2.toString());
	}
	
}*/

class AllWebsiteProcessorTest implements Runnable{
	//private List<String> websites = new ArrayList<String>();
	String websites;
	private boolean deepSearch, googleSearch;
	private String search; //changed to static to test with new singleWebsiteProcessor
	private static Set<String> emails = new HashSet<String>();//changed to static to test with new singleWebsiteProcessor
	private final static String EMAILREGEX = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
	public Set<String> absoluteLinks; //experimental, trying with singleSiteProcessor
	//****experimental
	//ExecutorService executor = Executors.newFixedThreadPool(2);
	//SingleWebsiteProcessorTest singleWebProcessor;
	
	
	//executor.submit(webProcessor = new AllWebsiteProcessorTest(site, deepSearch);
	//****experimental
	
	//overloaded constructor
	AllWebsiteProcessorTest(String site, boolean deepSearch) {
		this.websites = site;
		this.deepSearch = deepSearch;
	}
	
	public void run() {
			
				scanFullWebsite(websites);
				//scanSingleWebsite(websites);
				//System.out.println("size###################"+emails.size());
				for(String email:emails){
					//writeContentText("\n     "+email);
					//System.out.print(email + ", ");       //didn't want it in test result
					//System.out.println("emailssssss");
				}
				//System.out.println(emails.size());
				//System.out.println("COMPLETE####################################");
		}
	
		
			
	private void scanSingleWebsite(String website){
		try {
			String url = website;
			//writeStatusText("\nLoading Site");
			Document document = Jsoup.connect(url).get();
			//writeStatusText("\nParsing Site");
			grabEmails(document.toString());
			//writeStatusText("\nEnd");
	} catch (Exception e) {
		//System.out.println("\n###ERROR in loading Site. Make sure URL: 'http:\\\\www.site.com'###");
	
	}
	}

	private void scanFullWebsite(String website){
		try {
			String url = website;
			//writeStatusText("\nLoading Site");
			System.out.println("Loading site");
			Document document = Jsoup.connect(url).get();
			System.out.println("Parsing site and grabbing links");
			//writeStatusText("\nParsing Site");
			//writeStatusText("\nGrabbing Links");
					
			//Set<String> absoluteLinks = new HashSet<String>();
			absoluteLinks = new HashSet<String>();
			Elements links = document.select("a");
			System.out.println("==================================number of pages: " + links.size());	
			
			
			//writeStatusText("\nFormatting Links");
			for(Element link: document.select("a")){
				//String relHref = link.attr("href");
				String absHref = link.attr("abs:href");
				absoluteLinks.add(absHref);
			}
			System.out.println(absoluteLinks.size());
			
			//**********experimental
			/*if(links.size() > 50){
				for(String allLink: absoluteLinks){
					System.out.println(allLink);
					executor.submit(singleWebProcessor = new SingleWebsiteProcessorTest(allLink));
				}
				executor.shutdown();
			}
				*/
			
			//****experimental
			
			for(String allLink: absoluteLinks){
				try{
					System.out.println(allLink);
					//writeStatusText("\nParsing: " + allLink);
					Document document2 = Jsoup.connect(allLink).get();
					
					grabEmails(document2.toString());
				}catch(Exception e){
					//writeStatusText("\nERROR Parsing: " + allLink);
				}
			}

			grabEmails(document.toString());
			
			
			
			//writeStatusText("\nEnd");
	} catch (Exception e) {
		//writeStatusText("\n###ERROR in loading Site. Make sure URL: 'http:\\\\www.site.com'###");
	}	
		
		
	}
	public static void grabEmails(String site) {
		Pattern p = Pattern.compile(EMAILREGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(site);
		while (matcher.find()) {
			emails.add(matcher.group());
		}
	}
	
}
	


