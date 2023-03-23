import java.sql.*;

public class SQLcommande {
    private static final String url="jdbc:mysql://localhost:3306/test";
    private static final String user = "admin";
    private static final String password = "admin";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String Select="SELECT * FROM user WHERE email=? AND password=?";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(url,user,password);
    }

    public static User UserLogin(String email,String password){
        Connection conn= null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        User user=null;
        try {
            conn = getConnection();
            preparedStatement=conn.prepareStatement(Select);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try { resultSet.close(); } catch (Exception e) { /* Ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* Ignored */ }
            try { conn.close(); } catch (Exception e) { /* Ignored */ }
        }

        return user;
    }


}
