package dbms.finalproject;

import java.sql.Date;

public class TerrorObj
{
    private Double id;
    private Date date;
    private String country;
    private String city;
    private String method;
    private int year;
    private int month;
    private int day;

    public TerrorObj(Double id, String country, String city, String method, int year, int day, int month) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.country = country;
        this.city = city;
        this.method = method;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
