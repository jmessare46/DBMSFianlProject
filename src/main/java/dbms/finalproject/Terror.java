package dbms.finalproject;

import java.sql.SQLException;
import java.util.List;

public interface Terror
{
    TerrorObj getEvent(double id) throws SQLException;

    List<TerrorObj> getAll() throws SQLException;

    void save(TerrorObj event) throws SQLException;

    void update(TerrorObj event, String[] params) throws SQLException;

    void delete(TerrorObj event) throws SQLException;
}
