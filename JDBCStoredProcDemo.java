import java.sql.*;

public class JDBCStoredProcDemo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "javauser";     // or your MySQL username
        String password = "testpass"; // or your MySQL password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected");

            // Insert Employee
            CallableStatement cs1 = conn.prepareCall("{call insert_employee(?,?,?)}");
            cs1.setInt(1, 101);
            cs1.setString(2, "John");
            cs1.setDouble(3, 50000);
            cs1.execute();
            System.out.println("Employee Inserted Successfully");

            // Get Salary
            CallableStatement cs2 = conn.prepareCall("{call get_salary_by_id(?,?)}");
            cs2.setInt(1, 101);
            cs2.registerOutParameter(2, Types.DECIMAL);
            cs2.execute();

            double salary = cs2.getDouble(2);
            System.out.println("Salary = " + salary);

            cs1.close();
            cs2.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
