package app.feed.com.ui.common.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.List;

import javax.inject.Inject;

import app.feed.com.BR;
import app.feed.com.R;
import app.feed.com.data.entities.response.HomeListResponse;
import app.feed.com.ui.injection.ActivityContext;

/**
 * Created by debu on 17/10/17.
 */

public class AdapterListing extends RecyclerView.Adapter<AdapterListing.ListHolder>{
    public interface ListItemClickListener{
        void onListDetailsClicked(int pos);
        void onFabClicked(int pos,boolean isChecked);
    }
    private List<HomeListResponse> listResponses;
    private Context context;
    private ListItemClickListener itemClickListener;

    @Inject
    public AdapterListing(@ActivityContext Context context) {
        this.context = context;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListHolder(DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false)));
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.getDataBinding().setVariable(BR.model,listResponses.get(holder.getAdapterPosition()));
        holder.setListeners(holder.getAdapterPosition());
        holder.getDataBinding().executePendingBindings();
    }

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return listResponses != null ? listResponses.size():0;
    }

    class ListHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding dataBinding;
        private CardView cardView;
        private ToggleButton fabButton;
        ListHolder(ViewDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
            this.cardView = (CardView)dataBinding.getRoot().findViewById(R.id.card);
            this.fabButton = (ToggleButton) dataBinding.getRoot().findViewById(R.id.toggle_fab);
        }

        public void setListeners(final int pos){
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener != null)
                        itemClickListener.onListDetailsClicked(pos);
                }
            });

            fabButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(itemClickListener != null)
                        itemClickListener.onFabClicked(pos,isChecked);
                }
            });
        }

        public ViewDataBinding getDataBinding() {
            return dataBinding;
        }
    }

    public void setData(List<HomeListResponse> listResponses){
        this.listResponses = listResponses;
        notifyDataSetChanged();
    }
}
