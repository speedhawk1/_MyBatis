package jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * Created by Administrator on 2016/11/27.
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        new Driver();
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test?user=root&password=system");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO test.user VALUES(NULL,?,?)");
        preparedStatement.setString(1, "张三");
        preparedStatement.setString(2, "123");
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
