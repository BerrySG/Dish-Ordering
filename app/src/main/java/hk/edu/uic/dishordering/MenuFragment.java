package hk.edu.uic.dishordering;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import hk.edu.uic.dishordering.Model.DishSubsystem.Dish;


public class MenuFragment extends Fragment {

    private FirebaseRecyclerAdapter<Dish, DishViewHolder> mAdapter;

    @Bind(R.id.menu_recycler_view)
    RecyclerView mMenuRecyclerView;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        ButterKnife.bind(this, view);

        mMenuRecyclerView.setHasFixedSize(true);
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch data from menu
        Firebase menuRef = new Firebase("https://dish-ordering.firebaseio.com/menu");
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mAdapter = new FirebaseRecyclerAdapter<Dish, DishViewHolder>(Dish.class, R.layout.item_dish, DishViewHolder.class, menuRef) {
            @Override
            protected void populateViewHolder(DishViewHolder dishViewHolder, Dish dish, int i) {
                dishViewHolder.mDishNameLabel.setText(dish.getName());
            }
        };

        mMenuRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
