package BtThem.entities;

public class Receipt {

    private Client client;
    private double oldIndex;
    private double newIndex;
    private double money;

    public Receipt(Client client, double oldIndex, double newIndex) {
        this.client = client;
        this.oldIndex = oldIndex;
        this.newIndex = newIndex;
        this.money = (this.newIndex - this.oldIndex)*750;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

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

    @Override
    public String toString() {
        return "Receipt{" +
                "client=" + client +
                ", oldIndex=" + oldIndex +
                ", newIndex=" + newIndex +
                ", money=" + money+
                '}';
    }
}
