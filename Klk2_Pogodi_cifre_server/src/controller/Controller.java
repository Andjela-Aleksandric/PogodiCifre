/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forms.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import model.Administrator;
import model.SkriveniBroj;

/**
 *
 * @author Anđela
 */
public class Controller {
    private static Controller controller;
    private List<Administrator> admini = new ArrayList<>();
    private Administrator ulogovani;
    private List<SkriveniBroj> listaSb = new ArrayList<>();
    ServerskaForma sf;

    private Controller() {
        admini = List.of(new Administrator("aca", "acic", "aca", "aca"), 
                new Administrator("djika", "andjic", "djika", "djika"));
    }

    public static Controller getController() {
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public List<Administrator> getAdmini() {
        return admini;
    }

    public void setAdmini(List<Administrator> admini) {
        this.admini = admini;
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }

    public List<SkriveniBroj> getListaSb() {
        return listaSb;
    }

    public void setListaSb(List<SkriveniBroj> listaSb) {
        this.listaSb = listaSb;
    }

    public void dodajBroj(SkriveniBroj sb){
        listaSb.add(sb);
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public ServerskaForma getSf() {
        return sf;
    }
    
    public boolean postojiBroj(SkriveniBroj sb){
        for (SkriveniBroj skriveniBroj : listaSb) {
            if(sb.getVrednost() == skriveniBroj.getVrednost()){
                return true;
            }
        }
        return false;
    }

    public SkriveniBroj proveriBroj(SkriveniBroj vrednost) {
        for (SkriveniBroj skriveniBroj : listaSb) {
            if(vrednost.getKolona() == skriveniBroj.getKolona() && vrednost.getRed() == skriveniBroj.getRed()){
                return skriveniBroj;
            }
        }
        return null;
    }

    public void osveziFormu() {
        sf.refreshTable();
    }
}
