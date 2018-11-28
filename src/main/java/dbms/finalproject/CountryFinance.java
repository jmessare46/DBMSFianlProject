package dbms.finalproject;

import java.sql.SQLException;
import java.util.List;

public interface CountryFinance {
    CountryObj getAttacker(String country, String subject, Integer year) throws SQLException;

    List<CountryObj> getAll() throws SQLException;

    void save(CountryObj attacker) throws SQLException;

    void update(CountryObj attacker, String[] params) throws SQLException;

    void delete(CountryObj attacker) throws SQLException;

}
