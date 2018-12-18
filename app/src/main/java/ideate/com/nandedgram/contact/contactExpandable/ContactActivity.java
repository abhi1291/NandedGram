package ideate.com.nandedgram.contact.contactExpandable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ideate.com.nandedgram.R;
import ideate.com.nandedgram.contact.Contact;

public class ContactActivity extends AppCompatActivity{

    private ContactCategoryAdapter mAdapter;
    private RecyclerView recyclerView;
    private String[] categoryName = {"Executive Board","Employee Executive","Main Executive"};
    private String[] name = {"Abhi waikar","Aaanand Lagad","Gajanan Korde","Abhishek Chetre","Shripad Jadhav","Ravindra Gopal"};
    private String[] number = {"889595626","56466586474536","458887844","54845854"};
    private String[] designation = {"Sarpanch","Worker","Builder","Barber"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


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



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new ContactCategoryAdapter(this, movieCategories);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                ContactCategory expandedContactCategory = movieCategories.get(position);

                String toastMsg = getResources().getString(R.string.expanded, expandedContactCategory.getName());
                Toast.makeText(ContactActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                ContactCategory collapsedContactCategory = movieCategories.get(position);

                String toastMsg = getResources().getString(R.string.collapsed, collapsedContactCategory.getName());
                Toast.makeText(ContactActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);
    }
}