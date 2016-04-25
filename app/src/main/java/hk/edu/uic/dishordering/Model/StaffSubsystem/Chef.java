package hk.edu.uic.dishordering.Model.StaffSubsystem;

import hk.edu.uic.dishordering.Model.DishSubsystem.Dish;
import hk.edu.uic.dishordering.Model.DishSubsystem.DishStatus;

public class Chef extends Staff {

    private Dish mCookingDish;
    private Department mDepartment;

    public Chef(Department department) {
        mDepartment = department;
    }

    public void startCooking() {
        // TODO: get dish from department

        if (mCookingDish.getDishStatus() == DishStatus.WAITING) {
            mCookingDish.setDishStatus(DishStatus.COOKING);
        }
    }

    public void endCooking() {
        if (mCookingDish.getDishStatus() == DishStatus.COOKING) {
            mCookingDish.setDishStatus(DishStatus.SERVING);
        }
    }
}
