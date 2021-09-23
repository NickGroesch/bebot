package com.ngng.bebot;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

public class Note extends Pitch{
    private int duration; //ticks // is this more appropriately another extension analagous to MidiMessage:MidiEvent?
    private int velocity; //0-127

    public int getDuration(){return this.duration;}
    public int getVelocity(){return this.velocity;}
    public MidiMessage getMessage() {
        ShortMessage myMsg= new ShortMessage();
        try{
            myMsg.setMessage(ShortMessage.NOTE_ON, 0, this.getMidi(), this.getVelocity());
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return myMsg;
    }

    public Note(int midi, int velocity, int duration){
        super(midi);
        this.velocity = velocity;
        this.duration = duration;
    }
    public Note(int midi, int velocity){
        super(midi);
        this.velocity = velocity;
        this.duration = 1;
    }
    public Note(int midi){
        super(midi);
        this.velocity = 60;
        this.duration = 1;
    }
}
