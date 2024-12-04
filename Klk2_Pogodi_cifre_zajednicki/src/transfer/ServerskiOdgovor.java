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
public class ServerskiOdgovor implements Serializable {
    private Object odgovor;
    private Exception exception;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, Exception exception) {
        this.odgovor = odgovor;
        this.exception = exception;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
    
}
