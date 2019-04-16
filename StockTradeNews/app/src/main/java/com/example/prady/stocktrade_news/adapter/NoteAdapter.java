package com.example.prady.stocktrade_news.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.prady.stocktrade_news.CustomOnItemClickListener;
import com.example.prady.stocktrade_news.FormAddUpdateActivity;
import com.example.prady.stocktrade_news.R;
import com.example.prady.stocktrade_news.entity.Note;

import java.util.LinkedList;

import androidx.annotation.NonNull;
//import java.util.logging.Filter;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewholder> implements Filterable{
    private LinkedList<Note> listNotes;
    private LinkedList<Note> listNotesfull;
    private Activity activity;

    class NoteViewholder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDescription, tvDate;
        CardView cvNote;

        public NoteViewholder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_item_title);
            tvDescription = (TextView)itemView.findViewById(R.id.tv_item_description);
            tvDate = (TextView)itemView.findViewById(R.id.tv_item_date);
            cvNote = (CardView)itemView.findViewById(R.id.cv_item_note);
        }
    }

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public LinkedList<Note> getListNotes() {
        return listNotes;
    }

    public void setListNotes(LinkedList<Note> listNotes) {
        this.listNotes = listNotes;
        this.listNotesfull = new LinkedList<>(this.listNotes);
    }

  /*  NoteAdapter(LinkedList<Note> listNotes) {
        this.listNotes = listNotes;
        listNotesfull = new LinkedList<>(listNotes);
    }*/

    @NonNull
    @Override
    public NoteViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewholder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewholder holder, int position) {
        holder.tvTitle.setText(getListNotes().get(position).getTitle());
        holder.tvDate.setText(getListNotes().get(position).getDate());
        holder.tvDescription.setText(getListNotes().get(position).getDescription());
        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, getListNotes().get(position));
                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));
    }

    /*public void updateList(LinkedList<Note> newList){
        listNotes = new LinkedList<>();
        listNotes.addAll(newList);
        notifyDataSetChanged();
    }*/

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            LinkedList<Note> filteredList = new LinkedList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listNotesfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                if(filterPattern.length() != 0){
                    for (Note item : listNotesfull) {
                        if (item.getTitle().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                else{
                    for (Note item: listNotesfull){
                        filteredList.add(item);
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listNotes.clear();
            Log.d("inside constraint", String.valueOf(constraint));
            listNotes.addAll((LinkedList) results.values);
            notifyDataSetChanged();
        }
    };


    @Override
    public int getItemCount() {
        return getListNotes().size();
    }

}
