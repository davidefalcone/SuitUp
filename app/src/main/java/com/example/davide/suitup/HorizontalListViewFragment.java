package com.example.davide.suitup;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.Colore;

import java.util.ArrayList;


public class HorizontalListViewFragment extends Fragment {

    //intero che tiene conto dell'activity chiamante
    private int activity;

    //riferimenti alle view
    private ArrayList<Colore> listitems = new ArrayList<>();
    private RecyclerView recyclerView;

    //stringhe necessarie per il passaggio di parametri dalle activity
    public final String EXTRA_COLORI = "colori";
    private final String ACTIVITY_CHIAMANTE = "activity";
    private final int ACTIVITY_CAPO = 0;
    private final int ACTIVITY_EDIT_CAPO = 1;

    public HorizontalListViewFragment () {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listitems.clear();
        listitems = getArguments().getParcelableArrayList(EXTRA_COLORI);
        activity = getArguments().getInt(ACTIVITY_CHIAMANTE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        recyclerView = view.findViewById(R.id.listaColori);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (listitems.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(ColoriAdapter.getInstance(listitems, activity));
        }
        recyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
