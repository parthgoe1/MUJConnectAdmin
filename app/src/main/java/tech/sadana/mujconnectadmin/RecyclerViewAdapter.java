package tech.sadana.mujconnectadmin;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DatabaseReference mDatabase;
// ...
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail().toString();
        String club = new String ();
        if(email.equals("mujaperture@gmail.com")) {
            club = "Aperture";
            mDatabase = FirebaseDatabase.getInstance().getReference("aperture");
        }
        else{
            mDatabase = null;
            club = null;
        }
        final Details studentDetails = MainImageUploadInfoList.get(position);

        holder.StudentNameTextView.setText(studentDetails.getFirstName() +" "+studentDetails.getLastName() );
        final String localReg = studentDetails.getRegNum();
        holder.StudentNumberTextView.setText(studentDetails.getRegNum());
        DatabaseReference checkPaid = mDatabase.child(localReg.toString()).child("paid");

        final String finalClub = club;
        holder.Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mDatabase.child(localReg.toString()).child("paid").setValue("true");
                Button pay = view.findViewById(R.id.payButton);
                pay.setVisibility(View.GONE);
                holder.StudentNameTextView.setTextColor(Color.parseColor("#96c93d"));
                holder.StudentNumberTextView.setTextColor(Color.parseColor("#96c93d"));

                try {
                    GMailSender sender = new GMailSender("elicit2018@gmail.com", "tushar123");
                    sender.sendMail("Your Reciept for Registration in "+ finalClub,
                            "You have successfully paid for the Registration and is completed",
                            "elicit2018@gmail.com",
                            studentDetails.getEmail().toString());
                } catch (Exception e) {
                    Toast.makeText(view.getContext(),"Email not sent",Toast.LENGTH_SHORT).show();
                }


            }
        });

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
        public Button Pay;

        public ViewHolder(View itemView) {

            super(itemView);

            StudentNameTextView =  itemView.findViewById(R.id.detailname);

            StudentNumberTextView =  itemView.findViewById(R.id.detailreg);
            Pay = itemView.findViewById(R.id.payButton);
        }
    }
}