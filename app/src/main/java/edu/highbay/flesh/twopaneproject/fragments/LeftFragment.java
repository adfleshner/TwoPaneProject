package edu.highbay.flesh.twopaneproject.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import edu.highbay.flesh.twopaneproject.R;

/**
 * Created by Aaron on 4/14/2016.
 */
public class LeftFragment extends Fragment {

    private static final String FRAG = "Frag";
    private static final String CURRENT_POSITION = "POS";
    private static final String CURRENT_TAG = "TAG";
    private FragmentManager fm;
    AHBottomNavigation bottomNavigation;
    int currentTabPos = 0;
    private String currentTag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getChildFragmentManager();
        if (savedInstanceState == null) {
            //Initially go to the Account tab.
            goToFragment(new NavigationFragment());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_POSITION, currentTabPos);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_left, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpBottomNavigation(view);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(CURRENT_POSITION)) {
                currentTabPos = savedInstanceState.getInt(CURRENT_POSITION);
                bottomNavigation.setCurrentItem(currentTabPos, false);
            }
            if (savedInstanceState.containsKey(CURRENT_TAG)) {
                currentTag = savedInstanceState.getString(CURRENT_TAG);
                goToFragment(fm.findFragmentByTag(currentTag));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setUpBottomNavigation(View view) {
        bottomNavigation = (AHBottomNavigation) view.findViewById(R.id.bottom_navigation);
        final AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.search, R.drawable.ic_magnify_white_48dp, R.color.white);
        final AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.search, R.drawable.ic_magnify_white_48dp, R.color.white);
        final AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.search, R.drawable.ic_magnify_white_48dp, R.color.white);
        final AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.search, R.drawable.ic_magnify_white_48dp, R.color.white);

        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                bottomNavigation.addItem(item1);
                bottomNavigation.addItem(item2);
                bottomNavigation.addItem(item3);
                bottomNavigation.addItem(item4);
            }
        };
        handler.postDelayed(r, 1);

        //Always display titles
        bottomNavigation.setForceTitlesDisplay(true);


//        // Set background color
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.white));
        bottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimary));
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        currentTabPos = 0;
                        goToFragment(new NavigationFragment());
                        break;
                    case 1:
                        currentTabPos = 1;
                        goToFragment(new Fragment());
                        break;
                    case 2:
                        currentTabPos = 2;
                        goToFragment(new Fragment());
                        Toast.makeText(getActivity(), "Open not created yet", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        currentTabPos = 3;
                        goToFragment(new NavigationFragment());
                        break;
                    default:
                        //do nothing
                }
            }
        });

    }

    private void goToFragment(Fragment currentFragment) {
        currentTag = currentFragment.getClass().getSimpleName();
        fm.beginTransaction().replace(R.id.navigationChildFrame, currentFragment, currentTag).commit();
    }
}
