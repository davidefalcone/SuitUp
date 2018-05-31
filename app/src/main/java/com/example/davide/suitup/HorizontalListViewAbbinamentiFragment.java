package com.example.davide.suitup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.DataSource;


public class HorizontalListViewAbbinamentiFragment extends Fragment {

    //riferimenti alle view
    private RecyclerView recyclerView;
    private DataSource dataSource;

    //stringhe necessarie per il passaggio di parametri dalle activity
    private final String EXTRA_CAPO = "capo";
    private final String EXTRA_TIPO = "tipo";



    public HorizontalListViewAbbinamentiFragment () {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        recyclerView = view.findViewById(R.id.listaColori);
        recyclerView.setHasFixedSize(true);
        dataSource = DataSource.getInstance();
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (recyclerView != null && getArguments() != null) {
            Capo capo = (Capo) getArguments().getSerializable(EXTRA_CAPO);
            Capo.Tipo tipo = (Capo.Tipo) Capo.Tipo.valueOf(getArguments().getString(EXTRA_TIPO));
            //dataSource.cercaCapiAbbinabili(recyclerView, tipo, capo.getColori(), getContext());
        }

        recyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
