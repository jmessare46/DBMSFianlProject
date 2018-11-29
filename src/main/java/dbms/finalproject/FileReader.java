package dbms.finalproject;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.Reader;
import java.sql.*;

public class FileReader {

    private String terrorCSV;
    private String econCSV;
    private DatabaseConnector db;

    public FileReader() {
        this.terrorCSV = "src/main/resources/Global_Terrorism_Database.csv";
        this.econCSV = "src/main/resources/WEOApr2018all.csv";
        db = new DatabaseConnector();
    }


    public void loadCountryFinance() {
        try {
            Reader in = new java.io.FileReader(econCSV);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            String sql = "INSERT INTO countryfinance (country, subject, subjectdes, unit, description, scale, year, amount) VALUES (?,?,?,?,?,?,?,?)";

            for (CSVRecord record : records) {
                for( int i = 9; i < 52; i++ )
                {
                    try {
                        PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);
                        prepStmt.setString(1, record.get("Country"));
                        prepStmt.setString(2, record.get("Subject Descriptor"));
                        prepStmt.setString(3, record.get("Subject Notes"));
                        prepStmt.setString(4, record.get("Units"));
                        prepStmt.setString(5, record.get("Country/Series-specific Notes"));
                        prepStmt.setString(6, record.get("Scale"));
                        prepStmt.setInt(7, 1971+i);
                        prepStmt.setDouble(8, Double.parseDouble(record.get(i)));
                        prepStmt.execute();
                    } catch (NumberFormatException e) {
                        // This means there is no value for that year given
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void loadTerrorEvent() {

        try {
            Reader in = new java.io.FileReader(terrorCSV);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                String sql = "INSERT INTO terevent (event_id, country, city, method, year, month, day) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

                prepStmt.setDouble(1, Double.parseDouble(record.get("eventid")));
                if( record.get("country_txt").length() < 255 && record.get("targtype1_txt").length() < 255 && record.get("attacktype1_txt").length() < 255 && record.get("city").length() < 255 )
                {
                    prepStmt.setString(2, record.get("country_txt"));
                    prepStmt.setString(3, record.get("city"));
                    prepStmt.setString(4, record.get("attacktype1_txt"));
                    prepStmt.setInt(5, Integer.parseInt(record.get("iyear")));
                    prepStmt.setInt(6, Integer.parseInt(record.get("imonth")));
                    prepStmt.setInt(7, Integer.parseInt(record.get("iday")));

                    prepStmt.execute();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTargets() {
        try {
            Reader in = new java.io.FileReader(terrorCSV);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                String sql = "INSERT INTO target (target_id, name, affiliation, organization, nationality, location) VALUES (?,?,?,?,?,?)";
                PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

                prepStmt.setDouble(1, Double.parseDouble(record.get("eventid")));
                prepStmt.setString(2, record.get("target1"));
                prepStmt.setString(3, record.get("corp1"));
                prepStmt.setString(4, record.get("targtype1_txt"));
                prepStmt.setString(5, record.get("natlty1_txt"));
                prepStmt.setString(6, record.get("targsubtype1_txt"));
                prepStmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadAttackers()
    {
        try {
            Reader in = new java.io.FileReader(terrorCSV);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String sql = "INSERT INTO attacker (id, name, motive, weapontype, weapon, numwounded, damagecost) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement prepStmt = db.getConnection().prepareStatement(sql);

                prepStmt.setDouble(1, Double.parseDouble(record.get("eventid")));
                prepStmt.setString(2, record.get("gname"));
                prepStmt.setString(3, record.get("motive"));
                prepStmt.setString(4, record.get("weaptype1_txt"));
                prepStmt.setString(5, record.get("weapsubtype1_txt"));
                if(( record.get("nwound").length() > 1 && record.get("nwound").matches("^[0-9]*$"))) {
                    prepStmt.setInt(6, Integer.parseInt(record.get("nwound")));
                } else {
                    prepStmt.setNull(6, Types.INTEGER);
                }
                if( record.get("propvalue").length() > 1 && record.get("propvalue").matches("^[0-9]*$")) {
                    prepStmt.setInt(7, Integer.parseInt(record.get("propvalue")));
                } else {
                    prepStmt.setNull(7, Types.INTEGER);
                }
                prepStmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
