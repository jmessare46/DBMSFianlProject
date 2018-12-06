package dbms.finalproject;

import java.sql.SQLException;
import java.util.List;

public interface CountryPopulation
{
    public CountryFinance getCountry(int uid) throws SQLException;

    public List<CountryFinance> getAll() throws SQLException;

    public void save(CountryFinance country) throws SQLException;

    public void update(CountryFinance country, String[] params) throws SQLException;

    public void delete(CountryFinance country) throws SQLException;
}
