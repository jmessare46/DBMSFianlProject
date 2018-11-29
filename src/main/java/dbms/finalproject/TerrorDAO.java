package dbms.finalproject;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TerrorDAO implements Terror
{
    private DatabaseConnector db;

    public TerrorDAO() {
        this.db = new DatabaseConnector();
    }

    public TerrorObj getEvent(double id) throws SQLException
    {
        String sql = "SELECT * FROM terevent WHERE event_id = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setDouble(1, id);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next())
        {
            return new TerrorObj(rs.getDouble("event_id"), rs.getString("country"), rs.getString("city"), rs.getString("method"), new Date(rs.getInt("year"), rs.getInt("month"), rs.getInt("day")));
        }
        return null;
    }

    public List<TerrorObj> getAll() throws SQLException
    {
        String sql = "SELECT * FROM terevent";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        ResultSet rs = prepStmt.executeQuery();
        ArrayList<TerrorObj> events = new ArrayList<TerrorObj>();
        while (rs.next())
        {
            TerrorObj event = new TerrorObj(rs.getDouble("event_id"), rs.getString("country"), rs.getString("city"), rs.getString("method"), new Date(rs.getInt("year"), rs.getInt("month"), rs.getInt("day")));
            events.add(event);
        }

        return events;
    }

    public void save(TerrorObj event) throws SQLException
    {
        String sql = "INSERT INTO terevent (event_id, country, city, method, date) VALUES (?,?,?,?,?)";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

        prepStmt.setDouble(1, event.getId());
        prepStmt.setString(2, event.getCountry());
        prepStmt.setString(3, event.getCity());
        prepStmt.setString(4, event.getMethod());
        prepStmt.setDate(5, event.getDate());

        prepStmt.executeUpdate();
    }

    public void update(TerrorObj event, String[] params) throws SQLException
    {
        for( String param : params )
        {
            String sql = "UPDATE terevent SET ? = ? WHERE event_id = ?";
            PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

            prepStmt.setString(1, param);
            prepStmt.setDouble(3, event.getId());

            if(param.compareTo("country") == 0) {
                prepStmt.setString(2, event.getCountry());
            } else if(param.compareTo("city") == 0) {
                prepStmt.setString(2, event.getCity());
            } else if(param.compareTo("method") == 0) {
                prepStmt.setString(2, event.getMethod());
            } else if(param.compareTo("date") == 0) {
                prepStmt.setDate(2, event.getDate());
            }

            prepStmt.executeUpdate();
        }
    }

    public void countryAttack( String country ) throws SQLException
    {
        String sql = "SELECT * FROM terevent WHERE country = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setString(1, country);
        ResultSet rs = prepStmt.executeQuery();
        ArrayList<TerrorObj> terrorEvents = new ArrayList<TerrorObj>();
        System.out.println(String.format("%35s %10s %10s", "Country", "Count", "Year" ));
        System.out.println("---------------------------------------------------------");
        int year = 1970;
        int tempYear = year;
        int count = 0;
        while(rs.next())
        {
            tempYear = rs.getInt("year");
            if(year == tempYear)
            {
                count++;
            }
            else
            {
                System.out.println(String.format("%35s %10s %10s", country, count, year));
                count = 0;
                year = tempYear;
            }
        }
        System.out.println(String.format("%35s %10s %10s", country, count, year));
    }

    public void delete(TerrorObj event) throws SQLException
    {
        String sql = "DELETE FROM terevent WHERE event_id = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setDouble(1, event.getId());
        prepStmt.executeUpdate();
    }
}
