package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StudentBook {
	private Integer studentId;
	private Integer bookId;
	private String bookName;
	private String issueDate;
	private String returnDate;
	private String writer;
	
	
	public StudentBook(Integer studentId, Integer bookId, String bookName, String issueDate, String returnDate,
			String writer) {
		super();
		this.studentId = studentId;
		this.bookId = bookId;
		this.bookName = bookName;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.writer = writer;
	}
	
	public StudentBook() {
		super();
	}

	public StudentBook(Integer bookId, String bookName, String issueDate, String returnDate, String writer) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.writer = writer;
	}

	public StudentBook(Integer bookId, String bookName, String issueDate, String returnDate) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}
	
	

public StudentBook(Integer bookId, String issueDate, String returnDate) {
		super();
		this.bookId = bookId;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

//	all my utility methods
	
	

	public ArrayList<StudentBook> collectBooksForStudent(Integer studentId){
		ArrayList<StudentBook> studentBooks = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select s.book_id,book_name,issue_date,return_date from "
					+ "student_books as s inner join books as b where b.book_id=s.book_id "
					+ "and student_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,studentId);
			
			ResultSet rs = ps.executeQuery();
			studentBooks = new ArrayList<StudentBook>();
			while(rs.next()){
				studentBooks.add(new StudentBook(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return studentBooks;
	}
	
	public ArrayList<StudentBook> returnBooksForStudent(Integer studentId,Integer bookId){
		ArrayList<StudentBook> books=null; 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    System.out.println("return books 1"+books);
	    System.out.println(dtf.format(now)+" "+studentId+" "+bookId);
	    String returnDate = dtf.format(now).toString();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?autoReconnect=true&useSSL=false&user=root&password=1234");
			String query = "delete from student_books where student_id=? and book_id=?";
			System.out.println("query executed 2 inside try"+books);
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,studentId);
			ps.setInt(2,bookId);
			
			ps.executeUpdate();
			
			con.close();
			System.out.println("query executed after calling collect books"+books);
			books = collectBooksForStudent(studentId);
			System.out.println("query executed last statement"+books);
			
		} catch (Exception e) {
			// TODO: handle exception
			books = null;
		}
		return books;
	}
	
	
	public ArrayList<StudentBook> addBooksForStudent(Integer studentId,Integer bookId){
		ArrayList<StudentBook> books = new ArrayList<StudentBook>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    
	    System.out.println(dtf.format(now));
	    String issueDate = dtf.format(now).toString();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "insert into student_books(student_id,book_id,issue_date,return_date)values(?,?,?,?)";
			
			PreparedStatement ps = con.prepareCall(query);
			ps.setInt(1, studentId);
			ps.setInt(2, bookId);		
			ps.setString(3,issueDate);
			ps.setString(4,"NA");
			
			ps.executeUpdate();
			books = collectBooksForStudent(studentId);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			books = null;
		}
		return books;
	}
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
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
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
