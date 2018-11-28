package dbms.finalproject;

import org.apache.commons.cli.*;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args)
    {
        loadData();
//
        DatabaseConnector db = new DatabaseConnector();
//        Options options = new Options();
//        options.addOption("y", "year", true,"Compare terror activity to financial data on that year.")
//                .addOption("h", "help", false, "Lists available commands");
//
//        HelpFormatter formatter = new HelpFormatter();
//        CommandLineParser cli = new DefaultParser();
//
//        try {
//            CommandLine cmd = cli.parse( options, args );
//
//            if(cmd.hasOption("h")) {
//                formatter.printHelp("CLI Tester", options);
//            }
//            else if( cmd.hasOption("y")) {
//                try {
//                    System.out.println(cmd.getOptionValue("y"));
//                    compareYears(Integer.parseInt(cmd.getOptionValue("y")), db);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        try {
//            compareYears(1, db);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    /*
     * Loads in the database from the csv files.
     */
    private static void loadData()
    {
        FileReader fr = new FileReader();
//        System.out.println("Loading targets...");
//        fr.loadTargets();
//        System.out.println("Targets loaded.");
//        System.out.println("Loading attackers...");
//        fr.loadAttackers();
//        System.out.println("Attackers loaded.");
        System.out.println("Loading terror events...");
        fr.loadTerrorEvent();
        System.out.println("dbms.finalproject.Terror events loaded");
//        System.out.println("Loading country finance data...");
//        fr.loadCountryFinance();
//        System.out.println("Finance data loaded.");
    }

    private static TerrorObj compareYears(int year, DatabaseConnector db ) throws SQLException {
        String sql = "SELECT * FROM terevent, countryfinance WHERE terevent = ? AND countryfinance = ? AND terevent.country = countryfinance.country";
        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
        prepStmt.setInt(1, year);
        prepStmt.setInt(2, year);
        ResultSet rs = prepStmt.executeQuery();
        if(rs.next())
        {
            return new TerrorObj(rs.getDouble("event_id"), rs.getString("country"), rs.getString("city"), rs.getString("method"), new Date(rs.getInt("year"), rs.getInt("month"), rs.getInt("day")));
        }
        return null;
    }

}
