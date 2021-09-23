package com.ngng.bebot;

import org.junit.Test;

import org.junit.Assert;

public class PitchTest {
    double DELTA= 0.05;
    @Test
    public void takesTheMidiYouGive() throws Exception {
        Pitch middleC = new Pitch(60);
        Assert.assertEquals("it returns midi of 60 when you construct a new Note(60)", middleC.getMidi(),  60);
    }

    @Test
    public void translatesMidiIntoScientific() throws Exception {
        Pitch middleC = new Pitch(60);
        Assert.assertEquals("it returns scientific of 'C.5' when you construct a new Note(60)",middleC.getScientific(), "C.5");
    }

    @Test
    public void translatesMidiIntoScientific2() throws Exception {
        Pitch middleCs = new Pitch(61);
        Assert.assertEquals("it returns scientific of 'C#.5' when you construct a new Note(60)",middleCs.getScientific(), "C#.5");
    }

    @Test
    public void staticMidiPitchMapShouldWork() throws Exception {
        Assert.assertEquals("it should recognize that 9 goes with a pitchclass A", Pitch.midiPitchMap.get(9), "A");
    }

    @Test
    public void transposeReturnsAPitch() throws Exception {
        Assert.assertEquals("transposing a pitch should return a pitch", new Pitch(60).transpose(4) instanceof Pitch, true);
    }

    @Test
    public void transposeReturnsProperPitch() {
        Pitch middleC = new Pitch(60);
        Assert.assertEquals("it should transpose by a positive number", middleC.transpose(4).getMidi(), 64);
    }
    @Test
    public void base7ifyBase7ifies(){
        Pitch middleC = new Pitch(60);
        Assert.assertEquals("it should have the proper base7 ", middleC.getBase7(), 35);
    }
}
