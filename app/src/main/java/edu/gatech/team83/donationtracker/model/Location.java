package edu.gatech.team83.donationtracker.model;

public class Location {

    private long id;
    private String name;
    private String type;
    private String longitude;
    private String latitude;
    private String address;
    private String phonenumber;

    public Location(int id, String type, String name, String longitude, String latitude, String address, String phonenumber) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public Location(){
        this(0, "","", "","", "", "");
    }


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }
}
