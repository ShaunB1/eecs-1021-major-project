import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.ssd1306.SSD1306;
import org.firmata4j.I2CDevice;

import java.util.Timer;
import java.util.Scanner;

import static Major_Project.Main.Pins.*;


public class Main {
    public static void main(String[] args) {
        String myPort = "COM6";
        IODevice myBoard = new FirmataDevice(myPort);
        Scanner scannerObject = new Scanner(System.in);

        try {
            myBoard.start();
            myBoard.ensureInitializationIsDone();

            I2CDevice i2cObject = myBoard.getI2CDevice(I2C0);
            SSD1306 oledObject = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);
            oledObject.init();

            Pin buttonObject = myBoard.getPin(D6);
            buttonObject.setMode(Pin.Mode.INPUT);

            Pin potObject = myBoard.getPin(A0);
            potObject.setMode(Pin.Mode.ANALOG);

            Pin soundObject = myBoard.getPin(A2);
            soundObject.setMode(Pin.Mode.ANALOG);

            Timer timerObject = new Timer();

            while(true) {
                oledObject.getCanvas().setTextsize(2);
                oledObject.getCanvas().drawString(0, 0, "Potentio\nPiano\nShaun \nBautista");
                oledObject.display();

                System.out.println("Returned");
                System.out.print("Select Mode: \n-Piano\n-Musical Ear\n-Listen\nMode: ");
                String userMode = scannerObject.nextLine();

                if(userMode.equals("Piano")) {
                    System.out.println("[Accessed Piano]");
                    var task = new Piano(oledObject, buttonObject, potObject, soundObject);
                    timerObject.schedule(task, 0, 1000);

                } else if(userMode.equals("Musical Ear")) {
                    System.out.println("[Accessed Musical Ear]");
                    var task = new MusicalEar(oledObject, buttonObject, potObject, soundObject);
                    timerObject.schedule(task, 0, 1000);

                } else if(userMode.equals("Listen")) {
                    System.out.println("Accessed Listen");
                    var task = new Listen(oledObject, potObject, buttonObject, soundObject);
                    timerObject.schedule(task, 0, 1000);

                } else {
                    System.out.println("Not valid");
                    continue;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
