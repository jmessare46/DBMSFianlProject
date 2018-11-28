package dbms.finalproject;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnector
{

    private String url;
    private Properties props;
    private Connection conn;

    public DatabaseConnector()
    {

        this.url = "jdbc:postgresql://localhost:5432/terrorism";
        this.props = new Properties();
        this.props.setProperty("user","josephmessare");
        props.setProperty("password","Mess1998");

        try {
            this.conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e);
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public Connection getConnection() {
        return this.conn;
    }
}
