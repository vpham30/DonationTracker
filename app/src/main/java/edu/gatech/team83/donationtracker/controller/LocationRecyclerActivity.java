package edu.gatech.team83.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.team83.donationtracker.R;
import edu.gatech.team83.donationtracker.model.Location;

public class LocationRecyclerActivity extends AppCompatActivity {

//    RecyclerView recyclerView;
//    LocationAdapter.LocationViewHolder

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        //Set up the recycle view
        //Need to know what layout to use
        View recyclerView = findViewById(R.id.recycler_view);
        setupRecyclerView((RecyclerView) recyclerView);
    }

    public void onAddLocationPressed(View v) {
        // take us to a blank edit page
        Context context = v.getContext();
        //TODO verify class name
        Intent intent = new Intent(context, LocationEditActivity.class);
        startActivity(intent);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        //TODO Database stuff here
        recyclerView.setAdapter(new LocationAdapter(list));
    }

    public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

        private Context con;
        private List<Location> locList;

        public LocationAdapter(Context con, List<Location> locList) {
            this.con = con;
            this.locList = locList;
        }

        @Override
        public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(con);
            View view = inflater.inflate(R.layout.location_list_item, null);
            return new LocationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(LocationViewHolder holder, final int position) {
            holder.locName.setText(locList.get(position).getName());
            //Listener for when you click a item in the recycler
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //takes us to the Edit page with the Loc's info
                    Context context = view.getContext();
                    //this needs some work
                    //TODO verify class name
                    Intent intent = new Intent(context, LocationEditActivity.class);
                    //sends the id of the location
                    //TODO verify works
                    intent.putExtra("Location Position", position);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return locList.size();
        }

        class LocationViewHolder extends RecyclerView.ViewHolder {

            TextView locName;
            View itemView;

            public LocationViewHolder(View itemView) {
                super(itemView);

                this.itemView = itemView;
                locName = itemView.findViewById(R.id.location_name_test);
            }
        }

    }
}
