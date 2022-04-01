package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Book {
	private Integer bookId;
	private String bookName;
	private Integer totalQuantity;
	private Integer issued;
	private String writer;
	private Integer classId;
	
	
	public Book(Integer bookId, String bookName, Integer totalQuantity, Integer issued, String writer,
			Integer classId) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.totalQuantity = totalQuantity;
		this.issued = issued;
		this.writer = writer;
		this.classId = classId;
	}


	public Book(String bookName, Integer totalQuantity, Integer issued, String writer, Integer classId) {
		super();
		this.bookName = bookName;
		this.totalQuantity = totalQuantity;
		this.issued = issued;
		this.writer = writer;
		this.classId = classId;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	 public models.Book fetchBookByBookId(Integer bookId){
		models.Book books = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select book_name,total_books,issued,writer,class_id from books where book_id=?";
			
			System.out.println(books);			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			books = new models.Book();
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				books.setBookName(rs.getString(1));
				books.setTotalQuantity(rs.getInt(2));
				books.setIssued(rs.getInt(3));
				books.setWriter(rs.getString(4));
				books.setClassId(rs.getInt(5));
			}
			System.out.println(books);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		return books;
	}
	
	public static ArrayList<Book> collectAllBooks(){
		ArrayList<Book> books = null;
		System.out.println("method collect all books");		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select book_id,book_name,total_books,issued,writer,class_id from books";
			
			System.out.println(books);			
			PreparedStatement ps = con.prepareStatement(query);
			books = new ArrayList<Book>();
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				books.add(new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6)));
			}
			System.out.println(books);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		return books;
	}
	
	public Boolean addBooks(){
		Boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "insert into books(book_name,total_books,issued,writer,class_id)values(?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,bookName);
			ps.setInt(2, issued);
			ps.setInt(3, totalQuantity);
			ps.setString(4,writer);
			ps.setInt(5,classId);
			
			ps.executeUpdate();
			flag = true;
			con.close();			
			
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
		}
		return flag;
	}
	
	public static void updateBookReturn(Integer bookId) {
		Integer issuedBook = 0;
		Integer totalBook = 0;
		
		String query = "select issued,total_books from books where book_id=?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			ResultSet rs= ps.executeQuery();
			System.out.println("inside update book----1");
			if(rs.next()){
				issuedBook = rs.getInt(1);
				totalBook = rs.getInt(2);
			}
			System.out.println("inside update book----2");
			query = "update books set total_books=?,issued=? where book_id=?";
			ps = con.prepareStatement(query);
			

			ps.setInt(1, ++totalBook);
			ps.setInt(2,--issuedBook);
			ps.setInt(3,bookId);
			
			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public static void updateBook(Integer bookId){
		Integer issuedBook = 0;
		Integer totalBook = 0;
		
		String query = "select issued,total_books from books where book_id=?";
//		String 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			ResultSet rs= ps.executeQuery();
			System.out.println("inside update book----1");
			if(rs.next()){
				issuedBook = rs.getInt(1);
				totalBook = rs.getInt(2);
			}
			System.out.println("inside update book----2");
			query = "update books set total_books=?,issued=? where book_id=?";
			ps = con.prepareStatement(query);
			
			System.out.println("inside update book----3");
			ps.setInt(1, --totalBook);
			ps.setInt(2,++issuedBook);
			ps.setInt(3,bookId);
			
			ps.executeUpdate();
			System.out.println("inside update book----4");
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Integer getBookId() {
		return bookId;
	}


	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public Integer getTotalQuantity() {
		return totalQuantity;
	}


	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}


	public Integer getIssued() {
		return issued;
	}


	public void setIssued(Integer issued) {
		this.issued = issued;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public Integer getClassId() {
		return classId;
	}


	public void setClassId(Integer classId) {
		this.classId = classId;
	}


	
	
	
}
