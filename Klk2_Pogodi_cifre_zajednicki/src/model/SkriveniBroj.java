/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Anđela
 */
public class SkriveniBroj implements Serializable {
    private int vrednost, red, kolona;

    public SkriveniBroj() {
    }

    public SkriveniBroj(int vrednost, int red, int kolona) {
        this.vrednost = vrednost;
        this.red = red;
        this.kolona = kolona;
    }
    

    public int getVrednost() {
        return vrednost;
    }

    public void setVrednost(int vrednost) {
        this.vrednost = vrednost;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getKolona() {
        return kolona;
    }

    public void setKolona(int kolona) {
        this.kolona = kolona;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.vrednost;
        hash = 67 * hash + this.red;
        hash = 67 * hash + this.kolona;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SkriveniBroj other = (SkriveniBroj) obj;
        if (this.vrednost != other.vrednost) {
            return false;
        }
        if (this.red != other.red) {
            return false;
        }
        return this.kolona == other.kolona;
    }

    @Override
    public String toString() {
        return "SkriveniBroj{" + "vrednost=" + vrednost + ", red=" + red + ", kolona=" + kolona + '}';
    }

    
    
}
