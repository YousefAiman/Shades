package app.map.shades.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.map.shades.Objects.NearByPlace;
import app.map.shades.R;

public class NearByMapAdapter extends RecyclerView.Adapter<NearByMapAdapter.NearByMapViewHolder>{

  ArrayList<NearByPlace> nearByPlaces;
  int lastClickedItem = -1;
  Context context;
  RecyclerView recyclerView;
  int itemLayout;
  int height;
  int width;
  private static RecyclerViewClickListener itemListener;

  public interface RecyclerViewClickListener {
    void recyclerViewListClicked(View v, int position);
  }

  public NearByMapAdapter(ArrayList<NearByPlace> nearByPlaces, Context context,int itemLayout,RecyclerViewClickListener itemListener){
    this.nearByPlaces = nearByPlaces;
    this.context = context;
    this.itemLayout = itemLayout;
//    this.listener = listener;
    NearByMapAdapter.itemListener = itemListener;
    height = (int) (context.getResources().getDisplayMetrics().heightPixels*0.23);
    width = (int) (context.getResources().getDisplayMetrics().widthPixels*0.41);
  }
  @NonNull
  @Override
  public NearByMapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new NearByMapViewHolder(LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull final NearByMapViewHolder holder, final int position) {

    holder.itemView.getLayoutParams().height = height;
    holder.itemView.getLayoutParams().width = width;

    final NearByPlace place = nearByPlaces.get(position);
    Picasso.get().load(place.getImageUrl()).fit().centerCrop().into(holder.nearByIv);
    holder.nearByNameTv.setText(place.getName());
    holder.nearByInfoTv.setText(place.getDistance()+"mi, "+place.getRating()+" stars");

  }

  @Override
  public int getItemCount() {
    return nearByPlaces.size();
  }


   static class NearByMapViewHolder extends RecyclerView.ViewHolder{
    ImageView nearByIv;
    TextView nearByNameTv;
    TextView nearByInfoTv;
    View blueView;
    public NearByMapViewHolder(@NonNull View itemView) {
      super(itemView);
      nearByIv = itemView.findViewById(R.id.nearByIv);
      nearByNameTv = itemView.findViewById(R.id.nearByNameTv);
      nearByInfoTv = itemView.findViewById(R.id.nearByInfoTv);
      blueView = itemView.findViewById(R.id.blueView);
      itemView.setOnClickListener(v -> itemListener.recyclerViewListClicked(v,getAdapterPosition()));
    }
  }
}
