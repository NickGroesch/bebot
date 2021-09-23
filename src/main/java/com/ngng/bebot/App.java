package com.ngng.bebot;

import javax.sound.midi.*;
import javax.sound.sampled.*;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Vector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    private static Mixer myMixer;
    private static Clip myClip;
    private static Synthesizer mySynth;
    private static Sequencer mySequencer;

    public static void main( String[] args ){
        //App.getAudioSoundoff();
        App.getMidiSynthSoundoff();
        App.letsHaveASequenceThen();
    }

    private static void letsHaveASequenceThen() {
        try {
            Sequence mySequence = new Sequence(Sequence.PPQ, 1);
            Track track = mySequence.createTrack();
           // for(Note note : notes)
            track.add(new MidiEvent( new Note(60).getMessage(), 1));
            track.add(new MidiEvent( new Note(61).getMessage(), 12));
            track.add(new MidiEvent( new Note(64).getMessage(), 14));
            track.add(new MidiEvent( new Note(65).getMessage(), 15));
            track.add(new MidiEvent( new Note(67).getMessage(), 19));
            track.add(new MidiEvent( new Note(68).getMessage(), 20));
            track.add(new MidiEvent( new Note(71).getMessage(), 22));
            track.add(new MidiEvent( new Note(72).getMessage(), 24));
            track.add(new MidiEvent( new Note(61).getMessage(), 30));
            track.add(new MidiEvent( new Note(60).getMessage(), 35));
            mySequencer.setSequence(mySequence);
            mySequencer.setTempoInBPM(120);
            //mySequencer.
            Transmitter myTrans = mySequencer.getTransmitter();
            Receiver myRcvr = mySynth.getReceiver();
            myTrans.setReceiver(myRcvr);
            mySequencer.start();
            do{
                try{ Thread.sleep(1000);}
                catch(InterruptedException ie){ie.printStackTrace();}
            } while(mySequencer.isRunning());
            System.out.println( "Great job" );
            App.cleanUpMidi();

        } catch (InvalidMidiDataException | MidiUnavailableException e) {
            e.printStackTrace();
            App.fail();
        }
    }

    private static void fail() {
        System.out.println("And he was never heard from again...");
        App.cleanUpMidi();
    }


    private static void getAudioSoundoff(){
        System.out.println( "It's happening" );
        Mixer.Info myMixerInfo = null;
        // get a mixer
        for(int i = 0; i< mixers.length; i++) {
            System.out.println(mixers[i].getName());
            if(mixers[i].getName().equals("Default Audio Device") ){
                System.out.println("oofda");
                myMixerInfo = mixers[i];
            }
        }
        System.out.println(myMixerInfo);
        myMixer = AudioSystem.getMixer( myMixerInfo);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);


        Line.Info[] absTargetLinesInfo = myMixer.getTargetLineInfo();
        TargetDataLine myClipLine;
        System.out.println(Arrays.toString(absTargetLinesInfo)+"  "+ absTargetLinesInfo.length);
        Line.Info myTargetLine = absTargetLinesInfo[0];

        try {
            Line myReservedTargetLine = myMixer.getLine(myTargetLine);// throws LineUnavailableException;
            System.out.println(myReservedTargetLine.isOpen());
            if (! myReservedTargetLine.isOpen()){
                myReservedTargetLine.open();
            }
            System.out.println(myReservedTargetLine.isOpen());
            myClip = (Clip) myMixer.getLine(dataInfo);
        }catch (LineUnavailableException lue){
            lue.printStackTrace();
        }

        try{
            System.out.println(App.class);

            URL myClipURL = App.class.getResource("Siren_BigRoom_GB.aif");
            System.out.println(myClipURL);
            AudioInputStream clipStream = AudioSystem.getAudioInputStream(myClipURL);
            myClip.open(clipStream);

        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException errs){
            errs.printStackTrace();
        }

        myClip.start();

        do{
            try{ Thread.sleep(50);}
            catch(InterruptedException ie){ie.printStackTrace();}
        } while(myClip.isActive());

        System.out.println( "It's no longer happening" );

    }
    private static void getMidiSynthSoundoff()  {
        Vector synthInfos = new Vector();
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
            } catch (MidiUnavailableException err) {
               continue;
            }
            System.out.println( device);
            if (device instanceof Synthesizer) {
                mySynth = (Synthesizer) device;
                synthInfos.add(infos[i]);
            }else if(device instanceof Sequencer){
                mySequencer= (Sequencer) device;
            }
        }
        System.out.println(mySequencer);
        System.out.println(mySynth);
        try {
            mySequencer.open();
            mySynth.open();
        }catch (MidiUnavailableException mue){
            mue.printStackTrace();
            App.fail();
        }
        ShortMessage myMsg = new ShortMessage();
        try {
            myMsg.setMessage(ShortMessage.NOTE_ON, 0, 60, 60);//middle C event
            long timeStamp = -1;
            Receiver rcvr = mySynth.getReceiver();
            rcvr.send(myMsg, timeStamp);
            long muSecPosition = mySynth.getMicrosecondPosition();
            System.out.println(muSecPosition);
            Thread.sleep(3000);
            //cleanUpMidi();

        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            App.fail();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            App.fail();
        } catch (InterruptedException e) {
            e.printStackTrace();
            App.fail();
        }

    }
    private static void cleanUpMidi(){
        mySynth.close(); //don't forget to clean up and release resources for the greater good
        mySequencer.close();
    }
}
