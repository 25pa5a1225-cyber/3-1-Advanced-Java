import java.sql.*;

public class Student123 {

    public static void main(String args[]) {

       String url = "jdbc:mysql://localhost:3306/studentdb";
String user = "root";
String password = "Student@123";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,user,password);

            Statement st = con.createStatement();

            String create =
                    "CREATE TABLE IF NOT EXISTS student(" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "department VARCHAR(50)," +
                    "marks INT)";

            st.executeUpdate(create);

            System.out.println("Table Ready");

            st.executeUpdate("INSERT INTO student VALUES(1,'Deepika','CSE',90)");

            ResultSet rs = st.executeQuery("SELECT * FROM student");

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("id")+" "
                        +rs.getString("name")+" "
                        +rs.getString("department")+" "
                        +rs.getInt("marks"));
            }

            con.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
