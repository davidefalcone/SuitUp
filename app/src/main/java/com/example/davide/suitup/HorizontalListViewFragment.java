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

    //riferimenti alle view
    private ArrayList<Colore> listitems = new ArrayList<>();
    private RecyclerView recyclerView;

    //stringhe necessarie per il passaggio di parametri dalle activity
    public final String EXTRA_CAPO = "capo";
    private final String EXTRA_COLORI = "colori";


    public HorizontalListViewFragment () {
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
        if (recyclerView != null ) {

            if( getArguments() == null ){
                //se get arguments è null, significa che sto creando un capo
                //recyclerView.setAdapter(new EditColoriAdapter());
            }else
                //imposto l'adapter della recyclerview
                recyclerView.setAdapter((CapoColoriAdapter)getArguments().getSerializable(EXTRA_COLORI));

        }
        recyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
