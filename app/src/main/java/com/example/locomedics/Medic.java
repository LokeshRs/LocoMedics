package com.example.locomedics;


public class Medic  {

    private String Company;
    private String Med_discription;
    private String Med_genericname;
    private String Med_id;
    private String Med_name;
    private String Image;
    public Medic() {
    }

    public Medic(String company, String med_discription, String med_genericname, String med_id, String med_name, String image) {
        Company = company;
        Med_discription = med_discription;
        Med_genericname = med_genericname;
        Med_id = med_id;
        Med_name = med_name;
        Image =image;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getMed_discription() {
        return Med_discription;
    }

    public void setMed_discription(String med_discription) {
        Med_discription = med_discription;
    }

    public String getMed_genericname() {
        return Med_genericname;
    }

    public void setMed_genericname(String med_genericname) {
        Med_genericname = med_genericname;
    }

    public String getMed_id() {
        return Med_id;
    }

    public void setMed_id(String med_id) {
        Med_id = med_id;
    }

    public String getMed_name() {
        return Med_name;
    }

    public void setMed_name(String med_name) {
        Med_name = med_name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}