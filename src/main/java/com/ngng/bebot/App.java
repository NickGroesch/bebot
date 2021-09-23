package com.ngng.bebot;

import javax.sound.sampled.*;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    public static Mixer myMixer;
    public static Clip myClip;
    public static void main( String[] args )
    {
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

        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException uafe){
            uafe.printStackTrace();
        }

        myClip.start();

        do{
            try{ Thread.sleep(50);}
            catch(InterruptedException ie){ie.printStackTrace();}
        } while(myClip.isActive());

        System.out.println( "It's no longer happening" );

    }
}
