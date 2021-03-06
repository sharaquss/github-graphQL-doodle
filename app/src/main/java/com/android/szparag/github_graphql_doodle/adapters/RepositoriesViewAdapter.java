package com.android.szparag.github_graphql_doodle.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.szparag.github_graphql_doodle.R;
import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.facebook.stetho.inspector.helper.IntegerFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ciemek on 06/11/2016.
 *
 * Implementation of BaseRecyclerViewAdapter for Github's Repository object.
 */
public class RepositoriesViewAdapter extends BaseRecyclerViewAdapter<Repository> {

    public RepositoriesViewAdapter(@Nullable RecyclerOnPosClickListener clickListener) {
        super(clickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoriesViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.item_recycler_repositories_row,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolder((RepositoriesViewHolder) holder, position);
    }

    private void onBindViewHolder(RepositoriesViewHolder holder, int position) {
        Repository item = items.get(position);

        holder.name.setText(item.getName());
        if (item.getDescription() == null || item.getDescription() == " ") {
            holder.desc.setVisibility(View.GONE);
        } else {
            holder.desc.setText(item.getDescription());
            holder.desc.setVisibility(View.VISIBLE);
        }
        holder.path.setText(item.getPath());
        holder.stats.setText(
                holder.stats.getContext()
                        .getString(
                                R.string.repository_stats,
                                item.getStargazers().getTotalCount(),
                                item.getForks().getTotalCount(),
                                item.getWatchers().getTotalCount()
                                )
        );

    }


    public class RepositoriesViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_recycler_repository_row_name)
        TextView name;

        @BindView(R.id.item_recycler_repository_row_description)
        TextView desc;

        @BindView(R.id.item_recycler_repository_row_path)
        TextView path;

        @BindView(R.id.item_recycler_repository_row_stats)
        TextView stats;

        public RepositoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (recyclerOnPosClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerOnPosClickListener.onPosClick(view, getLayoutPosition());
                    }
                });
            }

        }
    }

}
