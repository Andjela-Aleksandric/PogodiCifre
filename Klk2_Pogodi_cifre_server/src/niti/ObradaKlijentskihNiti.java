/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SkriveniBroj;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Anđela
 */
public class ObradaKlijentskihNiti extends Thread {

    Socket s;
    int brojNiti;
    Controller controller = Controller.getController();

    public ObradaKlijentskihNiti() {
    }

    public ObradaKlijentskihNiti(Socket s, int brojNiti) {
        this.s = s;
        this.brojNiti = brojNiti;
    }

    @Override
    public void run() {
        try{
        while (s != null && !s.isClosed()) {
            try{
                KlijentskiZahtev kz = primiZahtev();
                if(kz == null){
                    System.out.println("Klijent je prekinuo vezu");
                    return;
                }
                ServerskiOdgovor so = new ServerskiOdgovor();
                switch (kz.getOperacija()) {
                    case Operacije.POGADJANJE_CIFRE:
                        SkriveniBroj sb = (SkriveniBroj) kz.getZahtev();
                        SkriveniBroj odgovor = controller.proveriBroj(sb);
                        so.setOdgovor(odgovor);
                        break;
                    case Operacije.NOVA_IGRA:
                        controller.osveziFormu();
                        break;
                    default:
                        System.out.println("GRESKA");
                        ;
                }
                posaljiOdgovor(so);
            }catch(Exception e){
                System.out.println("Greska: " + e.getMessage());
            }
        }
        
    }finally{
        prekini();
    }
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public KlijentskiZahtev primiZahtev() {
        if (s == null) {
            System.out.println("Soket je zatvoren");
            return null;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (SocketException ex) {
            System.out.println("Klijent ili server su prekinuli komunikaciju");
            prekini();
        } catch (EOFException ex) {
            System.out.println("Soket je zatvoren");
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihNiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihNiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void prekini() {
        if (s != null && !s.isClosed()) {
            try {
                s.close();
            } catch (IOException ex) {
                System.out.println("Greska pri zatvaranju soketa " + ex.getMessage());
            }
        }
    }

    public void posaljiOdgovor(ServerskiOdgovor so) {
        if (s == null) {
            System.out.println("Soket je zatvoren");
            return;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihNiti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
