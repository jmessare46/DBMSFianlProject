package dbms.finalproject;

import org.apache.commons.cli.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
//        loadData();

        DatabaseConnector db = new DatabaseConnector();
        Options options = new Options();
        Option gdp = new Option("y", "year", true,"Compare terror activity to % GDP increase. ");
        gdp.setArgs(2);
        options.addOption(gdp);
        Option help = new Option("h", "help", false, "Lists available commands");
        options.addOption(help);
        Option terrorCount = new Option("c", "count", true, "Shows the number of terrorist events in a country over time");
        options.addOption(terrorCount);

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser cli = new DefaultParser();

        try {
            CommandLine cmd = cli.parse( options, args );
            if( cmd.hasOption("h"))
            {
                formatter.printHelp("CLI Tester", options);
            }
            else if( cmd.hasOption("y")) {
                try {
                    CountryFinanceDAO cfd = new CountryFinanceDAO();
                    String[] searchArgs = cmd.getOptionValues("y");
                    cfd.countryGDPvTerror(Integer.parseInt(searchArgs[0]), db, searchArgs[1]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if( cmd.hasOption("c") )
            {
                try {
                    TerrorDAO terrorDAO = new TerrorDAO();
                    terrorDAO.countryAttack(cmd.getOptionValue("c"));
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("That is not a command please use the following:");
                formatter.printHelp("CLI Tester", options);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /*
     * Loads in the database from the csv files.
     */
    private static void loadData()
    {
        FileReader fr = new FileReader();
        System.out.println("Loading targets...");
        fr.loadTargets();
        System.out.println("Targets loaded.");
        System.out.println("Loading attackers...");
        fr.loadAttackers();
        System.out.println("Attackers loaded.");
        System.out.println("Loading terror events...");
        fr.loadTerrorEvent();
        System.out.println("Terror events loaded");
        System.out.println("Loading country finance data...");
        fr.loadCountryFinance();
        System.out.println("Finance data loaded.");
    }

}
