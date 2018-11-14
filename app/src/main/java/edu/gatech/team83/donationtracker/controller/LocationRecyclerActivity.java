package edu.gatech.team83.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Location;
import edu.gatech.team83.donationtracker.model.Model;

public class LocationRecyclerActivity extends AppCompatActivity {

//    RecyclerView recyclerView;
//    LocationAdapter.LocationViewHolder
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        //Set up the recycle view
        //Need to know what layout to use\
        model = Model.getInstance();
        View recyclerView = findViewById(R.id.recycler_view);
        setupRecyclerView((RecyclerView) recyclerView);
    }

    public void onAddLocationPressed(View v) {
        if ("Admin".equals(model.getType())) {
            Context context = v.getContext();
            Intent intent = new Intent(context, LocationEditActivity.class);
            startActivity(intent);
        } else {
            Snackbar failed = Snackbar.make(v, "You are not an admin", Snackbar.LENGTH_SHORT);
            failed.show();
        }
    }

    public void onBackPressed(View v) {
        Context context = v.getContext();
        if (getIntent().hasExtra("Act")
                && "SearchActivity".equals(getIntent().getStringExtra("Act"))) {
            Intent intent = new Intent(context, SearchActivity.class);
            startActivity(intent);
        }
        Intent intent = new Intent(context, WelcomeActivity.class);
        startActivity(intent);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        List<Location> list;
        if (getIntent().hasExtra("Locations")) {
            list = getIntent().getParcelableArrayListExtra("Locations");
        } else {
            list = model.getLocations();
        }
        recyclerView.setAdapter(new LocationAdapter(list));
    }

    public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

        private final List<Location> locList;

        LocationAdapter(List<Location> locList) {
            this.locList = locList;
        }

        @Override
        public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.location_list_item, parent, false);
            return new LocationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final LocationViewHolder holder, int position) {
            holder.locName.setText(locList.get(position).getName());
            //Listener for when you click a item in the recycler
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getIntent().hasExtra("Act")
                            && "SearchActivity".equals(getIntent().getStringExtra("Act"))) {
                        Location location = locList.get(holder.getAdapterPosition());
                        Intent intent = new Intent(view.getContext(), SearchActivity.class);
                        intent.putExtra("Location", location);
                        intent.putExtra("Act", "LocationRecyclerActivity");
                        startActivity(intent);
                    } else {
                        //takes us to the Edit page with the Loc's info
                        Context context = view.getContext();
                        Intent intent = new Intent(context, LocationDetailActivity.class);
                        //sends the id of the location
                        intent.putExtra("Location", locList.get(holder.getAdapterPosition()));
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return locList.size();
        }

        class LocationViewHolder extends RecyclerView.ViewHolder {

            final TextView locName;
            final View itemView;

            LocationViewHolder(View itemView) {
                super(itemView);

                this.itemView = itemView;
                locName = itemView.findViewById(R.id.location_name_test);
            }
        }

    }

}
