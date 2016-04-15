package edu.highbay.flesh.twopaneproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.highbay.flesh.twopaneproject.R;
import edu.highbay.flesh.twopaneproject.activities.HostActivity;

/**
 * Created by Aaron on 4/14/2016.
 */
public class DetailFragment extends Fragment {

    private static final String DATA = "StringAtPosition";
    private static final String TEMP = "Temp";
    TextView textView;
    String temp = "String";
    private boolean isTablet;

    public static DetailFragment newInstance(String stringAtPosition) {
        Bundle args = new Bundle();
        args.putString(DATA, stringAtPosition);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        isTablet = getResources().getBoolean(R.bool.isTablet);
        if(getArguments()!=null&&getArguments().containsKey(DATA)) {
            temp = getArguments().getString(DATA);
        }else{
            temp = "String";
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            if(savedInstanceState.containsKey(TEMP)){
                temp = savedInstanceState.getString(TEMP);
                textView.setText(temp);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        textView = (TextView) view.findViewById(R.id.tv);
        textView.setText(temp);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEMP,temp);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (isTablet) {
            ((HostActivity) getActivity()).getSecondaryToolbar().getMenu().clear();
        }
        if(temp.contains("9")){
            if (isTablet) {
                ((HostActivity) getActivity()).getSecondaryToolbar().inflateMenu(R.menu.menu_9);
                ((HostActivity) getActivity()).getSecondaryToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });
            } else {
                //MAYBE???
                inflater.inflate(R.menu.menu_9, menu);
            }
        }
    }
}
