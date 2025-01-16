

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class staffServlet
 */
@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Step 1: Prepare list of variables used for database connections private String jdbcURL = "jdbc:mysql://localhost:3306/userdetails";
	private String jdbcURL = "jdbc:mysql://SurfacePro-SL:3307/sat";
	private String jdbcUsername = "dba";
	private String jdbcPassword = "gqaeNryB6Xc_)Ty*";
	
	//Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	private static final String INSERT_STAFF_SQL = "INSERT INTO staff" + " (staff_id, name, address, mobile_no) VALUES " +
	" (?, ?, ?, ?);";
	private static final String SELECT_STAFF_BY_ID = "select staff_id, name, address, mobile_no from staff where staff_id =?";
	private static final String SELECT_ALL_STAFF = "select * from staff ";
	private static final String DELETE_STAFF_SQL = "delete from staff where staff_id = ?;";
	private static final String UPDATE_STAFF_SQL = "update staff set name = ?,address= ?, mobile_no =? where staff_id = ?;";

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println("Action doget:"+action);
		try {
		switch (action) {
		case "/StaffServlet/delete":
			deleteStaff(request, response);
			break;
		case "/StaffServlet/edit":
			showEditForm(request, response);
			break;
		case "/StaffServlet/update":
			updateStaff(request, response);
			break;
		case "/StaffServlet/dashboard":
			listStaff(request, response);
			break;
		case "/StaffServlet/insert":
			addNewStaff(request, response);
			break;
		}
		} catch (SQLException ex) {
		throw new ServletException(ex);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getServletPath();
		System.out.println("Action in DoPost:"+action);
		try {
		switch (action) {
		case "/StaffServlet/update":
				updateStaff(request, response);
				System.out.println("Action in DoPost2:"+action);
				//response.sendRedirect("http://localhost:8090/SAT/StaffServlet/dashboard");
				break;
		case "/StaffServlet/insert":
			
			//Step 2: retrieve the four parameters from the request from the web form
			String staffNo = request.getParameter("staffNo");
			String staffname = request.getParameter("staffname");
			String address = request.getParameter("address");
			String mobileNo = request.getParameter("mobileNo");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch (Exception exception) {
				System.out.println(exception);
				//System.out.close();
			}
			//Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/sat", "dba", "gqaeNryB6Xc_)Ty*");
			Connection con = DriverManager.getConnection( "jdbc:mysql://SurfacePro-SL:3307/sat", "dba", "gqaeNryB6Xc_)Ty*");
			PreparedStatement ps = con.prepareStatement("insert into staff(staff_id, name, address, mobile_no) values(?,?,?,?)");
			ps.setString(1, staffNo);
			ps.setString(2, staffname);
			ps.setString(3, address);
			ps.setString(4, mobileNo);
			int i = ps.executeUpdate();
			if (i > 0) {
				response.sendRedirect("http://localhost:8090/SAT/StaffServlet/dashboard");
				//	PrintWriter writer = response.getWriter();
				//	writer.println("<h1>" + "Staff record of "+staffname+" is successfully inserted / updated." + "</h1>");
				//	writer.close();
			}
			break;
		}
		}
		catch (SQLException ex) {
			throw new ServletException(ex);
			}
		
	}

	//Step 3: Implement the getConnection method which facilitates connection to the database via JDBC
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private void listStaff(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException
	{
		List <Staff> staff = new ArrayList <>();
		try (Connection connection = getConnection(); 
			// Step 5.1: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF);) 
			{ 
				// Step 5.2: Execute the query or update query
			
				ResultSet rs = preparedStatement.executeQuery(); 
				// Step 5.3: Process the ResultSet object.
				while (rs.next()) {
					String staffNo = rs.getString("staff_id");
					String staffname = rs.getString("name");
					String address = rs.getString("address");
					String mobileNo = rs.getString("mobile_no");
					System.out.println(staffname);
					staff.add(new Staff(staffNo, staffname, address, mobileNo));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	
		// Step 5.4: Set the staff list into the listStaff attribute to be pass to the staffManagement.jsp
		request.setAttribute("listStaff", staff);
		request.getRequestDispatcher("/staffManagement.jsp").forward(request, response);
	} 
	
	//method to delete user
	private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException { //Step 1: Retrieve value from the request
		String staffNo = request.getParameter("staffNo"); //Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STAFF_SQL);) {
			statement.setString(1, staffNo);
			int i = statement.executeUpdate();
		} 
		//Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
		response.sendRedirect("http://localhost:8090/SAT/StaffServlet/dashboard");
	}
	
	//method to trigger RegisterServlet
	private void addNewStaff(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException {
		//Step 2: retrieve the four parameters from the request from the web form
		String staffNo = request.getParameter("staffNo");
		String staffname = request.getParameter("staffname");
		String address = request.getParameter("address");
		String mobileNo = request.getParameter("mobileNo");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/sat", "dba", "gqaeNryB6Xc_)Ty*");
			PreparedStatement ps = con.prepareStatement("insert into staff(staff_id, name, address, mobile_no) values(?,?,?,?)");
			ps.setString(1, staffNo);
			ps.setString(2, staffname);
			ps.setString(3, address);
			ps.setString(4, mobileNo);
			int i = ps.executeUpdate();
			if (i > 0) {
				response.sendRedirect("http://localhost:8090/SAT/StaffServlet/dashboard");
				//	PrintWriter writer = response.getWriter();
				//	writer.println("<h1>" + "Staff record of "+staffname+" is successfully inserted / updated." + "</h1>");
				//	writer.close();
				}
			}
		catch (Exception exception) {
				System.out.println(exception);
				//System.out.close();
			}
		// Step 5.4: Set the staff list into the listStaff attribute to be pass to the staffManagement.jsp
		//response.sendRedirect("http://localhost:8090/SAT/StaffServlet/dashboard");
		//request.getRequestDispatcher("/staffManagement.jsp").forward(request, response);
		
	}
	//method to get parameter, query database for existing user data and redirect to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, ServletException, IOException {
		//get parameter passed in the URL
		String staffNo = request.getParameter("staffNo");
		Staff existingStaff = new Staff("", "", "", ""); 
		// Step 1: Establishing a Connection
	try (Connection connection = getConnection(); 
		// Step 2:Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
		preparedStatement.setString(1, staffNo); 
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery(); 
		// Step 4: Process the ResultSet object
		while (rs.next()) {
			staffNo = rs.getString("staff_id");
			String staffName = rs.getString("name");
			String address = rs.getString("address");
			String mobileNo = rs.getString("mobile_no");
			existingStaff = new Staff(staffNo, staffName, address, mobileNo);
			}
		} 
	catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	//Step 5: Set existingUser to request and serve up the userEdit form
	request.setAttribute("staff", existingStaff);
	request.getRequestDispatcher("/staffEdit.jsp").forward(request, response);
	}
	
	//Update Staff Record
	private void updateStaff(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		//Step 1: Retrieve value from the request
		String oriStaffNo = request.getParameter("oriStaffNo");
		String staffNo = request.getParameter("staffNo");
		String staffname = request.getParameter("staffname");
		String mobileNo = request.getParameter("mobileNo");
		String address = request.getParameter("address");
		//Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STAFF_SQL);) {
			//statement.setString(1, staffNo);
			statement.setString(1, staffname);
			statement.setString(2, address);
			statement.setString(3, mobileNo);
			statement.setString(4, oriStaffNo);
			int i = statement.executeUpdate();
		} 
		//Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
		response.sendRedirect("http://localhost:8090/SAT/StaffServlet/dashboard");
	}

	
	
}
