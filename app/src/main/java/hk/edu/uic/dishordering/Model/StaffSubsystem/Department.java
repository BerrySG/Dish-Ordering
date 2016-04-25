package hk.edu.uic.dishordering.Model.StaffSubsystem;

import java.util.ArrayList;

import hk.edu.uic.dishordering.Model.DishSubsystem.Dish;

public class Department {

    private ArrayList<Chef> mChefs;
    private ArrayList<Dish> mDishes;

    public Department() {
        mChefs = new ArrayList<>();
        mDishes = new ArrayList<>();
    }

    public void assignChef(Chef chef) {
        mChefs.add(chef);
    }

    public void dismissChef(Chef chef) {
        mChefs.remove(chef);
    }

    public ArrayList<Chef> getChefs() {
        return mChefs;
    }

    public void setChefs(ArrayList<Chef> chefs) {
        mChefs = chefs;
    }

    public ArrayList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        mDishes = dishes;
    }
}
