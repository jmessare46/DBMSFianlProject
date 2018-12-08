package dbms.finalproject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CountryDAO
{
    public CountryFinance getCountry(int uid) throws SQLException;

    public ArrayList<CountryFinance> getAll() throws SQLException;

    public void save(CountryFinance country) throws SQLException;

    public void update(CountryFinance country, String[] params) throws SQLException;

    public void delete(CountryFinance country) throws SQLException;
}
