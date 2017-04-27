package com.book.store;

import java.util.ArrayList;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class BookBean {
	private int bookId;
	private String type;
	private String authors;
	private String editors;
	private String title;
	private String bookTitle;
	private String pages;
	private String year;
	private String address;
	private String journal;
	private String volume;
	private String number;
	private String month;
	private String url;
	private String ee;
	private String cdrom;
	private String cite;
	private String publisher;
	private String note;
	private String crossref;
	private String isbn;
	private String series;
	private String school;
	private String chapter;

	private int price;
	private String picture;
	private int uid;
	private String status;
	private int sid;

	public BookBean(){}

	public BookBean(String type, int bookId, String authors, String editors, String title, String bookTitle,
			String pages, String year, String address, String journal, String volume, String number,
			String month, String url, String ee, String cdrom, String cite, String publisher, String note,
			String crossref, String isbn, String school, String series, String chapter) {
		super();
		this.bookId = bookId;
		this.type = type;
		this.authors = authors;
		this.editors = editors;
		this.title = title;
		this.bookTitle = bookTitle;
		this.pages = pages;
		this.year = year;
		this.address = address;
		this.journal = journal;
		this.volume = volume;
		this.number = number;
		this.month = month;
		this.url = url;
		this.ee = ee;
		this.cdrom = cdrom;
		this.cite = cite;
		this.publisher = publisher;
		this.note = note;
		this.crossref = crossref;
		this.isbn = isbn;
		this.series = series;
		this.school = school;
		this.chapter = chapter;
	}

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getEditors() {
		return editors;
	}
	public void setEditors(String editors) {
		this.editors = editors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		try {
			if (url != null) {
				this.url = URLEncoder.encode(url, "UTF-8");
			} else {
				this.url = url;
			}
		} catch (UnsupportedEncodingException e) {}
	}
	public String getEe() {
		return ee;
	}
	public void setEe(String ee) {
		this.ee = ee;
	}
	public String getCdrom() {
		return cdrom;
	}
	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}
	public String getCite() {
		return cite;
	}
	public void setCite(String cite) {
		this.cite = cite;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCrossref() {
		return crossref;
	}
	public void setCrossref(String crossref) {
		this.crossref = crossref;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		try {
			if (picture != null) {
				this.picture = URLDecoder.decode(picture, "UTF-8");
			} else {
				this.picture = picture;
			}
		} catch (UnsupportedEncodingException e) {}
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setAll(BookBean old) {
		this.bookId = old.getBookId();
		this.type = old.getType();
		this.authors = old.getAuthors();
		this.editors = old.getEditors();
		this.title = old.getTitle();
		this.bookTitle = old.getBookTitle();
		this.pages = old.getPages();
		this.year = old.getYear();
		this.address = old.getAddress();
		this.journal = old.getJournal();
		this.volume = old.getVolume();
		this.number = old.getNumber();
		this.month = old.getMonth();
		this.url = old.getUrl();
		this.ee = old.getEe();
		this.cdrom = old.getCdrom();
		this.cite = old.getCite();
		this.publisher = old.getPublisher();
		this.note = old.getNote();
		this.crossref = old.getCrossref();
		this.isbn = old.getIsbn();
		this.series = old.getSeries();
		this.school = old.getSchool();
		this.chapter = old.getChapter();
	}
	
	public ArrayList<BookBean> getSellItems(BookBean book) {
		SqlConnect Sqlhandle = new SqlConnect();
		ArrayList<BookBean> resultBook = Sqlhandle.searchBookSells(book);
		Sqlhandle.close();
		return resultBook;
	}
	
	public int addBook(BookBean book) {
		SqlConnect Sqlhandle = new SqlConnect();
		int bookId = Sqlhandle.addBook(book);
		Sqlhandle.close();
		return bookId;
	}
	
	public ArrayList<BookBean> getRandBooks() {
		ArrayList<BookBean> randBooks = new ArrayList<BookBean>();
		SqlConnect Sqlhandle = new SqlConnect();
		for (int i = 0; i < Math.min(10,20523); i++) {
			randBooks.add(Sqlhandle.searchBook((int)(Math.random()*20523)));
		}
		Sqlhandle.close();
		return randBooks;
	}
}