package dbms.finalproject;

import java.sql.SQLException;
import java.util.List;

public interface Attacker
{
    AttackerObj getAttacker(double id) throws SQLException;

    List<AttackerObj> getAll() throws SQLException;

    void save(AttackerObj attacker) throws SQLException;

    void update(AttackerObj attacker, String[] params) throws SQLException;

    void delete(AttackerObj attacker) throws SQLException;
}
