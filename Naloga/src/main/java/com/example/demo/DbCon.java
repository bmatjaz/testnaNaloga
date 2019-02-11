package com.example.demo;
import java.sql.*;

public class DbCon {

    public Statement conn() throws Exception {
    	Connection conn = DriverManager.getConnection("jdbc:h2:mem:dbh2", "sa", "");
		java.sql.Statement statement = conn.createStatement();
        return statement;
    }
}
