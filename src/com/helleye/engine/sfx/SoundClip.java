package com.helleye.engine.sfx;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClip {
	private Clip clip = null;
	private FloatControl gainControl;
	
	public SoundClip(String path) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundClip.class.getResourceAsStream(path)));
			AudioFormat baseFormat = ais.getFormat();
			AudioInputStream decodedStream = AudioSystem.getAudioInputStream(new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false), ais);
			clip = AudioSystem.getClip();
			clip.open(decodedStream);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		if (clip == null) return;
		stop();
		clip.setFramePosition(0);
		while(!clip.isRunning())
			clip.start();
		
	}
	public void stop(){
		if(clip.isRunning())
			clip.stop();
	}
	
	public void close(){
		stop();
		clip.drain();
		clip.close();
	}
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		play();
	}
	public void setVolume(float volume){ //From -80 to +6
		gainControl.setValue(volume);
	}
	public boolean isRunning(){
		return clip.isRunning();
	}
}
