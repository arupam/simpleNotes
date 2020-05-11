package com.example.simplenotes;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class notesAdapter  extends RecyclerView.Adapter<notesAdapter.ViewHolder>  {

    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mContent = new ArrayList<>();
    private Context mContext;

    public notesAdapter(ArrayList<String> mTitle, ArrayList<String> mContent, Context mContext) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating views here
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notecard, parent , false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Toast.makeText(mContext, "new note added!", Toast.LENGTH_SHORT).show();

        holder.title.setText(mTitle.get(position));
        holder.content.setText(mContent.get(position));

        holder.noteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(),Editor.class);
                intent.putExtra("noteId",position);//passing which note is tapped to be edited
                v.getContext().startActivity(intent);

            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote(position,holder);
            }
        });

    }

    private void deleteNote(int position, ViewHolder holder) {

        mContent.remove(position);
        mTitle.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mTitle.size());
        holder.itemView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //holds widget in memory i.e individual views

        TextView title , content ;
        CardView noteCard;
        Button deletebtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.noteCardTitle);
            content=itemView.findViewById(R.id.noteCardContent);
            noteCard=itemView.findViewById(R.id.noteCard);
            deletebtn = itemView.findViewById(R.id.noteCardDel);
        }
    }


}
