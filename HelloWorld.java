package test.alc;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@SuppressWarnings("serial")
public class HelloWorld extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

		response.setContentType("text/html; charset=utf8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>お酒の種類リスト</title>");
		out.println("</head>");
		out.println("<body>");

		Connection conn = null;
		String url = "jdbc:mysql://localhost/Alc?characterEncoding=utf8";
		String user = "****";
		String password = "****";

		try {
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		  conn = DriverManager.getConnection(url, user, password);

		  Statement stmt = conn.createStatement();
		  String sql = "select * from AlcType order by AlcTypeCD";
		  ResultSet rs = stmt.executeQuery(sql);

		  out.println("<table>");

		  while(rs.next()){
		    String alcTypeCd = rs.getString("AlcTypeCD");
		    String alcTypeName = rs.getString("AlcTypeName");
		    out.println("<tr>");
		    out.println("<td>" + alcTypeCd + "</td><td><a href='http://www.google.com/search?q=" + alcTypeName + "'>" + alcTypeName + "</a></td>");
		    out.println("</tr>");
		  }
		  out.println("</table>");

		  rs.close();
		  stmt.close();
		}catch (ClassNotFoundException e){
		  out.println("ClassNotFoundException:" + e.getMessage());
		}catch (SQLException e){
		  out.println("SQLException:" + e.getMessage());
		}catch (Exception e){
		  out.println("Exception:" + e.getMessage());
		}finally{
		  try{
		    if (conn != null){
		      conn.close();
		    }
		  }catch (SQLException e){
		    out.println("SQLException:" + e.getMessage());
		  }
		}

		out.println("</body>");
		out.println("</html>");
  }
}