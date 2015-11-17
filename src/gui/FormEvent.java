package gui;

import java.util.EventObject;

public class FormEvent extends EventObject{
	private String searchQuery;
	private String searchW;
	private boolean fullSearch;
	
	public FormEvent(Object source) {
		super(source);
	}
	public FormEvent(Object source, String searchW, String search, boolean fullSearch){
		super(source);
		this.searchW = searchW;
		this.searchQuery = search;
		this.fullSearch = fullSearch;
	}
	public String getSearchQuery() {
		return searchQuery;
	}
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	public String getSearchW() {
		return searchW;
	}
	public void setSearchW(String searchW) {
		this.searchW = searchW;
	}
	public boolean isFullSearch() {
		return fullSearch;
	}
	public void setFullSearch(boolean fullSearch) {
		this.fullSearch = fullSearch;
	}
	
	

}
