/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ng.adplus.bl;

/**
 *
 * @author johnson3yo
 */
public  class AdplusException extends Exception {

    private String message;
    
    public AdplusException(String message) {
    this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
}
