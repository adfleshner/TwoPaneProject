package edu.highbay.flesh.twopaneproject.activities;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import edu.highbay.flesh.twopaneproject.fragments.DetailFragment;
import edu.highbay.flesh.twopaneproject.fragments.LeftFragment;
import edu.highbay.flesh.twopaneproject.fragments.NavigationFragment;
import edu.highbay.flesh.twopaneproject.R;

public class HostActivity extends AppCompatActivity implements NavigationFragment.OnListItemSelectedListener {


    private boolean isTablet;
    private boolean isPortrait;
    private Toolbar myToolbar, secondaryToolbar;
    private Fragment leftFragment, detailFragment;
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);
        fm = getSupportFragmentManager();
        init();
        // set boolean flags so we know the devices configuration
        isTablet = getResources().getBoolean(R.bool.isTablet);
        isPortrait = getResources().getBoolean(R.bool.isPortrait);
        if (savedInstanceState == null) {
            leftFragment = new LeftFragment();
            detailFragment = new DetailFragment();
            if (isTablet) {
                showFragment(leftFragment, true, false, LeftFragment.class.getSimpleName());
                showFragment(detailFragment, false, false, DetailFragment.class.getSimpleName());
            } else {
                showFragment(leftFragment, true, false, LeftFragment.class.getSimpleName());
            }
        } else {
            leftFragment = getSupportFragmentManager().findFragmentByTag(LeftFragment.class.getSimpleName());
            detailFragment = getSupportFragmentManager().findFragmentByTag(DetailFragment.class.getSimpleName());
            if (isTablet) {
                showFragment(leftFragment, true, false, LeftFragment.class.getSimpleName());
                showFragment(detailFragment, false, false, DetailFragment.class.getSimpleName());
            } else {
                showFragment(leftFragment, true, false, LeftFragment.class.getSimpleName());
            }
        }
    }




    private void init() {
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        secondaryToolbar = (Toolbar) findViewById(R.id.secondaryToolbar);
        setSupportActionBar(myToolbar);
    }

    public Toolbar getSecondaryToolbar() {
        return secondaryToolbar;
    }

    //Primary meaning the screen we want
    public void showFragment(Fragment fragment, boolean primary, boolean addToBackStack, String backStackTag) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction lft = fm.beginTransaction();
        lft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        if (isTablet) {
            if (primary) {
                if (addToBackStack) {
                    lft.replace(R.id.leftFragmentContainer, fragment).addToBackStack(backStackTag).commit();
                } else {
                    lft.replace(R.id.leftFragmentContainer, fragment).commit();
                }
            } else {
                if (addToBackStack) {
                    lft.replace(R.id.rightFragmentContainer, fragment).addToBackStack(backStackTag).commit();
                } else {
                    lft.replace(R.id.rightFragmentContainer, fragment).commit();
                }
            }
        } else {
            if (addToBackStack) {
                lft.replace(R.id.fragmentContainer, fragment).addToBackStack(backStackTag).commit();
            } else {
                lft.replace(R.id.fragmentContainer, fragment).commit();
            }
        }
    }

    @Override
    public void onListItemSelected(String StringAtPosition) {
        if (isTablet) {
            if (StringAtPosition.contains("8")) {
                showFragment(DetailFragment.newInstance(StringAtPosition), true, true, DetailFragment.class.getSimpleName());
            } else {
                showFragment(DetailFragment.newInstance(StringAtPosition), false, true, DetailFragment.class.getSimpleName());
            }
        } else {
            showFragment(DetailFragment.newInstance(StringAtPosition), true, true, DetailFragment.class.getSimpleName());
        }
    }
}
