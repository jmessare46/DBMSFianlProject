package dbms.finalproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TargetDAO implements Target {

    private DatabaseConnector db;

    public TargetDAO() {
        this.db = new DatabaseConnector();
    }

    public TargetObj getEvent(double id) throws SQLException {
        String sql = "SELECT * FROM target WHERE target_id = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setDouble(1, id);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next()) {
            return new TargetObj(rs.getDouble("target_id"), rs.getString("name"), rs.getString("affiliation"), rs.getString("organization"), rs.getString("nationality"), rs.getString("location"));
        }
        return null;
    }

    public List<TargetObj> getAll() throws SQLException {
        String sql = "SELECT * FROM target";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        ResultSet rs = prepStmt.executeQuery();
        ArrayList<TargetObj> targets = new ArrayList<TargetObj>();
        while (rs.next())
        {
            TargetObj target = new TargetObj(rs.getDouble("target_id"), rs.getString("name"), rs.getString("affiliation"), rs.getString("organization"), rs.getString("nationality"), rs.getString("location"));
            targets.add(target);
        }

        return targets;
    }

    public void save(TargetObj target) throws SQLException {
        String sql = "INSERT INTO target (name, affiliation, organization, nationality, location, target_id) VALUES (?,?,?,?,?,?)";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

        prepStmt.setString(1, target.getName());
        prepStmt.setString(2, target.getAffiliation());
        prepStmt.setString(3, target.getOrganization());
        prepStmt.setString(4, target.getNationality());
        prepStmt.setString(5, target.getLocation());
        prepStmt.setDouble(6, target.getTargetID());

        prepStmt.executeUpdate();
    }

    public void update(TargetObj target, String[] params) throws SQLException {
        for( String param : params )
        {
            String sql = "UPDATE target SET ? = ? WHERE target_id = ?";
            PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

            prepStmt.setString(1, param);
            prepStmt.setDouble(3, target.getTargetID());

            if(param.compareTo("name") == 0) {
                prepStmt.setString(2, target.getName());
            } else if(param.compareTo("affiliation") == 0) {
                prepStmt.setString(2, target.getAffiliation());
            } else if(param.compareTo("organization") == 0) {
                prepStmt.setString(2, target.getOrganization());
            } else if(param.compareTo("nationality") == 0) {
                prepStmt.setString(2, target.getNationality());
            } else if(param.compareTo("location") == 0) {
                prepStmt.setString(2, target.getLocation());
            }

            prepStmt.executeUpdate();
        }
    }

    public void delete(TargetObj target) throws SQLException {
        String sql = "DELETE FROM target WHERE target_id = ?";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setDouble(1, target.getTargetID());
        prepStmt.executeUpdate();
    }
}
