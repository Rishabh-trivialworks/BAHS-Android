package com.androidapp.bahs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.bahs.R;

import java.util.ArrayList;

/**
 * Created by Purnendu on 12/27/2016.
 */

public class ChangeMembershipAdaptor extends RecyclerView.Adapter<ChangeMembershipAdaptor.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mList;

    public ChangeMembershipAdaptor(Context context,ArrayList<String> list){
        this.mContext=context;
        this.mList=list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.change_membership_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView){
            super(itemView);
        }
    }
}
