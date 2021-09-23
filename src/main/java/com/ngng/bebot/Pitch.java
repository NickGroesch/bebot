package com.ngng.bebot;

import java.util.Arrays;
import java.util.Hashtable;

public class Pitch implements Transposable<Pitch> {

    private int midiNote;
    private int octave;
    private String scientific;
    private int base7;
    private int base12;


    // static resources
    public static Hashtable<Integer, String> midiPitchMap;
    static {
        midiPitchMap = new Hashtable<Integer, String>();
        midiPitchMap.put(0, "C");
        midiPitchMap.put(1, "C#");
        midiPitchMap.put(2, "D");
        midiPitchMap.put(3, "Eb");
        midiPitchMap.put(4, "E");
        midiPitchMap.put(5, "F");
        midiPitchMap.put(6, "F#");
        midiPitchMap.put(7, "G");
        midiPitchMap.put(8, "Ab");
        midiPitchMap.put(9, "A");
        midiPitchMap.put(10, "Bb");
        midiPitchMap.put(11, "B");
    }

    public static Hashtable<String, Integer> base7map;
    static {
        base7map = new Hashtable<String, Integer>(7);
        base7map.put("C", 0);
        base7map.put("D", 1);
        base7map.put("E", 2);
        base7map.put("F", 3);
        base7map.put("G", 4);
        base7map.put("A", 5);
        base7map.put("B", 6);
    }

    // public static Hashtable<String, String[]> midiPitchClasses;
    // static {
    // String[] keyC = { "C", "Cs", "D", "Ds", "E", "F", "Fs", "G", "Gs", "A", "Bb",
    // "B" };
    // midiPitchClasses.put("C", keyC);
    // String[] keyF = { "C", "Cs", "D", "Eb", "E", "F", "Fs", "G", "Gs", "A", "Bb",
    // "B" };
    // midiPitchClasses.put("C", keyF);
    // String[] keyG = { "C", "Cs", "D", "Ds", "E", "F", "Fs", "G", "Gs", "A", "As",
    // "B" };
    // midiPitchClasses.put("C", keyG);
    // String[] keyD = { "C", "Cs", "D", "Eb", "E", "Es", "Fs", "G", "Gs", "A",
    // "As", "B" };
    // midiPitchClasses.put("C", keyD);
    // }

    static String midiToScientific(int midi) {
        Integer octave = midi / 12;
        int midiMod = midi % 12;
        String letter = Pitch.midiPitchMap.get(midiMod);
        // System.out.println(letter.concat(octave.toString()));
        //return letter(octave.toString());
        return String.format("%s.%o", letter, octave);
    }

//    static int scientificToMidi(String scientific){
//
//    }

    // const pitchBase = pitch => {
    // let x = pitch.split(".");
    // let letter = x[0].split("")[0];
    // let octave = x[1];
    // let pitchMap = { C: 0, D: 1, E: 2, F: 3, G: 4, A: 5, B: 6 };
    // let base = pitchMap[letter] + 7 * octave;
    // return base;
    // };

    static Integer base7ify(String scientificParam) {
        System.out.println("nothing " + scientificParam);
        String[] deets = scientificParam.split("\\.");
        System.out.println("hey" + Arrays.toString(deets));
        String pitchPart = deets[0];
        String letter = pitchPart.substring(0,1);
        String accidentals = pitchPart.substring(1);
        //doesn't handle accidentals yet (or ever?)
        int octave = Integer.valueOf(deets[1]);
        int base7 = Pitch.base7map.get(letter);
        return base7 + (7 * octave);
    }
    // constructors
    public Pitch(int midi) {
        this.midiNote = midi;
        this.scientific = Pitch.midiToScientific(midi);
        this.base7 = Pitch.base7ify(this.scientific);
    }
//    // TODO:Impliment Pitch(Scientific)
//    public Pitch(String scientific) {
//        //this.midiNote = Pitch.scientificToMidi(scientific);
//        this.scientific = scientific;
//        this.base7 = Pitch.base7ify(this.scientific);
//    }

    // getters
    public int getMidi(){
        return this.midiNote;
    }
    public String getScientific(){
        return this.scientific;
    }
    public int getBase7(){
        return this.base7;
    }
    //interface methods
    public Pitch transpose(int midiValue){
        return new Pitch(this.midiNote + midiValue);
    }

}