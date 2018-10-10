package ideate.com.nandedgram;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ideate.com.nandedgram.ContactFragment.OnListFragmentInteractionListener;
import ideate.com.nandedgram.dummy.DummyContent.DummyItem;
import ideate.com.nandedgram.model.Contact;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ContactItemRecyclerViewAdapter extends RecyclerView.Adapter<ContactItemRecyclerViewAdapter.MyViewHolder> {

    ArrayList<Contact> contactList;
    private Context mContext;

    public ContactItemRecyclerViewAdapter(ArrayList<Contact> contactList, Activity context) {
        this.contactList = contactList;
        this.mContext = context;
        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, designation, phoneNumber;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.contact_name);
            designation = view.findViewById(R.id.contact_designation);
            phoneNumber = view.findViewById(R.id.contact_phone_number);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.designation.setText(contactList.get(position).getDesignation());
        holder.name.setText(contactList.get(position).getName());
        holder.phoneNumber.setText(contactList.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}