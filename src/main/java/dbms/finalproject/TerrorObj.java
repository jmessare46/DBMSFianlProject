package dbms.finalproject;

import java.sql.Date;

public class TerrorObj
{
    private Double id;
    private Date date;
    private String country;
    private String city;
    private String method;

    public TerrorObj(Double id, String country, String city, String method, Date date) {
        this.id = id;
        this.date = date;
        this.country = country;
        this.city = city;
        this.method = method;
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
