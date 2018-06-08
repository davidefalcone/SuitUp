package com.example.davide.suitup.DataModel;

import com.example.davide.suitup.AbbinamentoActivity;
import com.example.davide.suitup.R;
import java.io.Serializable;
import java.util.ArrayList;

public class Colore implements Serializable{

    //attributi

    private String nomeColore;
    private int imageResourceId;
    private int idColore;
    private static boolean abbinamenti[][] = null;

    //costruttore

    public Colore(String nomeColore, int imageResourceId, int idColore) {
        this.nomeColore = nomeColore;
        this.imageResourceId = imageResourceId;
        this.idColore = idColore;
    }
    public Colore(){}

    //getters e setters
    public String getNomeColore() {
        return nomeColore;
    }

    public void setNomeColore(String nomeColore) {
        this.nomeColore = nomeColore;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getIdColore() {
        return idColore;
    }

    public void setIdColore(int idColore) {
        this.idColore = idColore;
    }

    //metodo che restituisce una lista di tutti i colori che non sono contenuti nel Capo
    public static ArrayList<Colore> ColoriRimanenti (Capo capo) {
        ArrayList<Colore> coloriRimanenti = Colore.Tuttiicolori();
        ArrayList<Colore> colori = capo.getColori();
        for (int i = 0; i<colori.size(); i++){
            for (int j = 0; j<coloriRimanenti.size(); j++) {
                if (colori.get(i).getNomeColore().matches(coloriRimanenti.get(j).getNomeColore()))
                    coloriRimanenti.remove(j);

            }
        }return coloriRimanenti;
    }
    //restituisce na lista di tutti i colori
    public static ArrayList<Colore> Tuttiicolori (){
        ArrayList<Colore> lista = new ArrayList<Colore>();
        lista.add(new Colore("Rosso", R.color.rosso, 0));
        lista.add(new Colore("Giallo", R.color.giallo,1));
        lista.add(new Colore("Verde", R.color.verde,2));
        lista.add(new Colore("Blu elettrico", R.color.blu_elettrico,3));
        lista.add(new Colore("Nero", R.color.black,4));
        lista.add(new Colore("Grigio",R.color.grigio,5));
        lista.add(new Colore("Bianco", R.color.bianco,6));
        lista.add(new Colore("Bordeaux", R.color.bordeaux,7));
        lista.add(new Colore("Beige", R.color.beige,8));
        lista.add(new Colore("Marrone", R.color.marrone,9));
        lista.add(new Colore("Jeans", R.drawable.jeans,10));
        lista.add(new Colore("Azzurro", R.color.azzurro,11));
        lista.add(new Colore("Viola", R.color.viola,12));
        lista.add(new Colore("Rosa", R.color.rosa,13));
        lista.add(new Colore("Arancione", R.color.arancione,14));
        lista.add(new Colore("Blu scuro", R.color.blu_scuro,15));



        return lista;
    }

    public static boolean coloriAbbinabili (ArrayList<Colore> coloriCapo, ArrayList<Colore> colori){
        boolean abbinabili = true;
        for ( int i=0; i<colori.size(); i++){
            for ( int j=0; j<coloriCapo.size(); j++){
                if(!getAbbinamenti()[colori.get(i).getIdColore()][coloriCapo.get(j).getIdColore()])
                    abbinabili = false;
            }
        }return abbinabili;
    }

    public static boolean[][] getAbbinamenti() {
        if (abbinamenti == null){
            abbinamenti = new boolean[16][16];
            abbinamenti[0][0] = true;
            abbinamenti[0][4] = true;
            abbinamenti[0][5] = true;
            abbinamenti[0][6] = true;
            abbinamenti[0][10] = true;
            abbinamenti[0][11] = true;
            abbinamenti[0][14] = true;
            abbinamenti[1][4] = true;
            abbinamenti[1][1] = true;
            abbinamenti[1][5] = true;
            abbinamenti[1][10] = true;
            abbinamenti[1][6] = true;
            abbinamenti[2][4] = true;
            abbinamenti[2][5] = true;
            abbinamenti[2][2] = true;
            abbinamenti[2][10] = true;
            abbinamenti[2][6] = true;
            abbinamenti[3][4] = true;
            abbinamenti[3][6] = true;
            abbinamenti[3][3] = true;
            abbinamenti[3][10] = true;
            abbinamenti[4][0] = true;
            abbinamenti[4][1] = true;
            abbinamenti[4][2] = true;
            abbinamenti[4][4] = true;
            abbinamenti[4][5] = true;
            abbinamenti[4][6] = true;
            abbinamenti[4][7] = true;
            abbinamenti[4][8] = true;
            abbinamenti[4][10] = true;
            abbinamenti[4][11] = true;
            abbinamenti[4][12] = true;
            abbinamenti[4][13] = true;
            abbinamenti[4][14] = true;
            abbinamenti[5][0] = true;
            abbinamenti[5][1] = true;
            abbinamenti[5][2] = true;
            abbinamenti[5][3] = true;
            abbinamenti[5][5] = true;
            abbinamenti[5][4] = true;
            abbinamenti[5][6] = true;
            abbinamenti[5][7] = true;
            abbinamenti[5][8] = true;
            abbinamenti[5][9] = true;
            abbinamenti[5][10] = true;
            abbinamenti[5][15] = true;
            abbinamenti[6][0] = true;
            abbinamenti[6][1] = true;
            abbinamenti[6][2] = true;
            abbinamenti[6][3] = true;
            abbinamenti[6][4] = true;
            abbinamenti[6][5] = true;
            abbinamenti[6][6] = true;
            abbinamenti[6][7] = true;
            abbinamenti[6][8] = true;
            abbinamenti[6][9] = true;
            abbinamenti[6][10] = true;
            abbinamenti[6][11] = true;
            abbinamenti[6][12] = true;
            abbinamenti[6][13] = true;
            abbinamenti[6][14] = true;
            abbinamenti[6][15] = true;
            abbinamenti[7][4] = true;
            abbinamenti[7][5] = true;
            abbinamenti[7][7] = true;
            abbinamenti[7][6] = true;
            abbinamenti[7][9] = true;
            abbinamenti[7][10] = true;
            abbinamenti[8][5] = true;
            abbinamenti[8][6] = true;
            abbinamenti[8][7] = true;
            abbinamenti[8][11] = true;
            abbinamenti[8][10] = true;
            abbinamenti[8][15] = true;
            abbinamenti[8][8] = true;
            abbinamenti[9][4] = true;
            abbinamenti[9][6] = true;
            abbinamenti[9][10] = true;
            abbinamenti[9][15] = true;
            abbinamenti[9][9] = true;
            abbinamenti[10][0] = true;
            abbinamenti[10][1] = true;
            abbinamenti[10][2] = true;
            abbinamenti[10][3] = true;
            abbinamenti[10][4] = true;
            abbinamenti[10][5] = true;
            abbinamenti[10][6] = true;
            abbinamenti[10][7] = true;
            abbinamenti[10][8] = true;
            abbinamenti[10][9] = true;
            abbinamenti[10][10] = true;
            abbinamenti[10][11] = true;
            abbinamenti[10][12] = true;
            abbinamenti[10][13] = true;
            abbinamenti[10][14] = true;
            abbinamenti[10][15] = true;
            abbinamenti[11][10] = true;
            abbinamenti[11][4] = true;
            abbinamenti[11][11] = true;
            abbinamenti[11][6] = true;
            abbinamenti[12][10] = true;
            abbinamenti[12][4] = true;
            abbinamenti[12][6] = true;
            abbinamenti[12][12] = true;
            abbinamenti[13][10] = true;
            abbinamenti[13][4] = true;
            abbinamenti[13][13] = true;
            abbinamenti[13][6] = true;
            abbinamenti[14][10] = true;
            abbinamenti[14][4] = true;
            abbinamenti[14][14] = true;
            abbinamenti[14][6] = true;
            abbinamenti[15][5] = true;
            abbinamenti[15][6] = true;
            abbinamenti[15][8] = true;
            abbinamenti[15][15] = true;
            abbinamenti[15][10] = true;
            abbinamenti[15][9] = true;





        } return abbinamenti;

    }
}

