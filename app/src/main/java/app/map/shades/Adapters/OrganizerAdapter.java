package app.map.shades.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.map.shades.Objects.Organizer;
import app.map.shades.R;

public class OrganizerAdapter extends RecyclerView.Adapter<OrganizerAdapter.OrganizerHolder> implements View.OnClickListener{

  ArrayList<Organizer> organizers;
  Context context;
  int itemLayout;
  int height;
  int width;
  public OrganizerAdapter(ArrayList<Organizer> organizers, Context context, int itemLayout){
    this.organizers = organizers;
    this.context = context;
    this.itemLayout = itemLayout;
    height = (int) (context.getResources().getDisplayMetrics().heightPixels*0.225);
    width = (int) (context.getResources().getDisplayMetrics().widthPixels*0.305);
  }
  @NonNull
  @Override
  public OrganizerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new OrganizerHolder(LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull final OrganizerHolder holder, final int position) {

    holder.itemView.getLayoutParams().height = height;
    holder.itemView.getLayoutParams().width = width;

    final Organizer organizer = organizers.get(position);
    Picasso.get().load(organizer.getImageUrl()).fit().centerCrop().into(holder.organizerIv);
    holder.organizerNameTv.setText(organizer.getName());
    holder.organizerProfessionTv.setText(organizer.getProfession());
    holder.itemView.setOnClickListener(this);
  }

  @Override
  public int getItemCount() {
    return organizers.size();
  }

  @Override
  public void onClick(View view) {
    view.findViewById(R.id.blueView).setBackgroundColor(Color.rgb(3,218,197));
  }

  static class OrganizerHolder extends RecyclerView.ViewHolder{
    ImageView organizerIv;
    TextView organizerNameTv;
    TextView organizerProfessionTv;
    View blueView;

    public OrganizerHolder(@NonNull View itemView) {
      super(itemView);
      organizerIv = itemView.findViewById(R.id.organizerIv);
      organizerNameTv = itemView.findViewById(R.id.organizerNameTv);
      organizerProfessionTv = itemView.findViewById(R.id.organizerProfessionTv);
      blueView = itemView.findViewById(R.id.blueView);

    }
  }
}
