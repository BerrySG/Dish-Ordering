package hk.edu.uic.dishordering;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DishViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.dish_name_label)
    TextView mDishNameLabel;

    @Bind(R.id.price_label)
    TextView mPriceLabel;

    public DishViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
