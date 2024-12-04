/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anđela
 */
public class PokreniServer extends Thread {
    ServerSocket ss;
    boolean kraj = false;
    static int brojNiti = 0;

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("Server osluskuje na portu 9000");
            while (!kraj) {
                try {
                    Socket s = ss.accept();
                    brojNiti++;
                    System.out.println("Klijent " + brojNiti + " uspesno povezan!");
                    ObradaKlijentskihNiti nit = new ObradaKlijentskihNiti(s, brojNiti);
                    nit.start();
                } catch (SocketException e) {
                    System.out.println("Klijent se odvezao ili je server prekinuo konekciju " + e.getMessage());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void prekini(){
        kraj = true;
        if(ss!=null && !ss.isClosed()){
            try {
                ss.close();
            } catch (IOException ex) {
                Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
