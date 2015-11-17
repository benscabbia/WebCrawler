package model;




import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import controller.Controller;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

import com.google.gson.Gson;

public class WebCrawler {
	private String search;
	private String searchW;
	private boolean deepSearch, started=false;
	private int processors;
	private static Controller controller;
	private long startTime, endTime;
	private AllWebsiteProcessor webProcessor;
	private ExecutorService executor;

	List<String> websites = new ArrayList<String>();
	List<String> emails = new ArrayList<String>();
	List<String> fullLinks = new ArrayList<String>();

	public WebCrawler(String searchW, String search, boolean deepSearch) {
		this.search = search;
		this.searchW = searchW;
		this.deepSearch = deepSearch;
		controller = getControllerInstance();
		
		if(!started){
			initializeThreads();
		}
		//I searchW is not empty, search single website
		if(!searchW.isEmpty()){
			writeStatusText("\nScanning Single Website: " + searchW);
			writeStatusText("\nDeep Search: " + deepSearch);
			websites.add(searchW);
			executor.submit(webProcessor = new AllWebsiteProcessor(websites, deepSearch));
			executor.shutdown();
			writeStatusText("\nComplete");			
		}
		//search on google
		else{
			writeStatusText("\nSearching Google: " + "\""+search+"\"");
			executor.submit(webProcessor = new AllWebsiteProcessor(search, deepSearch));
			executor.shutdown();
		}
	}
	//only create threads once
	private void initializeThreads(){
		startTime = System.currentTimeMillis();
		processors = Runtime.getRuntime().availableProcessors();
		writeStatusText("Number of processors in system: " + processors);
		writeStatusText("\nCreating: " + processors + " Threads");
		executor = Executors.newFixedThreadPool(processors);
		started=true;
	}
	
	private Controller getControllerInstance() {
		return Controller.getController();
	}

	public static void writeStatusText(String text) {
		controller.writeStatusText(text);
		// controller.writeStatusText(text);
	}
	public static void writeContentText(String text){
		controller.writeContentText(text);
	}
}

class AllWebsiteProcessor implements Runnable {
	private final String EMAILREGEX = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
	private List<String> websites = new ArrayList<String>();
	private boolean deepSearch, googleSearch;
	private String search;
	private Set<String> emails = new HashSet<String>();
	
	//overloaded constructor
	AllWebsiteProcessor(List site, boolean deepSearch) {
		this.websites = site;
		this.deepSearch = deepSearch;
		this.googleSearch = false;
	}
	
	AllWebsiteProcessor(String search, boolean deepSearch) {
		this.search = search;
		this.deepSearch = deepSearch;
		this.googleSearch = true;
	}

	public void run() {
		
		if(googleSearch){
			writeStatusText("\nSearching...");
			searchAndQuickScan(search);
			
			
		}
		else{
			writeContentText("\n\nFrom: " + websites.get(0));
			if (!deepSearch) {
				scanSingleWebsite(websites.get(0));
				for(String email:emails){
					writeContentText("\n     "+email);
				}
			}
			else{
				scanFullWebsite(websites.get(0));
				for(String email:emails){
					writeContentText("\n     "+email);
				}
				//System.out.println(emails.size());
			}
		}
	}
		
			
	private void scanSingleWebsite(String website){
		try {
			String url = website;
			writeStatusText("\nLoading Site");
			Document document = Jsoup.connect(url).get();
			writeStatusText("\nParsing Site");
			grabEmails(document.toString());
			writeStatusText("\nEnd");
	} catch (Exception e) {
		writeStatusText("\n###ERROR in loading Site. Make sure URL: 'http:\\\\www.site.com'###");
	}	
	}
	private void scanFullWebsite(String website){
		try {
			String url = website;
			writeStatusText("\nLoading Site");
			Document document = Jsoup.connect(url).get();
			writeStatusText("\nParsing Site");
			writeStatusText("\nGrabbing Links");
			
			Set<String> absoluteLinks = new HashSet<String>();
			Elements links = document.select("a");

			writeStatusText("\nFormatting Links");
			for(Element link: document.select("a")){
				//String relHref = link.attr("href");
				String absHref = link.attr("abs:href");
				absoluteLinks.add(absHref);
			}
			System.out.println(absoluteLinks.size());
			
			for(String allLink: absoluteLinks){
				try{
					System.out.println(allLink);
					writeStatusText("\nParsing: " + allLink);
					Document document2 = Jsoup.connect(allLink).get();
					
					grabEmails(document2.toString());
				}catch(Exception e){
					writeStatusText("\nERROR Parsing: " + allLink);
				}
			}

			grabEmails(document.toString());
			writeStatusText("\nEnd");
	} catch (Exception e) {
		writeStatusText("\n###ERROR in loading Site. Make sure URL: 'http:\\\\www.site.com'###");
	}	
	}
	
	private void searchAndQuickScan(String search){
		GoogleResults results = null;
		System.out.println("in here");
		for (int i = 0; i < 100; i = i + 4) {
			String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&start="+i+"&q=";
			
			String query = search;
			String charset = "UTF-8";
			
			try {
				URL url = new URL(address + URLEncoder.encode(query, charset));
				Reader reader = new InputStreamReader(url.openStream(), charset);
				results = new Gson().fromJson(reader, GoogleResults.class);
			 
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Show title and URL of each results
			for (int m = 0; m <= 3; m++) {
				System.out.println("Title: " + results.getResponseData().getResults().get(m).getTitle());
				System.out.println("URL: " + results.getResponseData().getResults().get(m).getUrl() + "\n");
			}
		}
	}
	private void searchAndFullScan(){
		
	}
	
	public void grabEmails(String site) {
		Pattern p = Pattern.compile(EMAILREGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(site);
		while (matcher.find()) {
			emails.add(matcher.group());
		}
	}
	public void writeStatusText(String text) {
		WebCrawler.writeStatusText(text);
	}
	public void writeContentText(String text){
		WebCrawler.writeContentText(text);
	}
}

class GoogleResults {

	private ResponseData responseData;

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	public String toString() {
		return "ResponseData[" + responseData + "]";
	}

	static class ResponseData {
		private List<Result> results;

		public List<Result> getResults() {
			return results;
		}

		public void setResults(List<Result> results) {
			this.results = results;
		}

		public String toString() {
			return "Results[" + results + "]";
		}
	}

	static class Result {
		private String url;
		private String title;

		public String getUrl() {
			return url;
		}

		public String getTitle() {
			return title;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String toString() {
			return "Result[url:" + url + ",title:" + title + "]";
		}
	}
}
