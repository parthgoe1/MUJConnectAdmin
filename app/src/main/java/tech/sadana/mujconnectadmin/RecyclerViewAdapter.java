package tech.sadana.mujconnectadmin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<Details> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<Details> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_recyclerview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Details studentDetails = MainImageUploadInfoList.get(position);

        holder.StudentNameTextView.setText(studentDetails.getName());

        holder.StudentNumberTextView.setText(studentDetails.getRegNum());

    }

    @Override
    public int getItemCount() {

        if(MainImageUploadInfoList!=null)
        return MainImageUploadInfoList.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView StudentNameTextView;
        public TextView StudentNumberTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            StudentNameTextView =  itemView.findViewById(R.id.detailname);

            StudentNumberTextView =  itemView.findViewById(R.id.detailreg);
        }
    }
}