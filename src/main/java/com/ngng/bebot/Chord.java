package com.ngng.bebot;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chord {
    private int[] midiPitches;

    public Chord(final int midiRoot, SeventhChordsEnum theEnum) {
        IntStream transposeStream = Arrays.stream(theEnum.pitches);
        IntStream rootedStream = transposeStream.map(trans -> trans + midiRoot);
        this.midiPitches = rootedStream.toArray();
    }

    public int[] getPitches(){
        return this.midiPitches;
    }
}
