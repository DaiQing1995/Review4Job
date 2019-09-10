package designpattern.state;

public class DaiQing {
	private MoodState moodState;
	
	public DaiQing(MoodState moodState) {
		this.moodState = moodState;
	}
	
	public void talkWithDaiQing() {
		moodState.talkVoice();
		moodState.speakPhone();
		moodState.studyEfficiency();
	}
}
