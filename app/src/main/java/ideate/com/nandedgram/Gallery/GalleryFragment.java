package ideate.com.nandedgram.Gallery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import ideate.com.nandedgram.R;

public class GalleryFragment extends Fragment {
    private String TAG = "Demo";
    Button second, secondTime;
    private String TAG_FRAGMENT = "Second";
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach:cr frag A");
    }
    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img5",
            "Img6",
            "Img7",
            "Img8",


    };

    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,


    };
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView frag : A");
        View view = inflater.inflate(R.layout.fragment_gallary,
                container, false);


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<CreateList> createLists = prepareData();
        AdapterGallery adapter = new AdapterGallery(getActivity(), createLists);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private ArrayList<CreateList> prepareData(){

        ArrayList<CreateList> theimage = new ArrayList<>();
        for(int i = 0; i< image_titles.length; i++){
            CreateList createList = new CreateList();
            createList.setImage_title(image_titles[i]);
            createList.setImage_ID(image_ids[i]);
            theimage.add(createList);
        }
        return theimage;
    }

}