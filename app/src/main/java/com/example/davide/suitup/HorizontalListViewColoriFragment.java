package com.example.davide.suitup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class HorizontalListViewColoriFragment extends Fragment {

    //riferimenti alle view
    private RecyclerView recyclerView;

    //stringhe necessarie per il passaggio di parametri dalle activity
    private final String EXTRA_COLORI = "colori";



    public HorizontalListViewColoriFragment () {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        recyclerView = view.findViewById(R.id.listaColori);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (recyclerView != null && getArguments() != null) {
            recyclerView.setAdapter((CapoColoriAdapter) getArguments().getSerializable(EXTRA_COLORI));
            }

        recyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
