package hk.edu.uic.dishordering;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public FragmentActivity mActivity;

    @Bind(R.id.dish_name_label)
    TextView mDishNameLabel;

    @Bind(R.id.price_label)
    TextView mPriceLabel;

    @Bind(R.id.order_button)
    Button mOrderButton;

    public DishViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DishDetailBottomSheetFragment dishDetailBottomSheetFragment = DishDetailBottomSheetFragment.newInstance(mDishNameLabel.getText().toString(), mPriceLabel.getText().toString());
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        dishDetailBottomSheetFragment.show(fragmentManager, "test");
    }
}
