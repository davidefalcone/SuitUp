package com.example.davide.suitup;


import android.os.Bundle;
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

    ArrayList<Colore> listitems = new ArrayList<>();
    RecyclerView recyclerView;
    String colori [] = { "Rosso", "Giallo", "Verde", "Blu", "Nero", "Beige", "Grigio", "Bordeaux", "Marrone" };
    int images [] = { R.color.rosso, R.color.giallo, R.color.verde, R.color.blu, R.color.black, R.color.beige, R.color.grigio, R.color.bordeaux, R.color.marrone };

    public HorizontalListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listitems.clear();
        for (int i = 0; i<colori.length; i++){
            Colore item = new Colore();
            item.setNomeColore(colori[i]);
            item.setImageResourceId(images[i]);
            listitems.add(item);
        } getActivity().setTitle("Colori");
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
            recyclerView.setAdapter(new ColoriAdapter(listitems));
        }
        recyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
