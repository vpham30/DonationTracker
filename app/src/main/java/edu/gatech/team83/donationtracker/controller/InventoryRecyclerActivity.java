package edu.gatech.team83.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Item;
import edu.gatech.team83.donationtracker.model.Location;

public class InventoryRecyclerActivity extends AppCompatActivity {

    private Location loc;
    private List<Item> inv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_list);
        if(getIntent().hasExtra("Location")) {
            loc = (Location) getIntent().getParcelableExtra("Location");
            inv = loc.getInventory();
        }
        View recyclerView = findViewById(R.id.recycler_view);
        setupRecyclerView((RecyclerView) recyclerView);
    }

    public void onAddItemPressed(View v) {
        Intent intent = new Intent(v.getContext(), ItemEditActivity.class);
        intent.putExtra("Location", loc);
        startActivity(intent);
    }

    public void onBackToInventoryPressed(View v) {
        startActivity(new Intent(v.getContext(), LocationRecyclerActivity.class));
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new InventoryAdapter(inv, loc));
    }

    public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

        private List<Item> inv;
        private Location loc;

        public InventoryAdapter(List<Item> inv, Location loc) {
            this.inv = inv;
            this.loc = loc;
        }

        @Override
        public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.inventory_list_item, parent, false);
            return new InventoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(InventoryViewHolder holder, final int position) {
            holder.itemName.setText(inv.get(position).getName());
            //Listener for when you click a item in the recycler
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //takes us to the Edit page with the Loc's info
                    Context context = view.getContext();
                    //this needs some work
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    //sends the id of the location
                    intent.putExtra("Item", inv.get(position));
                    intent.putExtra("Location", loc);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return inv.size();
        }

        class InventoryViewHolder extends RecyclerView.ViewHolder {

            TextView itemName;
            View itemView;

            public InventoryViewHolder(View itemView) {
                super(itemView);

                this.itemView = itemView;
                itemName = itemView.findViewById(R.id.item_name_test);
            }
        }

    }
}
