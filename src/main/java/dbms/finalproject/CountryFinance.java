package dbms.finalproject;

public class CountryFinance
{
    private String country;
    private String subjectDescription;
    private String unit;
    private String scale;
    private String notes;
    private Integer year;
    private Double value;
    private Integer uid;


    public CountryFinance(String country, String subjectDescription, String unit, String scale, String notes, Integer year, Double value, Integer uid) {
        this.country = country;
        this.subjectDescription = subjectDescription;
        this.unit = unit;
        this.scale = scale;
        this.notes = notes;
        this.year = year;
        this.value = value;
        this.uid = uid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
