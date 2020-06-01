package com.example.kitchendiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<FoodViewHolder>{

    private Context mContext;
    private List<FoodData> myFoodList;
    private int lastPosition =-1;

    public Adapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }



    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item,parent,false );
        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, int position) {
        Glide.with(mContext).load(myFoodList.get(position).getImage()).into(holder.imageView);
        holder.mTitle.setText(myFoodList.get(position).getName());
        holder.mDescription.setText(myFoodList.get(position).getDescription());
        holder.mTime.setText(myFoodList.get(position).getTime());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (mContext,DetailActivity.class);

                intent.putExtra( "Image", myFoodList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Description",myFoodList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Time",myFoodList.get(holder.getAdapterPosition()).getTime());
                intent.putExtra("Name",myFoodList.get(holder.getAdapterPosition()).getName());
                mContext.startActivity(intent);

            }
        });
        setAnimation(holder.itemView,position);
    }
    public void setAnimation(View viewToAnimate, int position){
        if(position>lastPosition){
            ScaleAnimation animation= new ScaleAnimation(0.0f,1.0f,0.0f,1.0f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1000);
            viewToAnimate.startAnimation(animation);
            lastPosition=position;
        }


    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public  void filteredList(ArrayList<FoodData> filterList) {
        myFoodList = filterList;
        notifyDataSetChanged();

    }


}
class FoodViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView mTitle,mDescription,mTime;
    CardView mCardView;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mTitle=itemView.findViewById(R.id.tvTitle);
        mDescription=itemView.findViewById(R.id.tvDescription);
        mTime=itemView.findViewById(R.id.tvTime);
        mCardView = itemView.findViewById(R.id.myCardView);
    }
}

