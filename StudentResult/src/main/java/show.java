import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/show")
public class show extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public show() {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // Set the response content type
        response.setContentType("text/html");

        // Create a PrintWriter to write the HTML response
        PrintWriter out = response.getWriter();
        

        // Retrieve the student ID from the request parameter
        int id = Integer.parseInt(request.getParameter("stdID"));
        
        try {
            // JDBC driver and database credentials
            Class.forName("com.mysql.cj.jdbc.Driver");
            String databaseUrl = "jdbc:mysql://localhost:3306/student";
            String username = "root";
            String password = "tiger";

            // Create a connection to the database
            Connection connection = DriverManager.getConnection(databaseUrl, username, password);
            System.out.println("Database connection established successfully!");

            // Create a statement
            PreparedStatement ps = connection.prepareStatement("select * from studata where stdID = ?");
            ps.setInt(1, id); 
            // Generate the HTML response
            out.println("<html><body align ='center'>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<h2>Student Result</h2>");
            out.print("<table width = 50% align = 'center' border=2>");
            
            //Resultset
            ResultSet rs=ps.executeQuery();
            
            //ResultSetMetaData
            ResultSetMetaData rsmd=rs.getMetaData();
            int totalColumn=rsmd.getColumnCount();
            
            for(int i=1;i<totalColumn;i++) {
            	out.print("<th>"+rsmd.getColumnName(i)+"</th>");
            }
            
            out.print("<tr>");
            while(rs.next()){
            	out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
            	
            }
            out.print("</table>");
        } catch (Exception e) {
            // Handle any exceptions that occur during database operations
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>Error:</h2>");
            out.println("<p>An error occurred while retrieving student records.</p>");
            out.println("</body></html>");
        }
    }
	private String getGrade(int marks) {
        if (marks >= 90 && marks <= 100) {
            return "A";
        }else if (marks >=80 && marks <= 90) {
        	return "B";
        }else if (marks >=70 && marks <= 80) {
        	return "C";
        }else if (marks >=60 && marks <= 70) {
        	return "D";
        }else if (marks >=50 && marks <= 60) {
        	return "E";
        }else {
        	return "FAIL";
        }
}}




//html file 



