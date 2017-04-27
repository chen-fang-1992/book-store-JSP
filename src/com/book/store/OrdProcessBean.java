package com.book.store;

import java.util.ArrayList;

public class OrdProcessBean {

	private int sellId;
	private int buyId;
	private int userId;
	private int bookId;
	private BookBean bb;
	private int price;
	private String picture;
	private String status;
	private String action;
	private String time;
	private String sellerName;

	public OrdProcessBean() {}

	public OrdProcessBean(int userId, int bookId, int price, String picture) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.price = price;
		this.picture = picture;
	}

	public OrdProcessBean(int userId, int sellId) {
		super();
		this.userId = userId;
		this.sellId = sellId;
	}
	
	public int getSellId() {
		return this.sellId;
	}
	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
	public int getBuyId(){
		return this.buyId;
	}
	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return this.bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public String getPicture(){
		return this.picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public BookBean getBb(){
		return this.bb;
	}
	public void setBb(BookBean bb){
		this.bb = bb;
	}
	public String getAction(){
		return this.action;
	}
	public void setAction(String action){
		this.action = action;
	}
	public String getTime(){
		return this.time;
	}
	public void setTime(String time){
		this.time = time;
	}
	public String getSellerName(){
		return this.sellerName;
	}
	public void setSellerName(String sellerName){
		this.sellerName = sellerName;
	}

	public int nextSid() {
		SqlConnect Sqlhandle = new SqlConnect();
		int sid = Sqlhandle.nextSid();
		Sqlhandle.close();
		return sid;
	}

	public void addSell() {
		SqlConnect Sqlhandle = new SqlConnect();
		Sqlhandle.addSell(userId, bookId, price, picture);
		Sqlhandle.close();
	}

	public ArrayList<OrdProcessBean> searchSells() {
		SqlConnect Sqlhandle = new SqlConnect();
		ArrayList<OrdProcessBean> OPBs = Sqlhandle.searchSells(userId);		
		ArrayList<OrdProcessBean> resultOPBs = new ArrayList<OrdProcessBean>();

		for (int i = 0; i < OPBs.size(); i++) {
			OrdProcessBean OPB = OPBs.get(i);
			OPB.bb = Sqlhandle.searchBook(OPB.getBookId());
			resultOPBs.add(OPB);
		}

		Sqlhandle.close();
		return resultOPBs;
	}
	
	public OrdProcessBean bookDetailGet() {
		SqlConnect Sqlhandle = new SqlConnect();
		OrdProcessBean OPB = Sqlhandle.searchSellRecordBySid(sellId);
		OPB.bb = Sqlhandle.searchBook(OPB.getBookId());
		Sqlhandle.close();
		return OPB;
	}

	public void activateSell() {
		SqlConnect Sqlhandle = new SqlConnect();
		Sqlhandle.activateSell(sellId);
		Sqlhandle.close();
	}

	public String payment(int userId, ArrayList<OrdProcessBean> OPBs) {
//		EmailService emailHandler = new EmailService();	
		SqlConnect Sqlhandle = new SqlConnect();
//		String fromEmail = "haofang03@gmail.com";
//		String emailName = "haofang03";
//		String emailPwd = "qwerasdf12";		
//		String subject = "no-reply.item sold";
		for(int i=0; i < OPBs.size(); i++){
			int sid = OPBs.get(i).getSellId();
			Sqlhandle.insertBuy(userId,sid);
			Sqlhandle.insertCus(userId,sid,"buy");
//			String username = Sqlhandle.findUsername(userId);
//			String toEmail = Sqlhandle.findEmail(sid);
//			String message = "Your book has been bought by " + username;
//			emailHandler.sendEmail(fromEmail, emailName, emailPwd, toEmail, subject, message);
		}

		Sqlhandle.close();
		return "success";
	}

	public void insertCus(int uid, int sid, String action) {
		SqlConnect Sqlhandle = new SqlConnect();
		Sqlhandle.insertCus(uid,sid,action);
		Sqlhandle.close();
	}

	public void unactivateSell() {
		SqlConnect Sqlhandle = new SqlConnect();
		Sqlhandle.unactivateSell(sellId);
		Sqlhandle.close();
	}

	public void remove() {
		SqlConnect Sqlhandle = new SqlConnect();
		Sqlhandle.remove(sellId);
		Sqlhandle.close();
	}

	public ArrayList<OrdProcessBean> getCusAct(int uid) {
		SqlConnect Sqlhandle = new SqlConnect();
		ArrayList<OrdProcessBean> resultCusActs = Sqlhandle.getCusAct(uid);
		ArrayList<OrdProcessBean> newResultCusActs = new ArrayList<OrdProcessBean>();
		for(int i = 0; i< resultCusActs.size();i++){
			OrdProcessBean OPB = resultCusActs.get(i);	
			OPB = OPB.bookDetailGet();
			OPB.setTime(resultCusActs.get(i).getTime());
			OPB.setAction(resultCusActs.get(i).getAction());
			OPB.setSellerName(Sqlhandle.getUsername(OPB.getSellId()));
			newResultCusActs.add(OPB);
		}
		Sqlhandle.close();
		return newResultCusActs;
	}
}