package hk.edu.uic.dishordering;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DishViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.dish_name_label)
    TextView mDishNameLabel;

    public DishViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
