package world;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Time {
	public static Timer timer = new Timer();
	public static float time = 0;
	public static Task task;
	public Time(){
		Timer.schedule(new Task(){
            @Override
            public void run() {
                time += 0.01;
                if(time%1 == 0){
                	Zomtasia.Zomtasia.waterShader.nextRandom();
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
}
