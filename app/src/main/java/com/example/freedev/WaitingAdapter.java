package com.example.freedev;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WaitingAdapter extends RecyclerView.Adapter<WaitingAdapter.ProcessViewHolder>{
    @NonNull
    private Context mContex;
    private List<Request> mRequest;
    private OnItemClickListener mListener;
    public WaitingAdapter(Context context,List<Request> requests){
        mContex =context;
        mRequest = requests;
    }
    @Override
    public ProcessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContex).inflate(R.layout.waiting_item,parent,false);
        return new WaitingAdapter.ProcessViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ProcessViewHolder holder, int position) {
        Request requestCurrent = mRequest.get(position);

        holder.Name.setText(requestCurrent.getName());
        holder.Email.setText(requestCurrent.getEmail());
        holder.Subject.setText(requestCurrent.getSubject());
        holder.Desc.setText(requestCurrent.getDescription());
        holder.Status.setText(requestCurrent.getStatus());


    }

    @Override
    public int getItemCount() {
        return mRequest.size();
    }

    public class ProcessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener,
            MenuItem.OnMenuItemClickListener {
        public TextView Name,Email,Subject,Desc,Status,Key;

        public ProcessViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.names);
            Email=itemView.findViewById(R.id.email);
            Subject=itemView.findViewById(R.id.subject);
            Desc=itemView.findViewById(R.id.desc);
            Status=itemView.findViewById(R.id.status);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mListener!=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem delete = menu.add(Menu.NONE,1,1,"Cancel");
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener!=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                   switch (item.getItemId()){
                       case 1:
                           mListener.onDeleteClick(position);
                           return true;
                   }
                }
            }
            return false;
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int postion);
        void onDeleteClick(int postion);
    }
public void setOnitemClickListener(OnItemClickListener listener){
mListener=listener;
}

}