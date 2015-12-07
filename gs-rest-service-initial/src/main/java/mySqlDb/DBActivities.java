package mySqlDb;

import java.awt.List;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.sf.ehcache.search.expression.IsNull;
import book.Book;

public class DBActivities {

	SQLConnection sqlConn;
	Connection conn;
	public DBActivities() {
		// TODO Auto-generated constructor stub
		sqlConn=new SQLConnection();
		conn = sqlConn.getConnection();
	}

	public boolean AddToDB(int code,String title,String author,int page, boolean hasread,String userid,int rating) {
		try {

			CallableStatement callableStatement = null;
			String strProcName = "{call PROC_InsertBookDetails(?,?,?,?,?,?,?)}";
			callableStatement = conn.prepareCall(strProcName);
			callableStatement.setInt(1, code);
			callableStatement.setString(2, title);
			callableStatement.setString(3, author);
			callableStatement.setInt(4, page);
			callableStatement.setBoolean(5, hasread);
			callableStatement.setString(6, userid);
			callableStatement.setInt(7, rating);
			callableStatement.execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Book ViewFromDB(String code,String userid) {
		Book bkEntity = null;
		
		try {
			Statement st = conn.createStatement();
			String sql="select TB.*,(select Rating from TblUserBookDetail where Barcode  = '"+code+"' )as Rating,IFNULL(TU.HasRead,0) as ReadVal from TblBookDetails TB left join TblUserBookDetail TU on TB.Barcode = TU.Barcode  and TU.UserDetail = '"+userid+"' where TB.Barcode = '"+code+"'  and TB.IsActive = 1;";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				bkEntity =new Book( rs.getString("Barcode"),
						rs.getString("Title"),
						rs.getString("Author"),
						Integer.valueOf(rs.getString("No_Of_Page")),
						Boolean.valueOf(rs.getString("ReadVal").equals("1") ? true :false),
						Integer.valueOf(rs.getString("Rating")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bkEntity;
	}
	
	public ArrayList<Book> GetAllFromDB(String userid) {
		Book bkEntity = null;
		ArrayList<Book> bkList = new ArrayList<Book>();
		
		try {
			Statement st = conn.createStatement();
			String sql="select * from TblBookDetails TB join TblUserBookDetail TU on TB.Barcode = TU.Barcode  where TU.UserDetail = '"+userid+"' and TB.IsActive = '1';";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				bkList.add(new Book(rs.getString("Barcode"),
						rs.getString("Title"),
						rs.getString("Author"),
						Integer.valueOf(rs.getString("No_Of_Page")),
						Boolean.valueOf(rs.getString("HasRead").equals("1") ? true :false),
						Integer.valueOf(rs.getString("Rating"))));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bkList;
	}

	public boolean DelFromDB(String code,String userid) {
		int iRes = 0;
		try {
			Statement st = conn.createStatement();
			String sql="update TblBookDetails TB set TB.IsActive=0 where TB.Barcode = '"+code+"' and TB.IsActive = 1;";
			System.out.println(sql);
			iRes = st.executeUpdate(sql);
		    conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return iRes>0? true:false;
	}
}
