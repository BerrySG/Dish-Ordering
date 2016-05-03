package hk.edu.uic.dishordering.Model.DishSubsystem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

import hk.edu.uic.dishordering.Model.MemberSubsystem.Comment;
import hk.edu.uic.dishordering.Model.StaffSubsystem.Department;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dish {

    private String mName;
    private int mPrice;
    private boolean mIsFeatured;
    private float mDiscount;
    private DishStatus mDishStatus;
    private Department mDepartment;
    private ArrayList<Comment> mComments;

    public Dish() {

    }

    public Dish(String name, Department department, int price, boolean isFeatured) {
        mName = name;
        mDepartment = department;
        mPrice = price;
        mIsFeatured = isFeatured;

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

    public boolean isFeatured() {
        return mIsFeatured;
    }

    public void setFeatured(boolean isFeatured) {
        mIsFeatured = isFeatured;
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
