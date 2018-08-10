package tech.sadana.mujconnectadmin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DatabaseReference mDatabase;
// ...
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail().toString();
        String club = new String();
        final String subject;
        final String revoke;
        String body;
        if (email.equals("mujaperture@gmail.com")) {
            club = "Aperture";
            subject= "Registration Successful for Aperture";
            revoke = "Your Registration is revoked for Aperture";
            mDatabase = FirebaseDatabase.getInstance().getReference("aperture");
        } else {
            mDatabase = null;
            club = null;
            subject = null;
            revoke = null;
        }
        final Details studentDetails = MainImageUploadInfoList.get(position);

        holder.StudentNameTextView.setText(studentDetails.getFirstName() + " " + studentDetails.getLastName());
        final String localReg = studentDetails.getRegNum();
        holder.StudentNumberTextView.setText(studentDetails.getRegNum());
        String checkPaid = MainImageUploadInfoList.get(position).getPaid().toString();
        if (checkPaid.equals("true")) {


            holder.Pay.setVisibility(View.GONE);
            holder.StudentNameTextView.setTextColor(Color.parseColor("#96c93d"));
            holder.StudentNumberTextView.setTextColor(Color.parseColor("#96c93d"));
            holder.layoutMain.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    mDatabase.child(localReg.toString()).child("paid").setValue("false");
                    MainImageUploadInfoList.get(position).setPaid("false");

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] {studentDetails.getEmail()});
                    intent.putExtra(Intent.EXTRA_SUBJECT,subject );
                    intent.putExtra(Intent.EXTRA_TEXT,revoke);

                    context.startActivity(intent);



                    return false;
                }
            });




        } else{

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
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] {studentDetails.getEmail()});
                    intent.putExtra(Intent.EXTRA_SUBJECT,subject );
                    intent.putExtra(Intent.EXTRA_TEXT,"Hi");

                    context.startActivity(intent);



                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Email not sent", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

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
        public RelativeLayout layoutMain;

        public ViewHolder(View itemView) {

            super(itemView);

            StudentNameTextView =  itemView.findViewById(R.id.detailname);

            StudentNumberTextView =  itemView.findViewById(R.id.detailreg);
            Pay = itemView.findViewById(R.id.payButton);
            layoutMain = itemView.findViewById(R.id.mainRel);
        }
    }
}