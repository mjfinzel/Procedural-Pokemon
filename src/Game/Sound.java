package Game;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Sound implements MetaEventListener{
	public Sound(){
		run();
	}

	private MidiPlayer player;

	  public void run() {

	    player = new MidiPlayer();

	    // load a sequence
	    String path = Sound.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	    path = path.substring(0, path.length()-4);
	    System.out.println("path is: "+path);
	    
	    Sequence sequence = player.getSequence(path+"/src/Sounds/route.mid");

	    // play the sequence
	    player.play(sequence, true);

	    // turn off the drums
	    System.out.println("Playing (without drums)...");
	    Sequencer sequencer = player.getSequencer();
	    //sequencer.setTrackMute(1, true);
	    sequencer.addMetaEventListener(this);
	  }
	@Override
	public void meta(MetaMessage arg0) {
		Sequencer sequencer = player.getSequencer();
	      if (sequencer.getTrackMute(1)) {
	        // turn on the drum track
	        System.out.println("Turning on drums...");
	        sequencer.setTrackMute(1, false);
	      } else {
	        // close the sequencer and exit
	        System.out.println("Exiting...");
	        player.close();
	        //System.exit(0);
	        run();
	      }
	}

}
