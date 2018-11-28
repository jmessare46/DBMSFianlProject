package dbms.finalproject;

public class TargetObj {
    private Double targetID;
    private String name;
    private String affiliation;
    private String organization;
    private String nationality;
    private String location;

    public TargetObj(Double targetID, String name, String affiliation, String organization, String nationality, String location) {
        this.targetID = targetID;
        this.name = name;
        this.affiliation = affiliation;
        this.organization = organization;
        this.nationality = nationality;
        this.location = location;
    }

    public Double getTargetID() {
        return targetID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
