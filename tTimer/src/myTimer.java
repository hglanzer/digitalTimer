import java.util.TimerTask;

public class myTimer extends TimerTask{
	@Override
	public void run() {
		//timeDisplay.printValueStdOut();
		timerGUI.update();
	}
		
}
