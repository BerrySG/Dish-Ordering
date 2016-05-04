package hk.edu.uic.dishordering;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DishDetailBottomSheetFragment extends BottomSheetDialogFragment {

    public static final String KEY_DISH_NAME = "KEY_DISH_NAME";
    public static final String KEY_PRICE = "KEY_PRICE";

    private String mDishName;
    private String mPrice;

    @Bind(R.id.dish_name_label)
    TextView mDishNameLabel;

    @Bind(R.id.price_label)
    TextView mPriceLabel;

    public static DishDetailBottomSheetFragment newInstance(String name, String price) {
        DishDetailBottomSheetFragment dishDetailBottomSheetFragment = new DishDetailBottomSheetFragment();
        Bundle args = new Bundle();
        args.putString(KEY_DISH_NAME, name);
        args.putString(KEY_PRICE, price);
        dishDetailBottomSheetFragment.setArguments(args);
        return dishDetailBottomSheetFragment;
    }

    public DishDetailBottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDishName = getArguments().getString(KEY_DISH_NAME);
        mPrice = getArguments().getString(KEY_PRICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish_detail, container, false);

        ButterKnife.bind(this, view);

        mDishNameLabel.setText(mDishName);
        mPriceLabel.setText(mPrice);

        return view;
    }

}
