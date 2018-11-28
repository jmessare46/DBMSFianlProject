package dbms.finalproject;

public class CountryObj {
    private String country;
    private String subject;
    private String subjectDesc;
    private String unit;
    private String description;
    private String scale;
    private Integer year;
    private Double amount;

    public CountryObj(String country, String subject, String subjectDesc, String unit, String description, String scale, Integer year, Double amount) {
        this.country = country;
        this.subject = subject;
        this.subjectDesc = subjectDesc;
        this.unit = unit;
        this.description = description;
        this.scale = scale;
        this.year = year;
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
