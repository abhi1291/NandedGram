package ideate.com.nandedgram.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ideate.com.nandedgram.R;
import ideate.com.nandedgram.Gallery.DummyContent.DummyItem;
import ideate.com.nandedgram.contact.contactExpandable.ContactActivity;
import ideate.com.nandedgram.contact.contactExpandable.ContactCategory;
import ideate.com.nandedgram.contact.contactExpandable.ContactCategoryAdapter;
import ideate.com.nandedgram.contact.contactExpandable.ExpandableRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ContactFragment extends Fragment {


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private ContactCategoryAdapter mAdapter;
    private RecyclerView recyclerView;
    private String[] categoryName = {"Executive Board","Employee Executive","Main Executive"};
    private String[] name = {"Abhi waikar","Aaanand Lagad","Gajanan Korde","Abhishek Chetre","Shripad Jadhav","Ravindra Gopal"};
    private String[] number = {"889595626","56466586474536","458887844","54845854"};
    private String[] designation = {"Sarpanch","Worker","Builder","Barber"};

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContactFragment newInstance(int columnCount) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contact, container, false);

        final List<ContactCategory> movieCategories = new ArrayList<>();
        Random random = new Random();

        for (int j = 0; j < 3; j++) {
            ArrayList<Contact> contactsList =new ArrayList<>();
            for (int i = 1; i < 100; i++) {
                Contact contact = new Contact();
                contact.setDesignation(designation[random.nextInt(designation.length)]);
                contact.setPhoneNumber(number[random.nextInt(number.length)]);
                contact.setName(name[random.nextInt(name.length)]);
                contactsList.add(contact);
            }
            movieCategories.add(new ContactCategory(categoryName[j],contactsList));
        }



        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mAdapter = new ContactCategoryAdapter(getActivity(), movieCategories);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                ContactCategory expandedContactCategory = movieCategories.get(position);

                String toastMsg = getResources().getString(R.string.expanded, expandedContactCategory.getName());
                Toast.makeText(getActivity(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                ContactCategory collapsedContactCategory = movieCategories.get(position);

                String toastMsg = getResources().getString(R.string.collapsed, collapsedContactCategory.getName());
                Toast.makeText(getActivity(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }


}
