package ideate.com.nandedgram.contact.contactExpandable;

import android.view.View;
import android.widget.TextView;

import ideate.com.nandedgram.R;
import ideate.com.nandedgram.contact.Contact;

public class ContactViewHolder extends ChildViewHolder {

    private TextView mContactName,mMobileNo,mDesignation;

    public ContactViewHolder(View itemView) {
        super(itemView);
        mContactName = (TextView) itemView.findViewById(R.id.contact_name);
        mMobileNo = (TextView) itemView.findViewById(R.id.contact_phone_number);
        mDesignation = (TextView) itemView.findViewById(R.id.contact_designation);
    }

    public void bind(Contact contact) {
        mMobileNo.setText(contact.getPhoneNumber());
        mContactName.setText(contact.getName());
        mDesignation.setText(contact.getDesignation());
    }
}
