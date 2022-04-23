package Major_Project.Main;

import jm.music.data.Note;
import jm.util.Play;
import org.firmata4j.Pin;
import org.firmata4j.ssd1306.MonochromeCanvas;
import org.firmata4j.ssd1306.SSD1306;

import jm.JMC;

import java.util.TimerTask;

public class Listen extends TimerTask implements JMC {
    private static final int[] amongus = {C2, C5, EF5, F5, GF5, F5, EF5, C5, BF4, EF5, C5};
    private static final int[] ussr = {G4, C5, G4, A5, B5, E4, E4, A4, G4, F4, G4, C4, C4, D4, D4, E4, F4, F4, G5, G4, A5, B5, C6, D6};
    private static final int[] riverflows = {A4, DF5, A5, AF5, A5, AF5, A5, E4, A5, D4, DF4, D4, E4, DF4, B4};
    private static final int[] rickroll = {BF4, C5, DF5, BF4, F5, F5, EF5, BF4, C5, DF4, C5, EF5, EF5, DF5, C5, BF4, BF4, C5, DF5, BF4, DF5, DF5, EF5, C5, BF4, AF4, F4, EF5, DF5};
    private static final int[] mariotheme = {E5, E5, E5, C5, E5, G5, G4, C5, G4, E4, A4, B4, BF4, A4, G4, E5, G5, A5, F5, G5, E5, C5, D5, B4};
    private static final int[] furelise = {A4, AF4, A4, AF4, A4, E4, G4, F4, D4, F3, A3, D4, E4, A3, DF4, E4, F4};

    private static SSD1306 oledObject;
    private static Pin potObject;
    private static Pin buttonObject;
    private static Pin stopObject;

    private static final int base = 170;
    private static int choice;
    private static int note;

    public Listen(SSD1306 oledObject, Pin potObject, Pin buttonObject, Pin stopObject) {
        this.oledObject = oledObject;
        this.potObject = potObject;
        this.buttonObject = buttonObject;
        this.stopObject = stopObject;
    }

    @Override
    public void run() {
        oledObject.clear();
        try {
            int noteCheck = (int)potObject.getValue();

            if(noteCheck <= base) {
                oledObject.clear();
                oledObject.getCanvas().setTextsize(1);
                oledObject.getCanvas().drawString(0, 0, "Among Us\nUSSR\nRiver Flows\nRick Roll\nMario Theme\nFur Elise");
                oledObject.getCanvas().drawTriangle(80, 3, 85, 1, 85, 5, MonochromeCanvas.Color.BRIGHT);
                oledObject.display();
                Thread.sleep(2000);
                for(int i = 0; i<amongus.length; i++) {
                    note = amongus[i];
                    Play.midi(new Note(note, 0.01));
                }

            } else if(noteCheck <= 2*base) {
                oledObject.clear();
                oledObject.getCanvas().setTextsize(1);
                oledObject.getCanvas().drawString(0, 0, "Among Us\nUSSR\nRiver Flows\nRick Roll\nMario Theme\nFur Elise");
                oledObject.getCanvas().drawTriangle(80, 10, 85, 8, 85, 12, MonochromeCanvas.Color.BRIGHT);
                oledObject.display();
                choice = 1;
                Thread.sleep(2000);

                for(int i = 0; i<ussr.length; i++) {
                    note = ussr[i];
                    Play.midi(new Note(note, 0.01));
                }

            } else if(noteCheck <= 3*base) {
                oledObject.clear();
                oledObject.getCanvas().setTextsize(1);
                oledObject.getCanvas().drawString(0, 0, "Among Us\nUSSR\nRiver Flows\nRick Roll\nMario Theme\nFur Elise");
                oledObject.getCanvas().drawTriangle(80, 17, 85, 15, 85, 19, MonochromeCanvas.Color.BRIGHT);
                oledObject.display();
                choice = 2;
                Thread.sleep(2000);

                for(int i = 0; i<riverflows.length; i++) {
                    note = riverflows[i];
                    Play.midi(new Note(note, 0.01));
                }

            } else if(noteCheck <= 4*base) {
                oledObject.clear();
                oledObject.getCanvas().setTextsize(1);
                oledObject.getCanvas().drawString(0, 0, "Among Us\nUSSR\nRiver Flows\nRick Roll\nMario Theme\nFur Elise");
                oledObject.getCanvas().drawTriangle(80, 25, 85, 23, 85, 27, MonochromeCanvas.Color.BRIGHT);
                oledObject.display();
                choice = 3;
                Thread.sleep(2000);

                for(int i = 0; i<rickroll.length; i++) {
                    note = rickroll[i];
                    Play.midi(new Note(note, 0.01));
                }

            } else if(noteCheck <= 5*base) {
                oledObject.clear();
                oledObject.getCanvas().setTextsize(1);
                oledObject.getCanvas().drawString(0, 0, "Among Us\nUSSR\nRiver Flows\nRick Roll\nMario Theme\nFur Elise");
                oledObject.getCanvas().drawTriangle(80, 35, 85, 33, 85, 37, MonochromeCanvas.Color.BRIGHT);
                oledObject.display();
                choice = 4;
                Thread.sleep(2000);

                for(int i = 0; i<mariotheme.length; i++) {
                    note = mariotheme[i];
                    Play.midi(new Note(note, 0.01));
                }

            } else if(noteCheck <= 6*base) {
                oledObject.clear();
                oledObject.getCanvas().setTextsize(1);
                oledObject.getCanvas().drawString(0, 0, "Among Us\nUSSR\nRiver Flows\nRick Roll\nMario Theme\nFur Elise");
                oledObject.getCanvas().drawTriangle(80, 43, 85, 41, 85, 45, MonochromeCanvas.Color.BRIGHT);
                oledObject.display();
                choice = 5;
                Thread.sleep(2000);

                for(int i = 0; i<furelise.length; i++) {
                    note = furelise[i];
                    Play.midi(new Note(note, 0.01));
                }
            }

            if(potObject.getValue() == 1 && stopObject.getValue() >= 500) {
                System.out.println("Stopped");
                cancel();
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
