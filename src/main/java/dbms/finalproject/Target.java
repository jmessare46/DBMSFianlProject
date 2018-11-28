package dbms.finalproject;

import java.sql.SQLException;
import java.util.List;

public interface Target {
    TargetObj getEvent(double id) throws SQLException;

    List<TargetObj> getAll() throws SQLException;

    void save(TargetObj event) throws SQLException;

    void update(TargetObj event, String[] params) throws SQLException;

    void delete(TargetObj event) throws SQLException;
}
