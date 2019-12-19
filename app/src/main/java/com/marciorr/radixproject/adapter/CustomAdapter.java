package com.marciorr.radixproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.marciorr.radixproject.DetalheActivity;
import com.marciorr.radixproject.R;
import com.marciorr.radixproject.model.RetroUser;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RetroUser> dataList;
    private Context context;

    public CustomAdapter(Context context, List<RetroUser> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    //Cria a lista de repositórios
    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtDescription;
        TextView txtName;
        private CardView cardViewFriend;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtDescription = mView.findViewById(R.id.description);
            txtName = mView.findViewById(R.id.name);
            cardViewFriend = mView.findViewById(R.id.card_view_friend);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    //Define posições na lista e adiciona o evento de clique
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        holder.txtName.setText(dataList.get(position).getName());
        holder.txtDescription.setText(dataList.get(position).getDescription());

        holder.cardViewFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), DetalheActivity.class);
                intent.putExtra("name", dataList.get(position).getName());
                intent.putExtra("description", dataList.get(position).getDescription());
                intent.putExtra("stargazers_count", dataList.get(position).getStargazers_count());
                intent.putExtra("forks_count", dataList.get(position).getForks_count());
                intent.putExtra("created_at", dataList.get(position).getCreated_at());
                intent.putExtra("open_issues_count", dataList.get(position).getOpen_issues_count());
                intent.putExtra("language", dataList.get(position).getLanguage());
                intent.putExtra("html_url", dataList.get(position).getHtml_url());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
