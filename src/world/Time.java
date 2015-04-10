package world;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Time {
	public static Timer timer = new Timer();
	public static float time = 0;
	public static Task task;
	public static boolean paused;
	public Time(){
		Timer.schedule(new Task(){
            @Override
            public void run() {
                if(isPaused()){
                	
                }else{
                    time += 0.01;
                }
            }
        }
        , 0        //    (delay)
        , 0.01f     //    (seconds)
    );
	}
	public static float getTime(){
		return time;
	}
	public static boolean isPaused(){
		if(paused){
			return true;
		}
		return false;
	}
}
