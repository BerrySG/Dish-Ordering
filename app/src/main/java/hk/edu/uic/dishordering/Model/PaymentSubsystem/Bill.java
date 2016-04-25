package hk.edu.uic.dishordering.Model.PaymentSubsystem;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

import hk.edu.uic.dishordering.Model.DishSubsystem.Dish;
import hk.edu.uic.dishordering.Model.MemberSubsystem.Member;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill {

    private int mTableID;
    private ArrayList<Dish> mDishes;
    private int mTotalPrice;
    private BillStatus mBillStatus;
    private PaymentMethod mPaymentMethod;
    private Member mMember;
    private long mCurrentTimeMillis;

    public Bill() {

    }

    public Bill(int tableID) {
        mTableID = tableID;

        mDishes = new ArrayList<>();
        mTotalPrice = 0;
        mBillStatus = BillStatus.UNPAID;
        mPaymentMethod = null;
        mMember = null;
        mCurrentTimeMillis = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return getTableID() + " - " + getCurrentTimeMillis();
    }

    public Receipt payBill(PaymentMethod paymentMethod) {

        if (mMember != null) {
            mTotalPrice = Math.round(mTotalPrice * mMember.getDiscount());
            mMember.increaseExpendAmount(mTotalPrice);
        }

        mPaymentMethod = paymentMethod;
        if (mPaymentMethod == PaymentMethod.ONLINE) {
            // invoke online payment module
        }

        return new Receipt(this);
    }

    public void orderDishes(Dish[] dishes) {
        // TODO: send dish to department
    }

    public void cancelDishes(Dish[] dishes) {
        // TODO: send notification to department
    }

    public int getTableID() {
        return mTableID;
    }

    public void setTableID(int tableID) {
        mTableID = tableID;
    }

    public ArrayList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        mDishes = dishes;
    }

    public int getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        mTotalPrice = totalPrice;
    }

    public BillStatus getBillStatus() {
        return mBillStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        mBillStatus = billStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return mPaymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        mPaymentMethod = paymentMethod;
    }

    public Member getMember() {
        return mMember;
    }

    public void setMember(Member member) {
        mMember = member;
    }

    public long getCurrentTimeMillis() {
        return mCurrentTimeMillis;
    }

    public void setCurrentTimeMillis(long currentTimeMillis) {
        mCurrentTimeMillis = currentTimeMillis;
    }

}
