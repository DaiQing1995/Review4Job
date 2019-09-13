package designpattern.state;

public class SadMood implements MoodState {

	@Override
	public void speakPhone() {
		System.out.println("nothing to talk with you!");
	}

	@Override
	public void talkVoice() {
		System.out.println("emmm~~~~~");
	}

	@Override
	public void studyEfficiency() {
		System.out.println("What is the result of one plus one!");
	}

}
