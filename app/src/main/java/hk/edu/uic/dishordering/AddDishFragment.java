package hk.edu.uic.dishordering;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;
import hk.edu.uic.dishordering.Model.DishSubsystem.Dish;

public class AddDishFragment extends Fragment {

    private Firebase mFirebase;

    @Bind(R.id.dish_name_input_layout)
    TextInputLayout mDishNameInputLayout;

    @Bind(R.id.price_input_layout)
    TextInputLayout mPriceInputLayout;

    @Bind(R.id.discount_input_layout)
    TextInputLayout mDiscountInputLayout;

    public AddDishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mFirebase = new Firebase("https://dish-ordering.firebaseio.com/menu");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_dish, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_dish_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveDishToFirebase();
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveDishToFirebase() {
        String dishName;
        int price;

        if (mDishNameInputLayout.getEditText() != null && mPriceInputLayout.getEditText() != null) {
            dishName = mDishNameInputLayout.getEditText().getText().toString();
            price = Integer.parseInt(mPriceInputLayout.getEditText().getText().toString());

            Dish dish = new Dish(dishName, null, price);
            mFirebase.push().setValue(dish);
        }
    }
}
