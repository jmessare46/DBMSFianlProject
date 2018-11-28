package dbms.finalproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttackerDAO implements Attacker {
    private DatabaseConnector db;

    public AttackerDAO() {
        this.db = new DatabaseConnector();
    }

    public AttackerObj getAttacker(double id) throws SQLException {
        String sql = "SELECT * FROM attacker WHERE id = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setDouble(1, id);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next()) {
            return new AttackerObj(rs.getString("name"), rs.getDouble("id"), rs.getString("weapontype"), rs.getString("weapon"), rs.getInt("numwounded"), rs.getInt("damagecost"), rs.getString("motive"));
        }
        return null;
    }

    public List<AttackerObj> getAll() throws SQLException {
        String sql = "SELECT * FROM attacker";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        ResultSet rs = prepStmt.executeQuery();
        ArrayList<AttackerObj> attackers = new ArrayList<AttackerObj>();
        while (rs.next())
        {
            AttackerObj attacker = new AttackerObj(rs.getString("name"), rs.getDouble("id"), rs.getString("weapontype"), rs.getString("weapon"), rs.getInt("numwounded"), rs.getInt("damagecost"), rs.getString("motive"));
            attackers.add(attacker);
        }

        return attackers;
    }

    public void save(AttackerObj attacker) throws SQLException {
        String sql = "INSERT INTO attacker (name, id, weapontype, weapon, numwounded, damagecost, motive) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

        prepStmt.setString(1, attacker.getName());
        prepStmt.setDouble(2, attacker.getId());
        prepStmt.setString(3, attacker.getWeaponType());
        prepStmt.setString(4, attacker.getWeapon());
        prepStmt.setInt(5, attacker.getnWounded());
        prepStmt.setInt(6, attacker.getDamageCost());
        prepStmt.setString(7, attacker.getMotive());

        prepStmt.executeUpdate();
    }

    public void update(AttackerObj attacker, String[] params) throws SQLException {
        for( String param : params )
        {
            String sql = "UPDATE attacker SET ? = ? WHERE id = ?";
            PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

            prepStmt.setString(1, param);
            prepStmt.setDouble(3, attacker.getId());

            if(param.compareTo("name") == 0) {
                prepStmt.setString(2, attacker.getName());
            } else if(param.compareTo("weapontype") == 0) {
                prepStmt.setString(2, attacker.getWeaponType());
            } else if(param.compareTo("weapon") == 0) {
                prepStmt.setString(2, attacker.getWeapon());
            } else if(param.compareTo("numwounded") == 0) {
                prepStmt.setInt(2, attacker.getnWounded());
            } else if(param.compareTo("damagecost") == 0) {
                prepStmt.setInt(2, attacker.getDamageCost());
            } else if(param.compareTo("motive") == 0) {
                prepStmt.setString(2, attacker.getMotive());
            }

            prepStmt.executeUpdate();
        }
    }

    public void delete(AttackerObj attacker) throws SQLException {
        String sql = "DELETE FROM attacker WHERE id = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setDouble(1, attacker.getId());
        prepStmt.executeUpdate();
    }
}
