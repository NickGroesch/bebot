package com.ngng.bebot;

public enum SeventhChordsEnum {
    MAJOR( new int[]{0,4,7,11}),
    MINOR (new int[]{0,4,7,10}),
    DOMINANT (new int[]{0,3,7,10}),
    HALFDIMINISHED (new int[]{0,3,6,10}),
    DIMISHED (new int[]{0,3,6,9}),
    AUGMENTED_DOMINANT (new int[]{0,4,8,10});

    public final int[] pitches;

    private SeventhChordsEnum( int[] pitches) {
        this.pitches = pitches;
    }
}
