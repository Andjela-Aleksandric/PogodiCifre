/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Anđela
 */
public class Komunikacija {

    private static Komunikacija kmk;
    private Socket s;

    private Komunikacija() {
        try {
            s = new Socket("localhost", 9000);
            System.out.println("Klijent uspesno povezan");
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Komunikacija getKmk() {
        if (kmk == null) {
            kmk = new Komunikacija();
        }
        return kmk;
    }

    public void posaljiZahtev(KlijentskiZahtev kz) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
            oos.flush();
        } catch (SocketException e) {
            System.out.println("Klijent se odvezao ili je server prekinuo konekciju " + e.getMessage());
            prekini();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ServerskiOdgovor primiOdgovor() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (ServerskiOdgovor) ois.readObject();
        } catch (SocketException e) {
            System.out.println("Klijent se odvezao ili je server prekinuo konekciju " + e.getMessage());
            prekini();
        } catch (EOFException ex) {
            System.out.println("Soket je zatvoren");
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
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

}
