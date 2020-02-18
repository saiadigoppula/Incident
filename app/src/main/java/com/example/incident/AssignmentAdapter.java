package com.example.incident;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<AssignmentItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AssignmentAdapter.OnItemClickListener listener) {
        mListener = listener;
    }



    public AssignmentAdapter(Context context, ArrayList<AssignmentItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public AssignmentAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentAdapter.ExampleViewHolder(v);
    }


    @Override
    public void onBindViewHolder(AssignmentAdapter.ExampleViewHolder holder, int position) {
        AssignmentItem currentItem = mExampleList.get(position);


        String creatorName = currentItem.getCreator();


        holder.mTextViewCreator.setText("       "+ creatorName);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<AssignmentItem> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }



    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}