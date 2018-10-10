package ideate.com.nandedgram.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ideate.com.nandedgram.R;

public class HomeFragment extends Fragment {
    private String TAG = "Demo";
    Button second, secondTime;
    private String TAG_FRAGMENT = "Second";
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach:cr frag A");
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView frag : A");
        View view = inflater.inflate(R.layout.frag_home,
                container, false);
       // second = view.findViewById(R.id.bt_first_frag_second);
        /*second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SecondFragment secondFragment = new SecondFragment();
                fragmentTransaction.replace(R.id.fm_frag_container, secondFragment, TAG_FRAGMENT).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });*/



        return view;
    }
}