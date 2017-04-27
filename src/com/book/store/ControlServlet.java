package com.book.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URLEncoder;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

//import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ControlServlet
 */
@WebServlet(urlPatterns="/control",displayName="ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass().getName());
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlServlet() {
		super();
	}
    
	//Do the parsing when starting up
	@Override
	public void init() throws ServletException {
		super.init();
		BookBean bb = new BookBean();
		ArrayList<BookBean> randBooks = bb.getRandBooks();
		getServletContext().setAttribute("randbooks", randBooks);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("random")) {
			BookBean bb = new BookBean();
			ArrayList<BookBean> randBooks = bb.getRandBooks();
			getServletContext().setAttribute("randbooks", randBooks);
			response.sendRedirect("welcome.jsp");
		} else if (action.equals("search")) {
    		ArrayList<BookBean> resultBooks = new ArrayList<BookBean>();
			String searchType = request.getParameter("search_type");
			if (searchType.equals("welcome_search")) {
				String value = request.getParameter("search_value");
				String key = request.getParameter("key");
				Search search = new Search(searchType, value, key);
				resultBooks = search.doSearch();
		    } else if (searchType.equals("advanced_search")) {
				String publicationType = request.getParameter("Publication-type");
				String authors = request.getParameter("Authors");
				String editors = request.getParameter("Editors");
				String title = request.getParameter("Title");
				String journal = request.getParameter("Journal");
				String year = request.getParameter("Year");
				String isbn = request.getParameter("Isbn");
				Search search = new Search(searchType, publicationType, authors, editors, 
						title, journal, year, isbn);
				resultBooks = search.doSearch();
			}

			if (searchType.equals("welcome_search") || searchType.equals("advanced_search")) {
				BookBean resultBook = null;
				ArrayList<BookBean> showBooks = new ArrayList<BookBean>();
				for (int i = 0; i < resultBooks.size(); i++) {
					resultBook = resultBooks.get(i);
					showBooks.addAll(resultBook.getSellItems(resultBook));
				}
				getServletContext().setAttribute("showbookscount", showBooks.size()/10);
				getServletContext().setAttribute("showbooks", showBooks);
				response.sendRedirect("search/results.jsp?page=0");
			}

			if (searchType.equals("sell_search")) {
				String value = request.getParameter("search_value");
				String key = request.getParameter("key");
				Search search = new Search(searchType, value, key);
				resultBooks = search.doSearch();
				getServletContext().setAttribute("sellsearchbookscount", resultBooks.size()/10);
				getServletContext().setAttribute("sellsearchbooks", resultBooks);
				response.sendRedirect("search/sell_search_results.jsp?page=0");
			}

			if(searchType.equals("admin_search")){
				String value = request.getParameter("search_value");
				String key = request.getParameter("key");
				Search search = new Search(searchType, value, key);
				resultBooks = search.doSearch();
				BookBean resultBook = null;
				ArrayList<BookBean> adminbooks = new ArrayList<BookBean>();
				for (int i = 0; i < resultBooks.size(); i++) {
					resultBook = resultBooks.get(i);
					adminbooks.addAll(resultBook.getSellItems(resultBook));
				}

				getServletContext().setAttribute("adminbookscount", adminbooks.size()/10);
				getServletContext().setAttribute("adminbooks", adminbooks);
				response.sendRedirect("admin/item_manage.jsp?value="+value+"&key="+key+"&page=0");
			}

			if(searchType.equals("customer_search")){
				String value = request.getParameter("search_value");
				String key = request.getParameter("key");
				Search search = new Search(searchType, value, key);
				ArrayList<CustomerBean> resultUsers = new ArrayList<CustomerBean>();
				resultUsers = search.customerSearch();
				getServletContext().setAttribute("adminuserscount", resultUsers.size()/10);
				getServletContext().setAttribute("adminusers", resultUsers);
				response.sendRedirect("admin/customer_manage.jsp?value="+value+"&key="+key+"&page=0");	
			}
		} else if (action.equals("advanced_search")) {
			response.sendRedirect("search/advanced_search.jsp");
		} else if (action.equals("create")) {
			response.sendRedirect("user/add_item.jsp");
		} else if (action.equals("selling")) {
			CustomerBean cb = (CustomerBean) request.getSession().getAttribute("customerbean");
			int userid = cb.getLoginId(cb.getName());
			OrdProcessBean ordProcess = new OrdProcessBean();
			ordProcess.setUserId(userid);
			ArrayList<OrdProcessBean> allSells = ordProcess.searchSells();
			getServletContext().setAttribute("allsellscount", allSells.size()/10);
			getServletContext().setAttribute("allsells", allSells);
			response.sendRedirect("user/selling.jsp?page=0");
		} else if (action.equals("add_sell")) {
			String bookId = request.getParameter("bookid");
			response.sendRedirect("user/add_sell.jsp?bookid="+bookId);	
		} else if (action.equals("add_cart")) {
			int userId = -1;
			String sid = request.getParameter("sid");
			HttpSession session = request.getSession();
			ArrayList<OrdProcessBean> cartList =null;
			Object objCartList = session.getAttribute("cart");
			if (objCartList != null) {
				cartList = (ArrayList<OrdProcessBean>) objCartList ;
			} else {
				cartList = new ArrayList<OrdProcessBean>();
				session.setAttribute("cart", cartList);
			}
			int sellId = Integer.parseInt(sid);
			CustomerBean cb = (CustomerBean) request.getSession().getAttribute("customerbean");

			if (cb != null) {
				userId = cb.getLoginId(cb.getName());
			}
			OrdProcessBean ordProcess = new OrdProcessBean(userId,sellId);
			ordProcess = ordProcess.bookDetailGet();
			cartList.add(ordProcess);
			ordProcess.insertCus(userId,sellId,"add");
			getServletContext().setAttribute("cart", cartList);
			response.sendRedirect("user/shoppingcart.jsp");
		} else if (action.equals("removefromcart")){
			OrdProcessBean ordProcess = new OrdProcessBean();
			HttpSession session = request.getSession();
			CustomerBean cb = (CustomerBean) session.getAttribute("customerbean");
			int userId = cb.getLoginId(cb.getName());
			ArrayList<OrdProcessBean> cartList =null;
			Object objCartList = session.getAttribute("cart");

			if (objCartList != null) {
				cartList = (ArrayList<OrdProcessBean>) objCartList ;
			} else {
				cartList = new ArrayList<OrdProcessBean>();
				session.setAttribute("cart", cartList);
			}

			String[] checkbox = request.getParameterValues("cart_checkbox");
			if (checkbox != null) {
				for (int i = 0; i < checkbox.length; i++) {
					int deleteSid = Integer.parseInt(checkbox[i]);
					for (int j = 0; j < cartList.size(); j++) {
						if (cartList.get(j).getSellId() == deleteSid) {
							cartList.remove(j);
							ordProcess.insertCus(userId,deleteSid,"remove");
						}
					}
				}
			}
			getServletContext().setAttribute("cart", cartList);
			response.sendRedirect("user/shoppingcart.jsp");
		} else if (action.equals("pay")) {
			HttpSession session = request.getSession();
			CustomerBean cb = (CustomerBean) request.getSession().getAttribute("customerbean");
			int userId = cb.getLoginId(cb.getName());
			ArrayList<OrdProcessBean> cartList = (ArrayList<OrdProcessBean>) session.getAttribute("cart");
			OrdProcessBean ordProcess = new OrdProcessBean();
			String state = ordProcess.payment(userId,cartList);

			if (state.equals("success")) {
				response.sendRedirect("user/payment_result.jsp?state="+state);
			} else {
				response.sendRedirect("user/payment_result.jsp?state="+state);
			}
		} else if (action.equals("cus_activity")) {
			String userId = request.getParameter("userid");
			String page = request.getParameter("page");
			int uid = Integer.parseInt(userId);
			OrdProcessBean getactivity = new OrdProcessBean();
			ArrayList<OrdProcessBean> resultCusActs =  getactivity.getCusAct(uid);
			getServletContext().setAttribute("resultcusactscount", resultCusActs.size()/10);
			getServletContext().setAttribute("resultcusacts", resultCusActs);
			response.sendRedirect("admin/customer_detail.jsp?userid="+userId+"&page="+page);
		} else if (action.equals("shopping_cart")) {
			response.sendRedirect("user/shoppingcart.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			MultipartRequest multi = null; 
			int maxPostSize = 1 * 100 * 1024 * 1024;
			String uploadPath = request.getSession().getServletContext().getRealPath("/")+"uploads\\";
			File f = new File(uploadPath);
			if (!f.exists()) {   
			  f.mkdir();
			}
			multi = new MultipartRequest(request, uploadPath, maxPostSize, "GBK");
			String action = multi.getParameter("action") == null ? "" : multi.getParameter("action");
			int bookId = 0;

			if (action.equals("add_new_item")) {
				BookBean addBook = new BookBean();
				String type = multi.getParameter("Publication-type") 
						== null ? "" : multi.getParameter("Publication-type");
				addBook.setType(new String(type.getBytes("ISO8859_1"), "GBK"));
				String authors = multi.getParameter("Authors")
						== null ? "" : multi.getParameter("Authors");
				addBook.setAuthors(new String(authors.getBytes("ISO8859_1"), "GBK"));
				String editors = multi.getParameter("Editors")
						== null ? "" : multi.getParameter("Editors");
				addBook.setEditors(new String(editors.getBytes("ISO8859_1"), "GBK"));
				String address = multi.getParameter("Address")
						== null ? "" : multi.getParameter("Address");
				addBook.setAddress(new String(address.getBytes("ISO8859_1"), "GBK"));
				String title = multi.getParameter("Title") == null ? "" : multi.getParameter("Title");
				addBook.setTitle(new String(title.getBytes("ISO8859_1"), "GBK"));
				String booktitle = multi.getParameter("Booktitle") 
						== null ? "" : multi.getParameter("Booktitle");
				addBook.setBookTitle(new String(booktitle.getBytes("ISO8859_1"), "GBK"));
				String pages = multi.getParameter("Pages") == null ? "" : multi.getParameter("Pages");
				addBook.setPages(new String(pages.getBytes("ISO8859_1"), "GBK"));
				String year = multi.getParameter("Year") == null ? "" : multi.getParameter("Year");
				addBook.setYear(new String(year.getBytes("ISO8859_1"), "GBK"));
				String journal = multi.getParameter("Journal")
						== null ? "" : multi.getParameter("Journal");
				addBook.setJournal(new String(journal.getBytes("ISO8859_1"), "GBK"));
				String volume = multi.getParameter("Volume")
						== null ? "" : multi.getParameter("Volume");
				addBook.setVolume(new String(volume.getBytes("ISO8859_1"), "GBK"));
				String number = multi.getParameter("Number")
						== null ? "" : multi.getParameter("Number");
				addBook.setNumber(new String(number.getBytes("ISO8859_1"), "GBK"));
				String month = multi.getParameter("Month") == null ? "" : multi.getParameter("Month");
				addBook.setMonth(new String(month.getBytes("ISO8859_1"), "GBK"));
				String url = multi.getParameter("Url") == null ? "" : multi.getParameter("Url");
				addBook.setUrl(new String(url.getBytes("ISO8859_1"), "GBK"));
				String ee = multi.getParameter("Ee") == null ? "" : multi.getParameter("Ee");
				addBook.setEe(new String(ee.getBytes("ISO8859_1"), "GBK"));
				String cite = multi.getParameter("Cite") == null ? "" : multi.getParameter("Cite");
				addBook.setCite(new String(cite.getBytes("ISO8859_1"), "GBK"));
				String school = multi.getParameter("School") == null ? "" : multi.getParameter("School");
				addBook.setSchool(new String(school.getBytes("ISO8859_1"), "GBK"));
				String publisher = multi.getParameter("Publisher")
						== null ? "" : multi.getParameter("Publisher");
				addBook.setPublisher(new String(publisher.getBytes("ISO8859_1"), "GBK"));
				String note = multi.getParameter("Note") == null ? "" : multi.getParameter("Note");
				addBook.setNote(new String(note.getBytes("ISO8859_1"), "GBK"));
				String cdrom = multi.getParameter("Cdrom") == null ? "" : multi.getParameter("Cdrom");
				addBook.setCdrom(new String(cdrom.getBytes("ISO8859_1"), "GBK"));
				String crossref = multi.getParameter("Crossref")
						== null ? "" : multi.getParameter("Crossref");
				addBook.setCrossref(new String(crossref.getBytes("ISO8859_1"), "GBK"));
				String isbn = multi.getParameter("Isbn") == null ? "" : multi.getParameter("Isbn");
				addBook.setIsbn(new String(isbn.getBytes("ISO8859_1"), "GBK"));
				String chapter = multi.getParameter("Chapter")
						== null ? "" : multi.getParameter("Chapter");
				addBook.setChapter(new String(chapter.getBytes("ISO8859_1"), "GBK"));
				String series = multi.getParameter("Series") == null ? "" : multi.getParameter("Series");
				addBook.setSeries(new String(series.getBytes("ISO8859_1"), "GBK"));
				bookId = addBook.addBook(addBook);
			} else if (action.equals("do_sell")) {
				String bid = multi.getParameter("bookid") == null ? "" : multi.getParameter("bookid");
				bid = new String(bid.getBytes("ISO8859_1"), "GBK");
				bookId = Integer.parseInt(bid);
			}

			if (action.equals("add_new_item") || action.equals("do_sell")) {
				OrdProcessBean opb = new OrdProcessBean();
				int sid = opb.nextSid();
				@SuppressWarnings("rawtypes")
				Enumeration files = multi.getFileNames();
				String oldName = "";
				String newName = "";
				while (files.hasMoreElements()) {
					String strFile = (String) files.nextElement();
					File oldFile = multi.getFile(strFile);
					int attachSize = (int) oldFile.length();
					oldName = multi.getFilesystemName(strFile);
					newName = sid + oldName.substring(oldName.indexOf("."), oldName.length());
					String newPath = uploadPath + newName; 
					String fileSuffix = oldName.substring(oldName.indexOf(".")+1, oldName.length());
					oldName = uploadPath + oldName;
					boolean isFlag = (fileSuffix.equals("jpg") || fileSuffix.equals("png")
							|| fileSuffix.equals("gif")) && attachSize < 307200;
					if (isFlag) {
						FileInputStream fis = new FileInputStream(oldFile);
						FileOutputStream fos = new FileOutputStream(newPath);
						byte [] b = new byte[1024];
						int n = 0, all = 0;
						while (true) {
							n = fis.read(b);
							all = all + n;
							if(n == -1) break;
							fos.write(b,0,n);
						}
						fis.close();
						fos.close();
					}
					File deleteFile = new File(oldName);
					deleteFile.delete();
				}
				CustomerBean cb = (CustomerBean) request.getSession().getAttribute("customerbean");
				int userId = cb.getLoginId(cb.getName());
				String pri = multi.getParameter("price") == null ? "" : multi.getParameter("price");
				pri = new String(pri.getBytes("ISO8859_1"), "GBK");
				int price = Integer.parseInt(pri);
				String picture = URLEncoder.encode("uploads\\"+newName, "UTF-8");
				opb = new OrdProcessBean(userId, bookId, price, picture);
				opb.addSell();
				ArrayList<OrdProcessBean> allSells = opb.searchSells();
				getServletContext().setAttribute("allsellscount", allSells.size()/10);
				getServletContext().setAttribute("allsells", allSells);
				response.sendRedirect("user/selling.jsp?page=0");
			}
		} else {
			String action = request.getParameter("action");

			if (action.equals("login")) {
				String url = request.getSession().getAttribute("url").toString();
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				CustomerBean loginService = new CustomerBean(username, password);
				String state = loginService.authenticate();

				if (state.equals("success")) {
					String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date());
					HttpSession session = request.getSession();
					session.setAttribute("logintime", timeStamp);
					loginService = loginService.getUserInfo(username);
					session.setAttribute("customerbean", loginService);
					response.sendRedirect(url);
					return;
				} else {
					request.getSession().setAttribute("state", state);
					response.sendRedirect("user/login.jsp");
					return;
				}
			} else if (action.equals("register")) {
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String retype = request.getParameter("retype");
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String address = request.getParameter("address");
				String birthyear = request.getParameter("birthyear");
				String creditcard = request.getParameter("creditcard");
				CustomerBean registerService = new CustomerBean(username,password,retype,firstname,
					lastname,email,birthyear,address,creditcard);
				String state = registerService.insertUser();

				if (state.equals("success")) {
					response.sendRedirect("user/register_success.jsp");
					return;
				} else {
					request.getSession().setAttribute("state", state);
					response.sendRedirect("user/register.jsp");
					return;
				}
			} else if (action.equals("profile")) {
				CustomerBean cb = (CustomerBean) request.getSession().getAttribute("customerbean");
				cb = cb.getUserInfo(cb.getName());
				request.getSession().setAttribute("customerbean", cb);
				response.sendRedirect("user/profile.jsp");
			} else if (action.equals("change_profile")) {
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String password = request.getParameter("password");			
				String retype = request.getParameter("password");
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String address = request.getParameter("address");
				String birthyear = request.getParameter("birthyear");
				String creditcard = request.getParameter("creditcard");
				CustomerBean cb = (CustomerBean) request.getSession().getAttribute("customerbean");
				int userId = cb.getLoginId(cb.getName());
				CustomerBean updateService = new CustomerBean(username,password,retype,firstname,
					lastname,email,birthyear,address,creditcard);
				updateService.setUid(userId);
				updateService.setName(cb.getName());
				updateService.updateProfile();
				cb = cb.getUserInfo(userId);
				request.getSession().setAttribute("customerbean", cb);
				response.sendRedirect("user/profile.jsp");
			} else if (action.equals("activate_sell")) {
				String[] checkbox = request.getParameterValues("activate_checkbox");
				if (checkbox != null) {
					for (int i = 0; i < checkbox.length; i++) {
						OrdProcessBean ordProcess = new OrdProcessBean();
						ordProcess.setSellId(Integer.parseInt(checkbox[i]));
						ordProcess.activateSell();
					}
					CustomerBean cb = (CustomerBean) request.getSession().getAttribute("customerbean");
					int userId = cb.getLoginId(cb.getName());
					OrdProcessBean ordProcess = new OrdProcessBean();
					ordProcess.setUserId(userId);
					ArrayList<OrdProcessBean> allSells = ordProcess.searchSells();
				
					getServletContext().setAttribute("allsellscount",allSells.size()/10);
					getServletContext().setAttribute("allsells",allSells);
					response.sendRedirect("user/selling.jsp?page=0");
				} else {
					response.sendRedirect("user/selling.jsp?page=0");
				}
			} else if (action.equals("unactivate_sell")) {
				String[] checkbox = request.getParameterValues("unactivate_checkbox");
				if (checkbox != null) {
					for (int i = 0; i < checkbox.length; i++) {
						OrdProcessBean ordProcess = new OrdProcessBean();
						ordProcess.setSellId(Integer.parseInt(checkbox[i]));
						ordProcess.unactivateSell();
					}
					CustomerBean cb = (CustomerBean) request.getSession().getAttribute("customerbean");
					int userId = cb.getLoginId(cb.getName());
					OrdProcessBean ordProcess = new OrdProcessBean();
					ordProcess.setUserId(userId);
					ArrayList<OrdProcessBean> allSells = ordProcess.searchSells();
				
					getServletContext().setAttribute("allsellscount", allSells.size()/10);
					getServletContext().setAttribute("allsells", allSells);
					response.sendRedirect("user/selling.jsp?page=0");
				} else {
					response.sendRedirect("user/selling.jsp?page=0");
				}
			} else if (action.equals("adminlogin")) {
				String administrator = request.getParameter("administrator");
				String password = request.getParameter("password");
				Administration adminService = new Administration(administrator, password);
				String state = adminService.authenticate();
				if (state.equals("success")) {
					request.getSession().setAttribute("admin", adminService);
					response.sendRedirect("admin/admin_manage.jsp");
					return;
				} else {
					request.getSession().setAttribute("state", state);
					response.sendRedirect("admin/admin_login.jsp");
					return;
				}
			} else if (action.equals("recovery")) {
				ArrayList<BookBean> resultBooks = new ArrayList<BookBean>();
				String[] checkbox = request.getParameterValues("recovery_checkbox");
				String value = request.getParameter("value");
				String key = request.getParameter("key");
				String page = request.getParameter("page");
				if (checkbox != null) {
					for (int i = 0; i < checkbox.length; i++) {
						OrdProcessBean ordprocess = new OrdProcessBean();
						ordprocess.setSellId(Integer.parseInt(checkbox[i]));
						ordprocess.activateSell();
					}
					Search search = new Search("admin_search", value, key);
					resultBooks = search.doSearch();
					BookBean resultBook = null;
					ArrayList<BookBean> adminBooks = new ArrayList<BookBean>();
					for (int i = 0; i < resultBooks.size(); i++) {
						resultBook = resultBooks.get(i);
						adminBooks.addAll(resultBook.getSellItems(resultBook));
					}
					getServletContext().setAttribute("adminbookscount",adminBooks.size()/10);
					getServletContext().setAttribute("adminbooks",adminBooks);
					response.sendRedirect("admin/item_manage.jsp?value="+value+"&key="+key+"&page="+page);
				} else
					response.sendRedirect("admin/item_manage.jsp?value="+value+"&key="+key+"&page="+page);
			} else if (action.equals("remove")) {
				ArrayList<BookBean> resultBooks = new ArrayList<BookBean>();
				String[] checkbox = request.getParameterValues("remove_checkbox");
				String value = request.getParameter("value");
				String key = request.getParameter("key");
				String page = request.getParameter("page");
				if (checkbox != null) {
					for (int i = 0; i < checkbox.length; i++) {
						OrdProcessBean ordProcess = new OrdProcessBean();
						ordProcess.setSellId(Integer.parseInt(checkbox[i]));
						ordProcess.remove();
					}
					Search search = new Search("admin_search", value, key);
					resultBooks = search.doSearch();
					BookBean resultBook = null;
					ArrayList<BookBean> adminBooks = new ArrayList<BookBean>();
					for (int i = 0; i < resultBooks.size(); i++) {
						resultBook = resultBooks.get(i);
						adminBooks.addAll(resultBook.getSellItems(resultBook));
					}
					getServletContext().setAttribute("adminbookscount", adminBooks.size()/10);
					getServletContext().setAttribute("adminbooks", adminBooks);
					response.sendRedirect("admin/item_manage.jsp?value="+value+"&key="+key+"&page="+page);
				} else {
					response.sendRedirect("admin/item_manage.jsp?value="+value+"&key="+key+"&page="+page);
				}
			} else if (action.equals("unban")) {
				ArrayList<CustomerBean> resultUsers = new ArrayList<CustomerBean>();
				String[] checkbox = request.getParameterValues("unban_checkbox");
				String value = request.getParameter("value");
				String key = request.getParameter("key");
				String page = request.getParameter("page");
				if (checkbox != null) {
					for (int i = 0; i < checkbox.length; i++) {
						CustomerBean customer = new CustomerBean();
						customer.setUid(Integer.parseInt(checkbox[i]));
						customer.unban();
					}
					Search search = new Search("customer_search", value, key);
					resultUsers = search.customerSearch();	
					getServletContext().setAttribute("adminuserscount", resultUsers.size()/10);
					getServletContext().setAttribute("adminusers", resultUsers);
					response.sendRedirect("admin/customer_manage.jsp?value="+value+"&key="+key+"&page="+page);	
				} else {
					response.sendRedirect("admin/customer_manage.jsp?value="+value+"&key="+key+"&page="+page);
				}
			} else if (action.equals("ban")) {
				ArrayList<CustomerBean> resultUsers = new ArrayList<CustomerBean>();
				String[] checkbox = request.getParameterValues("ban_checkbox");
				String value = request.getParameter("value");
				String key = request.getParameter("key");
				String page = request.getParameter("page");
				if (checkbox != null) {
					for (int i = 0; i < checkbox.length; i++) {
						CustomerBean customer = new CustomerBean();
						customer.setUid(Integer.parseInt(checkbox[i]));
						customer.ban();
					}
					Search search = new Search("customer_search", value, key);
					resultUsers = search.customerSearch();	
					getServletContext().setAttribute("adminuserscount", resultUsers.size()/10);
					getServletContext().setAttribute("adminusers", resultUsers);
					response.sendRedirect("admin/customer_manage.jsp?value="+value+"&key="+key+"&page="+page);	
				} else {
					response.sendRedirect("admin/customer_manage.jsp?value="+value+"&key="+key+"&page="+page);
				}
			} else if (action.equals("log_out")) {
				request.getSession().removeAttribute("customerbean");
				response.sendRedirect("welcome.jsp");
			}
		}
	}
}