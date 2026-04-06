package assignment4;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    int cid;
    String cname;
    double amount;

    Customer(int cid, String cname, double amount) {
        this.cid = cid;
        this.cname = cname;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CID: " + cid + " | Name: " + cname + " | Balance: Rs." + String.format("%.2f", amount);
    }
}
