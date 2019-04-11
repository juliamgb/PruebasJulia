
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;


public class Lampara extends javax.swing.JFrame {
	

	public PanamaHitek_Arduino ino;
	public boolean estadoEncendido;
	 private SerialPortEventListener listener = new SerialPortEventListener() {
         @Override
         public void serialEvent(SerialPortEvent spe) {
             try {
             	
                 if (ino.isMessageAvailable()) {
                 	// si recibimos algo por consola se va a mostrar un mensaje
                     //Se imprime el mensaje recibido en la consola
                     System.out.println("Arduino dice: "+ino.printMessage());
                 }
             } catch (SerialPortException | ArduinoException ex) {
                 Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
             }

         }
     };
   
    public Lampara() {
        //Se inicializa el puerto de serie
        ino = new PanamaHitek_Arduino();
        estadoEncendido = false;
       
        try {
           //Comunicación con el puerto de serie 
            ino.arduinoRXTX("COM3", 9600,listener);
        } catch (ArduinoException ex) {
            Logger.getLogger(Lampara.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  //Encendemos
	  public void encender()
	  {
		  estadoEncendido = true;
	  }
	  
	  //Apagamos
	  public void Apagar()
	  {
		  estadoEncendido = false;
	  }
	  
	 //Si la lampara está encendida
	  public boolean estadoEncendido()
	  {
	    return estadoEncendido;
	  }

}