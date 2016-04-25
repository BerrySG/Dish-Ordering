package hk.edu.uic.dishordering.Model.PaymentSubsystem;

public class Receipt {

    private String mContent;

    public Receipt(Bill bill) {
        mContent = "Receipt: " + "Table ID: " + bill.getTableID() + "Total: " + bill.getTotalPrice();
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
