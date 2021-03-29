package com.example.recyclerview3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsAdapterVh> implements Filterable {

    private List<WordModel> wordModelList;
    private List<WordModel> getwordModelListFiltered;
    private Context context;
    private SelectedWord selectedWord;

    public WordsAdapter(List<WordModel> wordModelList, SelectedWord selectedWord) {
        this.wordModelList = wordModelList;
        this.getwordModelListFiltered = wordModelList;
        this.selectedWord = selectedWord;
    }

    @NonNull
    @Override
    public WordsAdapter.WordsAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new WordsAdapterVh(LayoutInflater.from(context).inflate(R.layout.row_words, null));
    }

    @Override
    public void onBindViewHolder(@NonNull WordsAdapter.WordsAdapterVh holder, int position) {
        WordModel wordModel = wordModelList.get(position);

        String wordname = wordModel.getWord();
        String prefix = wordModel.getWord().substring(0,1);

        holder.tvWordName.setText(wordname);
        holder.tvPrefix.setText(prefix);
    }

    @Override
    public int getItemCount() {
        return wordModelList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if(constraint == null || constraint.length() == 0){
                    filterResults.count = getwordModelListFiltered.size();
                    filterResults.values = getwordModelListFiltered;
                }
                else{
                    String searchChar = constraint.toString().toLowerCase();
                    List<WordModel> resultData = new ArrayList<>();

                    for(WordModel wordModel:getwordModelListFiltered){
                        if (wordModel.getWord().toLowerCase().contains(searchChar)){
                            resultData.add(wordModel);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                wordModelList = (List<WordModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public interface SelectedWord{
        void selectedWord(WordModel wordModel);

        boolean OnOptionsItemSelected(@NonNull MenuItem item);
    }

    public class WordsAdapterVh extends RecyclerView.ViewHolder {
        TextView tvPrefix;
        TextView tvWordName;
        ImageView imIcon;
        public WordsAdapterVh(@NonNull View itemView) {
            super(itemView);

            tvPrefix = itemView.findViewById(R.id.prefix);
            tvWordName = itemView.findViewById(R.id.word);
            imIcon = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedWord.selectedWord(wordModelList.get(getAdapterPosition()));
                }
            });
        }
    }
}
