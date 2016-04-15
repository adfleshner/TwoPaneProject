package edu.highbay.flesh.twopaneproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

import edu.highbay.flesh.twopaneproject.R;

/**
 * Created by Aaron on 4/14/2016.
 */
public class NavigationFragment extends Fragment {

    private OnListItemSelectedListener listItemSelectedCallback;

    private RecyclerView list;

    // Host Activity must implement this interface
    public interface OnListItemSelectedListener {
        void onListItemSelected(String StringAtPosition);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        try {
            listItemSelectedCallback = (OnListItemSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnListItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        setUpRecyclerView(view);
    }

    private void setUpRecyclerView(View view) {
        list = (RecyclerView) view.findViewById(R.id.navigationList);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        final ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("Item " + i);
        }
        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_expandable_list_item_1, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
                ((ViewHolder) holder).title.setText(data.get(position));

                ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listItemSelectedCallback.onListItemSelected(data.get(position));
                    }
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder {
                private TextView title;

                public ViewHolder(View itemView) {
                    super(itemView);
                    title = (TextView) itemView.findViewById(android.R.id.text1);
                }
            }

        };
        list.setAdapter(adapter);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_navi,menu);
    }
}
