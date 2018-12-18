package ideate.com.nandedgram.contact.contactExpandable;

import java.util.List;

import ideate.com.nandedgram.contact.Contact;

public class ContactCategory implements ParentListItem {
    private String mName;
    private List<Contact> contacts;

    public ContactCategory(String name, List<Contact> contacts) {
        mName = name;
        this.contacts = contacts;
    }

    public String getName() {
        return mName;
    }

    @Override
    public List<?> getChildItemList() {
        return contacts;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
