package book;


import java.util.ArrayList;
import mySqlDb.DBActivities;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping("/addbook")
	public boolean addBook(@RequestParam(value="code", defaultValue="") String code,
			@RequestParam(value="title", defaultValue="") String title,
			@RequestParam(value="author", defaultValue="") String author,
			@RequestParam(value="page", defaultValue="") String page,
			@RequestParam(value="read", defaultValue="") String read,
			@RequestParam(value="userid", defaultValue="") String userid,
			@RequestParam(value="rating", defaultValue="") String rating
			
			) {

		boolean bResult=false;

		try{
			DBActivities dbaInsert = new DBActivities();
			 bResult = dbaInsert.AddToDB(Integer.valueOf(code),title,author,Integer.valueOf(page),Boolean.valueOf(read),userid,
					 Integer.valueOf(rating));	
		}
		catch(Exception e)
		{
			return false;
		}
		return bResult;
	}
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping("/viewbook")
	public Book viewBook(@RequestParam(value="barcode", defaultValue="") String code,
			@RequestParam(value="userid", defaultValue="") String userid) {


		try{
			DBActivities dbaInsert = new DBActivities();
			Book bkEntity = dbaInsert.ViewFromDB(code,userid);
			return bkEntity;
		}
		catch(Exception e)
		{
			return new Book("","","",0,false,0);
		}
	}
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping("/delbook")
	public boolean delBook(@RequestParam(value="barcode", defaultValue="") String code,
			@RequestParam(value="userid", defaultValue="") String userid) {


		try{
			DBActivities dbaInsert = new DBActivities();
			return dbaInsert.DelFromDB(code,userid);
		
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping("/mybook")
	public 	ArrayList<Book> myBook(@RequestParam(value="userid", defaultValue="") String userid) {

		try{
			DBActivities dbaInsert = new DBActivities();
			return dbaInsert.GetAllFromDB(userid);
		
		}
		catch(Exception e)
		{
			return null;
		}
	}
}