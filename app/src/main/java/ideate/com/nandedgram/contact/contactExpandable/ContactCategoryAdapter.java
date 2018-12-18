package ideate.com.nandedgram.contact.contactExpandable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import ideate.com.nandedgram.R;
import ideate.com.nandedgram.contact.Contact;

public class ContactCategoryAdapter extends ExpandableRecyclerAdapter<ContactCategoryViewHolder, ContactViewHolder> {

    private LayoutInflater mInflator;

    public ContactCategoryAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public ContactCategoryViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View movieCategoryView = mInflator.inflate(R.layout.movie_category_view, parentViewGroup, false);
        return new ContactCategoryViewHolder(movieCategoryView);
    }

    @Override
    public ContactViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View moviesView = mInflator.inflate(R.layout.contact_list_item, childViewGroup, false);
        return new ContactViewHolder(moviesView);
    }

    @Override
    public void onBindParentViewHolder(ContactCategoryViewHolder movieCategoryViewHolder, int position, ParentListItem parentListItem) {
        ContactCategory movieCategory = (ContactCategory) parentListItem;
        movieCategoryViewHolder.bind(movieCategory);
    }

    @Override
    public void onBindChildViewHolder(ContactViewHolder moviesViewHolder, int position, Object childListItem) {
        Contact movies = (Contact) childListItem;
        moviesViewHolder.bind(movies);
    }
}
