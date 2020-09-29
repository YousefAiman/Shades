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

import app.map.shades.Objects.Event;
import app.map.shades.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> implements View.OnClickListener{

  ArrayList<Event> events;
  Context context;
  int itemLayout;
  int height;
  int width;
  public EventsAdapter(ArrayList<Event> events, Context context, int itemLayout){
    this.events = events;
    this.context = context;
    this.itemLayout = itemLayout;
    height = (int) (context.getResources().getDisplayMetrics().heightPixels*0.225);
    width = (int) (context.getResources().getDisplayMetrics().widthPixels*0.5);
  }
  @NonNull
  @Override
  public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new EventsViewHolder(LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull final EventsViewHolder holder, final int position) {

    holder.itemView.getLayoutParams().height = height;
    holder.itemView.getLayoutParams().width = width;

    final Event event = events.get(position);
    Picasso.get().load(event.getImageUrl()).fit().centerCrop().into(holder.eventIv);
    holder.eventNameTv.setText(event.getName());
    holder.eventMakerTv.setText(event.getMaker());
    holder.eventTimeTv.setText(event.getTime());
    holder.itemView.setOnClickListener(this);
  }

  @Override
  public int getItemCount() {
    return events.size();
  }

  @Override
  public void onClick(View view) {
    view.findViewById(R.id.blueView).setBackgroundColor(Color.rgb(3,218,197));
  }

  static class EventsViewHolder extends RecyclerView.ViewHolder{
    ImageView eventIv;
    TextView eventTimeTv;
    TextView eventNameTv;
//    View blueView;
    TextView eventMakerTv;
    public EventsViewHolder(@NonNull View itemView) {
      super(itemView);
      eventIv = itemView.findViewById(R.id.eventIv);
      eventTimeTv = itemView.findViewById(R.id.eventTimeTv);
      eventNameTv = itemView.findViewById(R.id.eventNameTv);
//      blueView = itemView.findViewById(R.id.blueView);
      eventMakerTv = itemView.findViewById(R.id.eventMakerTv);
    }
  }
}
