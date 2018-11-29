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

    public void countryGDPvTerror(int year, DatabaseConnector db, String country ) throws SQLException
    {
        String sql = "SELECT DISTINCT countryfinance.country, avg(countryfinance.amount) as gdp, terrorcount.num " +
                "FROM countryfinance, (SELECT count(*) as num, year, country FROM terevent GROUP BY terevent.year, terevent.country) as terrorcount " +
                "WHERE countryfinance.year = ? " +
                "AND countryfinance.subject = 'Gross domestic product, constant prices'" +
                "AND unit = 'Percent change' AND terrorcount.year = countryfinance.year " +
                "AND terrorcount.country = countryfinance.country " +
                "GROUP BY countryfinance.country, unit, terrorcount.num;";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setInt(1, year);

        ResultSet rs = prepStmt.executeQuery();
        System.out.println(String.format("%35s %24s %15s", "Country", "GDP Change", "Terror Attacks" ));
        System.out.println("----------------------------------------------------------------------------");

        while(rs.next()) {
            System.out.println(String.format("%35s %24s %15s", rs.getString(1), rs.getString(2), rs.getString(3)));
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
