package hk.edu.uic.dishordering.Model.DishSubsystem;

import java.util.ArrayList;

import hk.edu.uic.dishordering.Model.MemberSubsystem.Comment;
import hk.edu.uic.dishordering.Model.StaffSubsystem.Department;

public class Dish {

    private String mName;
    private int mPrice;
    private float mDiscount;
    private DishStatus mDishStatus;
    private Department mDepartment;
    private ArrayList<Comment> mComments;

    public Dish(String name, Department department, int price) {
        mName = name;
        mDepartment = department;
        mPrice = price;

        mDiscount = 0.0f;
        mComments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return getName() + " - " + getDepartment() + " - " + getPrice();
    }

    public void addComment(Comment comment) {
        mComments.add(comment);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public float getDiscount() {
        return mDiscount;
    }

    public void setDiscount(float discount) {
        mDiscount = discount;
    }

    public DishStatus getDishStatus() {
        return mDishStatus;
    }

    public void setDishStatus(DishStatus dishStatus) {
        mDishStatus = dishStatus;
    }

    public Department getDepartment() {
        return mDepartment;
    }

    public void setDepartment(Department department) {
        mDepartment = department;
    }

    public ArrayList<Comment> getComments() {
        return mComments;
    }

    public void setComments(ArrayList<Comment> comments) {
        mComments = comments;
    }
}
