package com.book.store;

import java.util.ArrayList;

public class Search {
	private String searchType;

	private String value;
	private String key;

	private String publicationType;
	private String authors;
	private String editors;
	private String title;
	private String journal;
	private String year;
	private String isbn;

	public Search() {}

	public Search(String searchType, String value, String key) {
		super();
		this.searchType = searchType;
		this.value = value;
		this.key = key;
	}
	
	public Search(String searchType, String publicationType, String authors,
			String editors, String title, String journal, String year, String isbn){
		super();
		this.searchType = searchType;
		this.publicationType = publicationType;
		this.authors = authors;
		this.editors = editors;
		this.title = title;
		this.journal = journal;
		this.year = year;
		this.isbn = isbn;
	}

	public String getSearchType() {
		return this.searchType;
	}
	public String getValue() {
		return this.value;
	}
	public String getKey() {
		return this.key;
	}
	public String getPublicationType() {
		return this.publicationType;
	}
	public String getAuthors() {
		return this.authors;
	}
	public String getEditors() {
		return this.editors;
	}
	public String getTitle() {
		return this.title;
	}
	public String getJournal() {
		return this.journal;
	}
	public String getYear() {
		return this.year;
	}
	public String getIsbn() {
		return this.isbn;
	}

	public ArrayList<BookBean> doSearch() {
		SqlConnect Sqlhandle = new SqlConnect();
		if (searchType.equals("welcome_search") || searchType.equals("sell_search")
				|| searchType.equals("admin_search")) {
			ArrayList<BookBean> resultBooks = Sqlhandle.searchBooks(key, value);
			Sqlhandle.close();
			return resultBooks;
		} else if (searchType.equals("advanced_search")) {
			ArrayList<BookBean> resultBooks = Sqlhandle.searchBooks(publicationType,
					authors, editors, title, journal, year, isbn);
			Sqlhandle.close();
			return resultBooks;
		}
		Sqlhandle.close();
		return null;
	}

	public ArrayList<CustomerBean> customerSearch(){
		SqlConnect Sqlhandle = new SqlConnect();
		ArrayList<CustomerBean> users = Sqlhandle.searchUsers(key,value);
		Sqlhandle.close();
		return users;
	}
}
