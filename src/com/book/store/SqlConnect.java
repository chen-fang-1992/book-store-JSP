package com.book.store;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SqlConnect {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final String INSERT_USER = "insert into users(username,password,email,"
			+ "firstname,lastname,address,birthyear,creditcard,status) values(?,?,?,?,?,?,?,?,?)";
	private static final String SEARCH_USER_INFO_BY_USERNAME = "select * from users where username=?";
	private static final String SEARCH_USER_INFO_BY_UID = "select * from users where uid=?";
	private static final String UNBAN_USER_BY_UID = "update users set status = 'good' where uid=?";
	private static final String BAN_USER_BY_UID = "update users set status = 'invalid' where uid=?";

	private static final String INSERT_BUY_RECORD = "insert into buy_record(uid,sid) value(?,?)";

	private static final String INSERT_CUS_ACTIVITY = "insert into cus_activity(uid,sid,action,time) values(?,?,?,?)";
	private static final String SEARCH_CUS_ACTIVITY_BY_UID = "select * from cus_activity where uid=?";

	private static final String INSERT_BOOK = "insert into books(publication_type,authors,editor,title,booktitle,pages,"
			+ "year,address,journal,volume,number,month,url,ee,cdrom,cite,publisher,note,crossref,isbn,series,school,chapter)"
			+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SEARCH_BOOK_BY_BID = "select * from books where bid=?";
	private static final String SEARCH_BOOK_MAX_BID = "select max(bid) from books";

	private static final String INSERT_SELL_RECORD = "insert into sell_record(bid,uid,price,picture,status) values(?,?,?,?,?)";
	private static final String SEARCH_SELL_RECORD_BY_SID = "select * from sell_record where sid?=";
	private static final String SEARCH_SELL_RECORD_MAX_SID = "select max(sid) from sell_record";
	private static final String SEARCH_SELL_RECORD_BY_UID = "select * from sell_record where uid=?";
	private static final String SEARCH_SELL_RECORD_BY_BID = "select * from sell_record where bid=?";
	private static final String ACTIVATE_SELL_RECORD_BY_SID = "update sell_record set status = 'good' where sid=?";
	private static final String UNACTIVATE_SELL_RECORD_BY_SID = "update sell_record set status = 'pending' where sid=?";
	private static final String REMOVE_SELL_RECORD_BY_SID = "update sell_record set status = 'invalid' where sid=?";

	public SqlConnect() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("notConnect");
		}
	}

	public void close() {
		try {
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId(String searchAttr, String table, String column, String value) {
		String GET_ID = "select " + searchAttr + " from " + table + " where " + column + "=?";
		try {
			pst = con.prepareStatement(GET_ID);
			pst.setString(1, value);
			pst.executeQuery();
			rs = pst.executeQuery();
			rs.next();
			int result = rs.getInt(searchAttr);
			rs.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public CustomerBean getUserInfo(String username) {
		CustomerBean cb = new CustomerBean();
		try {
			pst = con.prepareStatement(SEARCH_USER_INFO_BY_USERNAME);
			pst.setString(1, username);
			rs = pst.executeQuery();
			while (rs.next()) {
				cb.setUid(rs.getInt("uid"));
				cb.setName(rs.getString("username"));
				cb.setPassword(rs.getString("password"));
				cb.setEmail(rs.getString("email"));
				cb.setFirstname(rs.getString("firstname"));
				cb.setLastname(rs.getString("lastname"));
				cb.setAddress(rs.getString("address"));
				cb.setBirthyear(rs.getString("birthyear"));
				cb.setCredit(rs.getString("creditcard"));
				cb.setStatus(rs.getString("status"));
			}
			rs.close();
			return cb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cb;
	}

	public CustomerBean getUserInfo(int uid) {
		CustomerBean cb = new CustomerBean();
		try {
			pst = con.prepareStatement(SEARCH_USER_INFO_BY_UID);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				cb.setUid(rs.getInt("uid"));
				cb.setName(rs.getString("username"));
				cb.setPassword(rs.getString("password"));
				cb.setEmail(rs.getString("email"));
				cb.setFirstname(rs.getString("firstname"));
				cb.setLastname(rs.getString("lastname"));
				cb.setAddress(rs.getString("address"));
				cb.setBirthyear(rs.getString("birthyear"));
				cb.setCredit(rs.getString("creditcard"));
				cb.setStatus(rs.getString("status"));
			}
			rs.close();
			return cb;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return cb;
	}

	public String getUsername(int sid) {
		CustomerBean cb = new CustomerBean();
		try {
			pst = con.prepareStatement(SEARCH_SELL_RECORD_BY_SID);
			pst.setInt(1, sid);
			rs = pst.executeQuery();
			while (rs.next()) {
				cb = getUserInfo(rs.getInt("uid"));			
			}
			rs.close();
			return cb.getName();
		} catch(Exception e) {
			e.printStackTrace();
		}	
		return cb.getName();
	}

	public boolean authenticate(String table ,String column, String username, String password) {
		String AUTHENTICATE = "select * from " + table + " where " + column + "=? and password=?"; 
		try {
			pst = con.prepareStatement(AUTHENTICATE);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				rs.close();
				return true;
			} else {
				rs.close();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean authUsername(String table ,String column, String username) {
		try {
			pst = con.prepareStatement(SEARCH_USER_INFO_BY_USERNAME);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				rs.close();
				return true;
			} else {
				rs.close();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkUserStatus(String table ,String column,String username, String value) {
		try {
			pst = con.prepareStatement(SEARCH_USER_INFO_BY_USERNAME);
			pst.setString(1, username);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (rs.getString("status").equals(value)) {
					rs.close();
					return true;
				} else {
					rs.close();
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateUserProfile(String column, String value, String uid) {
		String UPDATE_USER_PROFILE = "update users set " + column + "=? where uid=?";
		try {
			pst = con.prepareStatement(UPDATE_USER_PROFILE);
			pst.setString(1, value);
			pst.setString(2, uid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertUser(String username, String email, String password, String retype, String firstname,
			String lastname, String address, String birthYear, String creditcard, String status) {
		try {
			pst = con.prepareStatement(INSERT_USER);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, firstname);
			pst.setString(5, lastname);
			pst.setString(6, address);
			pst.setString(7, birthYear);
			pst.setString(8, creditcard);
			pst.setString(9, status);
			pst.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void insertBuy(int uid, int sid) {
		try {
			pst = con.prepareStatement(INSERT_BUY_RECORD);
			pst.setInt(1, uid);
			pst.setInt(2, sid);
			pst.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void insertCus(int uid, int sid, String action) {
		try {
			pst = con.prepareStatement(INSERT_CUS_ACTIVITY);
			Timestamp timestamp = new Timestamp(new Date().getTime());
			pst.setInt(1, uid);
			pst.setInt(2, sid);
			pst.setString(3, action);
			pst.setTimestamp(4, timestamp);
			pst.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public OrdProcessBean searchSellRecordBySid(int sid) {
		OrdProcessBean OPB = new OrdProcessBean();
		try {
			pst = con.prepareStatement(SEARCH_SELL_RECORD_BY_SID);
			pst.setInt(1, sid);
    		rs = pst.executeQuery();
			while (rs.next()) {
				OPB.setSellId(rs.getInt("sid"));
				OPB.setBookId(rs.getInt("bid"));
				OPB.setUserId(rs.getInt("uid"));
				OPB.setPrice(rs.getInt("price"));
				OPB.setPicture(rs.getString("picture"));
				OPB.setStatus(rs.getString("status"));
			}
			rs.close();
			return OPB;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return OPB;
	}

	public BookBean searchBook(int bid) {
		BookBean resultBook = new BookBean();
		try {
			pst = con.prepareStatement(SEARCH_BOOK_BY_BID);
			pst.setInt(1, bid);
    		rs = pst.executeQuery();
			while (rs.next()) {
				resultBook.setBookId(rs.getInt("bid"));
				resultBook.setType(rs.getString("publication_type"));
				resultBook.setAuthors(rs.getString("authors"));
				resultBook.setEditors(rs.getString("editor"));
				resultBook.setTitle(rs.getString("title"));
				resultBook.setBookTitle(rs.getString("booktitle"));
				resultBook.setPages(rs.getString("pages"));
				resultBook.setYear(rs.getString("year"));
				resultBook.setAddress(rs.getString("address"));
				resultBook.setJournal(rs.getString("journal"));
				resultBook.setVolume(rs.getString("volume"));
				resultBook.setNumber(rs.getString("number"));
				resultBook.setMonth(rs.getString("month"));
				resultBook.setUrl(rs.getString("url"));
				resultBook.setEe(rs.getString("ee"));
				resultBook.setCdrom(rs.getString("cdrom"));
				resultBook.setCite(rs.getString("cite"));
				resultBook.setPublisher(rs.getString("publisher"));
				resultBook.setNote(rs.getString("note"));
				resultBook.setCrossref(rs.getString("crossref"));
				resultBook.setIsbn(rs.getString("isbn"));
				resultBook.setSchool(rs.getString("school"));
				resultBook.setSeries(rs.getString("series"));
				resultBook.setChapter(rs.getString("chapter"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultBook;
	}

	public ArrayList<BookBean> searchBooks(String key, String value) {
		ArrayList<BookBean> resultBooks = new ArrayList<BookBean>();
		String SEARCH_BOOKS = "select * from books where " + key + " like '%" + value + "%'";
		try {
			pst = con.prepareStatement(SEARCH_BOOKS);
    		rs = pst.executeQuery();
			BookBean resultBook;
			while (rs.next()) {
				resultBook = new BookBean();
				resultBook.setBookId(rs.getInt("bid"));
				resultBook.setType(rs.getString("publication_type"));
				resultBook.setAuthors(rs.getString("authors"));
				resultBook.setEditors(rs.getString("editor"));
				resultBook.setTitle(rs.getString("title"));
				resultBook.setBookTitle(rs.getString("booktitle"));
				resultBook.setPages(rs.getString("pages"));
				resultBook.setYear(rs.getString("year"));
				resultBook.setAddress(rs.getString("address"));
				resultBook.setJournal(rs.getString("journal"));
				resultBook.setVolume(rs.getString("volume"));
				resultBook.setNumber(rs.getString("number"));
				resultBook.setMonth(rs.getString("month"));
				resultBook.setUrl(rs.getString("url"));
				resultBook.setEe(rs.getString("ee"));
				resultBook.setCdrom(rs.getString("cdrom"));
				resultBook.setCite(rs.getString("cite"));
				resultBook.setPublisher(rs.getString("publisher"));
				resultBook.setNote(rs.getString("note"));
				resultBook.setCrossref(rs.getString("crossref"));
				resultBook.setIsbn(rs.getString("isbn"));
				resultBook.setSchool(rs.getString("school"));
				resultBook.setSeries(rs.getString("series"));
				resultBook.setChapter(rs.getString("chapter"));
				resultBooks.add(resultBook);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultBooks;
	}

	public ArrayList<BookBean> searchBooks(String publicationType, String authors, String editors, 
			String title, String journal, String year, String isbn) {
		ArrayList<BookBean> resultBooks = new ArrayList<BookBean>();
		int keyCount = 0;
		String SEARCH_BOOKS = "select * from books where ";
		if (!publicationType.equals("")) {
			SEARCH_BOOKS += "publication_type like '%" + publicationType + "%'";
			keyCount ++;
		}
		if (!authors.equals("")) {
			if (keyCount > 0)
				SEARCH_BOOKS += " and ";
			SEARCH_BOOKS += "authors like '%" + authors + "%'";
			keyCount ++;
		}
		if (!editors.equals("")) {
			if (keyCount > 0)
				SEARCH_BOOKS += " and ";
			SEARCH_BOOKS += "editors like '%" + editors + "%'";
			keyCount ++;
		}
		if (!title.equals("")) {
			if (keyCount > 0)
				SEARCH_BOOKS += " and ";
			SEARCH_BOOKS += "title like '%" + title + "%'";
			keyCount ++;
		}
		if (!journal.equals("")) {
			if (keyCount > 0)
				SEARCH_BOOKS += " and ";
			SEARCH_BOOKS += "journal like '%" + journal + "%'";
			keyCount ++;
		}
		if (!year.equals("")) {
			if (keyCount > 0)
				SEARCH_BOOKS += " and ";
			SEARCH_BOOKS += "year like '%" + year + "%'";
			keyCount ++;
		}
		if (!isbn.equals("")) {
			if (keyCount > 0)
				SEARCH_BOOKS += " and ";
			SEARCH_BOOKS += "isbn like '%" + isbn + "%'";
			keyCount ++;
		}
		try {
			pst = con.prepareStatement(SEARCH_BOOKS);
    		rs = pst.executeQuery();
			BookBean resultBook;
			while (rs.next()) {
				resultBook = new BookBean();
				resultBook.setBookId(rs.getInt("bid"));
				resultBook.setType(rs.getString("publication_type"));
				resultBook.setAuthors(rs.getString("authors"));
				resultBook.setEditors(rs.getString("editor"));
				resultBook.setTitle(rs.getString("title"));
				resultBook.setBookTitle(rs.getString("booktitle"));
				resultBook.setPages(rs.getString("pages"));
				resultBook.setYear(rs.getString("year"));
				resultBook.setAddress(rs.getString("address"));
				resultBook.setJournal(rs.getString("journal"));
				resultBook.setVolume(rs.getString("volume"));
				resultBook.setNumber(rs.getString("number"));
				resultBook.setMonth(rs.getString("month"));
				resultBook.setUrl(rs.getString("url"));
				resultBook.setEe(rs.getString("ee"));
				resultBook.setCdrom(rs.getString("cdrom"));
				resultBook.setCite(rs.getString("cite"));
				resultBook.setPublisher(rs.getString("publisher"));
				resultBook.setNote(rs.getString("note"));
				resultBook.setCrossref(rs.getString("crossref"));
				resultBook.setIsbn(rs.getString("isbn"));
				resultBook.setSchool(rs.getString("school"));
				resultBook.setSeries(rs.getString("series"));
				resultBook.setChapter(rs.getString("chapter"));
				resultBooks.add(resultBook);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultBooks;
	}

	public int nextSid() {
		try {
			pst = con.prepareStatement(SEARCH_SELL_RECORD_MAX_SID);
			rs = pst.executeQuery();
			rs.next();
			int result = rs.getInt("max(sid)") + 1;
			rs.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void addSell(int uid, int bid, int price, String picture) {
		try {
			pst = con.prepareStatement(INSERT_SELL_RECORD);
			pst.setInt(1, bid);
			pst.setInt(2, uid);
			pst.setInt(3, price);
			pst.setString(4, picture);
			pst.setString(5, "pending");
			pst.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<OrdProcessBean> searchSells(int uid) {
		ArrayList<OrdProcessBean> OPBs = new ArrayList<OrdProcessBean>();
		try {
			pst = con.prepareStatement(SEARCH_SELL_RECORD_BY_UID);
			pst.setInt(1, uid);
    		rs = pst.executeQuery();
    		OrdProcessBean OPB;
			while (rs.next()) {
				OPB = new OrdProcessBean();
				OPB.setSellId(rs.getInt("sid"));
				OPB.setBookId(rs.getInt("bid"));
				OPB.setUserId(rs.getInt("uid"));
				OPB.setPrice(rs.getInt("price"));
				OPB.setPicture(rs.getString("picture"));
				OPB.setStatus(rs.getString("status"));
				OPBs.add(OPB);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return OPBs;
	}

	public void activateSell(int sid) {
		try {
			pst = con.prepareStatement(ACTIVATE_SELL_RECORD_BY_SID);
			pst.setInt(1, sid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unactivateSell(int sid) {
		try {
			pst = con.prepareStatement(UNACTIVATE_SELL_RECORD_BY_SID);
			pst.setInt(1, sid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(int sid) {
		try {
			pst = con.prepareStatement(REMOVE_SELL_RECORD_BY_SID);
			pst.setInt(1, sid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unbanUser(int uid) {
		try {
			pst = con.prepareStatement(UNBAN_USER_BY_UID);
			pst.setInt(1, uid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void banUser(int uid) {
		try {
			pst = con.prepareStatement(BAN_USER_BY_UID);
			pst.setInt(1, uid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BookBean> searchBookSells(BookBean book) {
		ArrayList<BookBean> books = new ArrayList<BookBean>();
		BookBean tempBook;
		try {
			pst = con.prepareStatement(SEARCH_SELL_RECORD_BY_BID);
			pst.setInt(1, book.getBookId());
    		rs = pst.executeQuery();
			while (rs.next()) {
				tempBook = new BookBean();
				tempBook.setAll(book);
				tempBook.setPrice(rs.getInt("price"));
				tempBook.setPicture(rs.getString("picture"));
				tempBook.setUid(rs.getInt("uid"));
				tempBook.setStatus(rs.getString("status"));
				tempBook.setSid(rs.getInt("sid"));
				System.out.println(rs.getInt("sid"));
				if (tempBook.getStatus().equals("good")) {
					books.add(tempBook);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public int addBook(BookBean book) {
		try {
			pst = con.prepareStatement(INSERT_BOOK);
			pst.setString(1, book.getType());
			pst.setString(2, book.getAuthors());
			pst.setString(3, book.getEditors());
			pst.setString(4, book.getTitle());
			pst.setString(5, book.getBookTitle());
			pst.setString(6, book.getPages());
			pst.setString(7, book.getYear());
			pst.setString(8, book.getAddress());
			pst.setString(9, book.getJournal());
			pst.setString(10, book.getVolume());
			pst.setString(11, book.getNumber());
			pst.setString(12, book.getMonth());
			pst.setString(13, book.getUrl());
			pst.setString(14, book.getEe());
			pst.setString(15, book.getCdrom());
			pst.setString(16, book.getCite());
			pst.setString(17, book.getPublisher());
			pst.setString(18, book.getNote());
			pst.setString(19, book.getCrossref());
			pst.setString(20, book.getIsbn());
			pst.setString(21, book.getSeries());
			pst.setString(22, book.getSchool());
			pst.setString(23, book.getChapter());
			pst.executeUpdate();
			pst = con.prepareStatement(SEARCH_BOOK_MAX_BID);
			rs = pst.executeQuery();
			rs.next();
			int result = rs.getInt("max(bid)");
			rs.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<CustomerBean> searchUsers(String key, String value) {
		ArrayList<CustomerBean> resultUsers = new ArrayList<CustomerBean>();
		String SEARCH_USER = "select * from users where " + key + " like ?";
		try {
			pst = con.prepareStatement(SEARCH_USER);
			pst.setString(1, "%"+value+"%");
    		rs = pst.executeQuery();
			CustomerBean resultUser;
			while (rs.next()) {
				resultUser = new CustomerBean();
				resultUser.setUid(rs.getInt("uid"));
				resultUser.setName(rs.getString("username"));
				resultUser.setEmail(rs.getString("email"));
				resultUser.setFirstname(rs.getString("firstname"));
				resultUser.setLastname(rs.getString("lastname"));
				resultUser.setAddress(rs.getString("address"));
				resultUser.setBirthyear(rs.getString("birthyear"));
				resultUser.setCredit(rs.getString("creditcard"));
				resultUser.setStatus(rs.getString("status"));	
				resultUsers.add(resultUser);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultUsers;
	}

	public ArrayList<OrdProcessBean> getCusAct(int uid) {
		ArrayList<OrdProcessBean> resultCusActs = new ArrayList<OrdProcessBean>();
		try {
			pst = con.prepareStatement(SEARCH_CUS_ACTIVITY_BY_UID);
			pst.setInt(1, uid);
    		rs = pst.executeQuery();
    		OrdProcessBean resultCusAct;
    		String timestamp;
			while (rs.next()) {
				resultCusAct = new OrdProcessBean();
				resultCusAct.setUserId(rs.getInt("uid"));
				resultCusAct.setSellId(rs.getInt("sid"));
				resultCusAct.setAction(rs.getString("action"));
				timestamp = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").format(rs.getTimestamp("time"));
				resultCusAct.setTime(timestamp);	
				resultCusActs.add(resultCusAct);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultCusActs;
	}
}
