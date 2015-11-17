package controller;

import model.WebCrawler;
import gui.App;
import gui.FormEvent;
import gui.MainFrame;


public class Controller {
	private WebCrawler webCrawler;
	private String searchW;
	private String search;
	private boolean deepSearch;
	
	public void getSearch(FormEvent e){
		search = e.getSearchQuery();
		searchW = e.getSearchW();
		deepSearch = e.isFullSearch();
		webCrawler = new WebCrawler(searchW, search, deepSearch);
	}
	public void writeStatusText(String text){
		MainFrame.writeStatusText(text);
	}
	public void writeContentText(String text){
		MainFrame.writeContentText(text);
	}
	public static Controller getController(){
		return MainFrame.getControllerInstance();
	}
	
}
