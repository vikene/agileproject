package com.example.prady.stocktrade_news;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.prady.stocktrade_news.models.Article;
import com.example.prady.stocktrade_news.models.MarketWatch;

import java.util.List;

public class AdapterMW extends RecyclerView.Adapter<AdapterMW.MyViewHolder>{

    private List<MarketWatch> markets;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public AdapterMW(List<MarketWatch> markets, Context context) {
        this.markets = markets;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stocks, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        MarketWatch model = markets.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(model.getSymbol())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);
        holder.ticker.setText(model.getSymbol());
        holder.company.setText(model.getName());
        holder.price.setText(model.getCurrency() + " " + model.getPrice());
        holder.change.setText(model.getChangePct());
//        holder.source.setText(model.getSource().getName());
//        holder.time.setText("\u2022" + Utils.DateToTimeFormat(model.getPublishedAt()));
//        holder.published_at.setText(Utils.DateFormat(model.getPublishedAt()));
//        holder.author.setText(model.getAuthor());

    }

    @Override
    public int getItemCount() {
        return markets.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends ViewHolder implements View.OnClickListener{

        TextView ticker, company, price, change;
        ImageView imageView;
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;


        public MyViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            ticker = itemView.findViewById(R.id.title);
            company = itemView.findViewById(R.id.desc);
            price = itemView.findViewById(R.id.author);
            change = itemView.findViewById(R.id.publishedAt);

            imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.progess_load_photo);

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
