package BtThem.entities;

public class Client {
    private String name;
    private String homeNumber;
    private String meterCode;

    public Client(String name, String meterCode, String homeNumber) {
        this.name = name;
        this.meterCode = meterCode;
        this.homeNumber = homeNumber;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" +
                "homeNumber='" + homeNumber + '\'' +
                ", name='" + name + '\'' +
                ", meterCode='" + meterCode + '\'' +
                '}';
    }
}
