package dbms.finalproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryGDPDAO implements CountryDAO
{
    private DatabaseConnector db;

    public CountryGDPDAO() {
        this.db = new DatabaseConnector();
    }

    public CountryFinance getCountry(int uid) throws SQLException {
        String sql = "SELECT * FROM countrygdp WHERE uid = ?";
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
        String sql = "SELECT * FROM countrygdp";
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

    public void save(CountryFinance contry) throws SQLException {
        String sql = "INSERT INTO countrygdp (country, subjectdes, unit, scale, notes, year, value) VALUES (?,?,?,?,?,?,?)";
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
            String sql = "UPDATE countrygdp SET ? = ? WHERE uid = ?";
            PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

            prepStmt.setString(1, param);
            prepStmt.setDouble(3, country.getUid());

            CountryRevenueDAO.checkParam(country, param, prepStmt);

            prepStmt.executeUpdate();
        }
    }

    public void delete(CountryFinance country) throws SQLException {
        String sql = "DELETE FROM countrygdp WHERE uid = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setDouble(1, country.getUid());
        prepStmt.executeUpdate();
    }

}
