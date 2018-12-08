package dbms.finalproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryRevenueDAO implements CountryDAO
{
    private DatabaseConnector db;

    public CountryRevenueDAO() {
        this.db = new DatabaseConnector();
    }

    public CountryFinance getCountry(int uid) throws SQLException {
        String sql = "SELECT * FROM countryrevenue WHERE uid = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setInt(1, uid);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next()) {
            return new CountryFinance(rs.getString("country"),
                    rs.getString("subjectdes"), rs.getString("unit"),
                    rs.getString("scale"), rs.getString("notes"), rs.getInt("year"),
                    rs.getDouble("value"), rs.getInt("uid"));
        }
        return null;
    }

    public ArrayList<CountryFinance> getAll() throws SQLException {
        String sql = "SELECT * FROM countryrevenue";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        ResultSet rs = prepStmt.executeQuery();
        ArrayList<CountryFinance> countries = new ArrayList<CountryFinance>();
        while (rs.next())
        {
            CountryFinance countryFinance = new CountryFinance(rs.getString("country"),
                    rs.getString("subjectdes"), rs.getString("unit"),
                    rs.getString("scale"), rs.getString("notes"), rs.getInt("year"),
                    rs.getDouble("value"), rs.getInt("uid"));

            countries.add(countryFinance);
        }

        return countries;
    }

    public CountryFinance getYearData( String country, int year ) throws SQLException {
        String sql = "SELECT * FROM countryrevenue WHERE country = ? AND year = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setString(1, country);
        prepStmt.setInt(2, year);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next())
        {
            return new CountryFinance(rs.getString("country"),
                    rs.getString("subjectdes"), rs.getString("unit"),
                    rs.getString("scale"), rs.getString("notes"), rs.getInt("year"),
                    rs.getDouble("value"), rs.getInt("uid"));
        }

        return null;
    }

    public void save(CountryFinance contry) throws SQLException {
        String sql = "INSERT INTO countryrevenue (country, subjectdes, unit, scale, notes, year, value) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

        prepStmt.setString(1, contry.getCountry());
        prepStmt.setString(2, contry.getSubjectDescription());
        prepStmt.setString(3, contry.getUnit());
        prepStmt.setString(4, contry.getScale());
        prepStmt.setString(5, contry.getNotes());
        prepStmt.setInt(6, contry.getYear());
        prepStmt.setDouble(7, contry.getValue());

        prepStmt.executeUpdate();
    }

    public void update(CountryFinance country, String[] params) throws SQLException {
        for( String param : params )
        {
            String sql = "UPDATE countryrevenue SET ? = ? WHERE uid = ?";
            PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

            prepStmt.setString(1, param);
            prepStmt.setDouble(3, country.getUid());

            checkParam(country, param, prepStmt);

            prepStmt.executeUpdate();
        }
    }

    static void checkParam(CountryFinance country, String param, PreparedStatement prepStmt) throws SQLException {
        if(param.compareTo("country") == 0) {
            prepStmt.setString(2, country.getCountry());
        } else if(param.compareTo("subjectdes") == 0) {
            prepStmt.setString(2, country.getSubjectDescription());
        } else if(param.compareTo("unit") == 0) {
            prepStmt.setString(2, country.getUnit());
        } else if(param.compareTo("scale") == 0) {
            prepStmt.setString(2, country.getScale());
        } else if(param.compareTo("notes") == 0) {
            prepStmt.setString(2, country.getNotes());
        } else if(param.compareTo("year") == 0) {
            prepStmt.setInt(2, country.getYear());
        } else if(param.compareTo("value") == 0) {
            prepStmt.setDouble(2, country.getValue());
        }
    }

    public void delete(CountryFinance country) throws SQLException {
        String sql = "DELETE FROM countryrevenue WHERE uid = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setDouble(1, country.getUid());
        prepStmt.executeUpdate();
    }

}
