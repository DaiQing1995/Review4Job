package designpattern.state;

public class HappyMood implements MoodState {

	@Override
	public void speakPhone() {
		System.out.println("very happy to talk with you!");
	}

	@Override
	public void talkVoice() {
		System.out.println("I am in medium voice!");
	}

	@Override
	public void studyEfficiency() {
		System.out.println("I can finish observing every parts of elephant in 1 hour!");
	}

}
