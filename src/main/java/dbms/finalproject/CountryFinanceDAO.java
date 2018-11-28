package dbms.finalproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryFinanceDAO implements CountryFinance {

    private DatabaseConnector db;

    public CountryFinanceDAO() {
        this.db = new DatabaseConnector();
    }

    public CountryObj getAttacker(String country, String subject, Integer year) throws SQLException {
        String sql = "SELECT * FROM countryfinance WHERE country = ? AND subject = ? AND year = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setString(1, country);
        prepStmt.setString(2, subject);
        prepStmt.setInt(3, year);

        ResultSet rs = prepStmt.executeQuery();
        if(rs.next()) {
            return new CountryObj(rs.getString("country"), rs.getString("subject"), rs.getString("subjectdes"), rs.getString("unit"), rs.getString("description"), rs.getString("scale"), rs.getInt("year"), rs.getDouble("amount"));
        }
        return null;
    }

    public List<CountryObj> getAll() throws SQLException {
        String sql = "SELECT * FROM countryfinance";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        ResultSet rs = prepStmt.executeQuery();
        ArrayList<CountryObj> countries = new ArrayList<CountryObj>();
        while (rs.next())
        {
            CountryObj country = new CountryObj(rs.getString("country"), rs.getString("subject"), rs.getString("subjectdes"), rs.getString("unit"), rs.getString("description"), rs.getString("scale"), rs.getInt("year"), rs.getDouble("amount"));
            countries.add(country);
        }

        return countries;
    }

    public void save(CountryObj country) throws SQLException {
        String sql = "INSERT INTO countryfinance (country, subject, subjectdes, unit, description, scale, year, amount) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

        prepStmt.setString(1, country.getCountry());
        prepStmt.setString(2, country.getSubject());
        prepStmt.setString(3, country.getSubjectDesc());
        prepStmt.setString(4, country.getUnit());
        prepStmt.setString(5, country.getDescription());
        prepStmt.setString(6, country.getScale());
        prepStmt.setInt(7, country.getYear());
        prepStmt.setDouble(8, country.getAmount());

        prepStmt.executeUpdate();
    }

    public void update(CountryObj country, String[] params) throws SQLException {
        for( String param : params )
        {
            String sql = "UPDATE countryfinance SET ? = ? WHERE country = ? AND subject = ? AND year = ?";
            PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

            prepStmt.setString(1, param);
            prepStmt.setString(3, country.getCountry());
            prepStmt.setString(4, country.getSubject());
            prepStmt.setInt(5, country.getYear());



            if(param.compareTo("country") == 0) {
                prepStmt.setString(2, country.getCountry());
            } else if(param.compareTo("subject") == 0) {
                prepStmt.setString(2, country.getSubject());
            } else if(param.compareTo("subjectdes") == 0) {
                prepStmt.setString(2, country.getSubjectDesc());
            } else if(param.compareTo("unit") == 0) {
                prepStmt.setString(2, country.getUnit());
            } else if(param.compareTo("description") == 0) {
                prepStmt.setString(2, country.getDescription());
            } else if(param.compareTo("scale") == 0) {
                prepStmt.setString(2, country.getScale());
            } else if(param.compareTo("year") == 0) {
                prepStmt.setInt(2, country.getYear());
            } else if(param.compareTo("amount") == 0) {
                prepStmt.setDouble(2, country.getAmount());
            }

            prepStmt.executeUpdate();
        }
    }

    public void delete(CountryObj country) throws SQLException {
        String sql = "DELETE FROM countryfinance WHERE country = ? AND subject = ? AND year = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setString(1, country.getCountry());
        prepStmt.setString(2, country.getSubject());
        prepStmt.setInt(3, country.getYear());

        prepStmt.executeUpdate();
    }
}
