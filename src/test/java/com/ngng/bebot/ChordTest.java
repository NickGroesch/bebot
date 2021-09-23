package com.ngng.bebot;

import org.junit.Assert;
import org.junit.Test;

public class ChordTest {
    @Test
    public void makesACMajorChord() throws Exception {
        Chord cMajor7 = new Chord(60, SeventhChordsEnum.MAJOR);
        Assert.assertArrayEquals("gives the right notes", cMajor7.getPitches() , new int[]{60,64,67,71});
    }
}
