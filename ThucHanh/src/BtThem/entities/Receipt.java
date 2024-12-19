package BtThem.entities;

public class Receipt {

//    private Client client;
    private double oldIndex;
    private double newIndex;
    private double money;
    private String id_Client;

    public Receipt(double oldIndex, double newIndex, String id_Client) {
//        this.client = client;
        this.oldIndex = oldIndex;
        this.newIndex = newIndex;
        this.money = (newIndex -oldIndex)*750;
        this.id_Client = id_Client;
    }

//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }


    public double getOldIndex() {
        return oldIndex;
    }

    public void setOldIndex(double oldIndex) {
        this.oldIndex = oldIndex;
    }

    public double getNewIndex() {
        return newIndex;
    }

    public void setNewIndex(double newIndex) {
        this.newIndex = newIndex;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getId_Client() {
        return id_Client;
    }

    public void setId_Client(String id_Client) {
        this.id_Client = id_Client;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "client=" + id_Client +
                ", oldIndex=" + oldIndex +
                ", newIndex=" + newIndex +
                ", money=" + money+
                '}';
    }
}
