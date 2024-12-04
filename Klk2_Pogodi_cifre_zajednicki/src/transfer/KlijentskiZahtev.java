/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Anđela
 */
public class KlijentskiZahtev implements Serializable {
    private Object zahtev;
    private int operacija;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(Object zahtev, int operacija) {
        this.zahtev = zahtev;
        this.operacija = operacija;
    }

    public Object getZahtev() {
        return zahtev;
    }

    public void setZahtev(Object zahtev) {
        this.zahtev = zahtev;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }
    
    
}
