package Major_Project.Main;

import jm.music.data.Note;
import jm.util.Play;
import org.firmata4j.Pin;
import org.firmata4j.ssd1306.MonochromeCanvas;
import org.firmata4j.ssd1306.SSD1306;

import jm.JMC;

import java.util.TimerTask;
import java.util.Random;

public class MusicalEar extends TimerTask implements JMC{
    private static Pin octavePin;
    private static Pin notePin;
    private static SSD1306 oledObject;
    private static int note;
    private static final int base = 145;
    private static int count;
    private static String label;
    private static Pin stopPin;
    private static boolean getRand = true;
    private static int answerNote;
    private static int randNoteIndex;
    private final int[] notesArray = {C0, C1, C2, C3, C4, C5, C6, C7, C8, D0, D1, D2, D3, D4, D5, D6, D7, D8, E0, E1, E2, E3, E4, E5, E6, E7, E8, F0, F1, F2, F3, F4, F5, F6, F7, F8, G0, G1, G2, G3, G4, G5, G6, G7, G8};
    private final String[] notesArrayString = {"C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "G0", "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8"};

    public MusicalEar(SSD1306 oledObject, Pin octavePin, Pin notePin, Pin stopPin) {
        this.oledObject = oledObject;
        this.octavePin = octavePin;
        this.notePin = notePin;
        this.stopPin = stopPin;
    }

    @Override
    public void run() {
        oledObject.clear();
        Random randObject = new Random();
        try {
            int noteCheck = (int)notePin.getValue();

            if(octavePin.getValue() == 1 && notePin.getValue() == 0 && count != 0) {
                count = count - 1;
                label = "Lower Octave";
            } else if(count > 8){
                count = 0;
            } else if(octavePin.getValue() == 1 && count != 9) {
                count = count + 1;
            }

            if(noteCheck <= base) {
                int[] octaves = {C0, C1, C2, C3, C4, C5, C6, C7, C8};
                String[] octavesString = {"C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8"};
                note = octaves[count];
                label = octavesString[count];

            } else if(noteCheck <= 2*base) {
                int[] octaves = {D0, D1, D2, D3, D4, D5, D6, D7, D8};
                String[] octavesString = {"D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8"};
                note = octaves[count];
                label = octavesString[count];

            } else if(noteCheck <= 3*base) {
                int[] octaves = {E0, E1, E2, E3, E4, E5, E6, E7, E8};
                String[] octavesString = {"E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8"};
                note = octaves[count];
                label = octavesString[count];

            } else if(noteCheck <= 4*base) {
                int[] octaves = {F0, F1, F2, F3, F4, F5, F6, F7, F8};
                String[] octavesString = {"F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8"};
                note = octaves[count];
                label = octavesString[count];

            } else if(noteCheck <= 5*base) {
                int[] octaves = {G0, G1, G2, G3, G4, G5, G6, G7, G8};
                String[] octavesString = {"G0", "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8"};
                note = octaves[count];
                label = octavesString[count];

            } else if(noteCheck <= 6*base) {
                int[] octavesInt = {A0, A1, A2, A3, A4, A5, A6, A7, A8};
                String[] octavesString = {"A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8"};
                note = octavesInt[count];
                label = octavesString[count];

            } else if(noteCheck > 6*base) {
                int[] octaves = {B0, B1, B2, B3, B4, B5, B6, B7, B8};
                String[] octavesString = {"B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8"};
                note = octaves[count];
                label = octavesString[count];
            }

            oledObject.clear();
            oledObject.getCanvas().setTextsize(2);
            oledObject.getCanvas().drawString(0, 0, label);
            oledObject.getCanvas().drawHorizontalLine(0, 20, (int)notePin.getValue()/10, MonochromeCanvas.Color.BRIGHT);
            oledObject.display();

            if(getRand == true) {
                randNoteIndex = randObject.nextInt(notesArray.length);
                System.out.println(notesArrayString[randNoteIndex]);

                answerNote = notesArray[randNoteIndex];
                getRand = false;
            }

            Play.midi(new Note(answerNote, 0.33));

            if(octavePin.getValue() == 1 && note == answerNote) {
                System.out.println("Correct!");;
                oledObject.clear();
                oledObject.getCanvas().setTextsize(2);
                oledObject.getCanvas().drawString(0, 0, "Correct!");
                oledObject.display();
                cancel();
            }

            if(octavePin.getValue() == 1 && stopPin.getValue() >= 500) {
                System.out.println("Stopped");
                System.out.println(notesArrayString[randNoteIndex]);
                cancel();
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
