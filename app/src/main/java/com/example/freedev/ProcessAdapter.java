    package com.example.freedev;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.List;

    public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ProcessViewHolder>{
        @NonNull
        private Context mContex;
        private List<Request> mRequest;
        public ProcessAdapter(Context context,List<Request> requests){
            mContex =context;
            mRequest = requests;
        }
        @Override
        public ProcessViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContex).inflate(R.layout.process_item,parent,false);
            return new ProcessAdapter.ProcessViewHolder(v);

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

        public class ProcessViewHolder extends RecyclerView.ViewHolder{
            public TextView Name,Email,Subject,Desc,Status,Key;

            public ProcessViewHolder(@NonNull View itemView) {
                super(itemView);

                Name=itemView.findViewById(R.id.names);
                Email=itemView.findViewById(R.id.email);
                Subject=itemView.findViewById(R.id.subject);
                Desc=itemView.findViewById(R.id.desc);
                Status=itemView.findViewById(R.id.status);

            }
        }


    }
