package model;

public class Clinic {

    public String name;
    public String address;
    public double longitude;
    public double latitude;
    public String contactNumber;

    public Clinic() {
    }

    public Clinic(
            String name,
            String address,
            double longitude,
            double latitude,
            String contactNumber
    ) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.contactNumber = contactNumber;
    }
}
