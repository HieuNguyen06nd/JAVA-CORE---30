package BtThem.entities;

public class Client {
    private static  int autoId;

    private String id;
    private String name;
    private String homeNumber;
    private String meterCode;

    public Client( String name, String homeNumber, String meterCode) {
        this.id = "KH" + ++autoId;
        this.name = name;
        this.homeNumber = homeNumber;
        this.meterCode = meterCode;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID"+ id+
                ", homeNumber='" + homeNumber + '\'' +
                ", name='" + name + '\'' +
                ", meterCode='" + meterCode + '\'' +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }
}
