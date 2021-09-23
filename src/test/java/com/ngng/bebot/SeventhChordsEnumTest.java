package com.ngng.bebot;

import org.junit.Assert;
import org.junit.Test;

public class SeventhChordsEnumTest {
    @Test
    public void getsPitchesFromEnum() throws Exception {
        int[] majorNotes = SeventhChordsEnum.MAJOR.pitches;
        int[] theRightNotes = new int[]{0,4,7,11};
        Assert.assertArrayEquals("gives the right notes", majorNotes , theRightNotes);
    }
}
