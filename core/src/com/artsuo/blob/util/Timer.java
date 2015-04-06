package com.artsuo.blob.util;

public class Timer {

	private long startTime;
    private long duration;   
    private long elapsedTime;
    private boolean running;

    public Timer(long duration) {
        this.duration = duration;
        this.startTime = System.currentTimeMillis();      
        this.running = true;  
        this.elapsedTime = 0;
    }
    
    public Timer(long duration, boolean startTimer) {
        this.duration = duration;
        this.startTime = System.currentTimeMillis();      
        this.running = startTimer;   
        this.elapsedTime = 0;
    }
    
    public boolean isStarted() {
        return running;
    }

    public void restart(boolean startTimer) {
        running = startTimer;
        startTime = System.currentTimeMillis();      
    }
    
    public void restart() {
        running = true;
        startTime = System.currentTimeMillis();      
    }
    
    public void stop() {
    	running = false;
    	startTime = 0;
    }
    
    public void reset() {
    	running = false;
    	duration = 0;
    	startTime = 0;
    }
    
    public void pause() {
        // save remaining time to duration       
        running = false;
        duration = getRemainingTimeInSeconds();
    }
    
    public void setRunning(boolean started) {
        running = started;
    }
       
    public boolean isOver() {
        elapsedTime = System.currentTimeMillis() - startTime;
        if(elapsedTime < duration) {           
            return false;
        }             
        return true;
    } 
    
    public long getElapsedTimeInSeconds() {      
            return (System.currentTimeMillis() - startTime) / 1000;        
    }
         
    public long getRemainingTimeInSeconds() {
        return duration - getElapsedTimeInSeconds();
    }
      
    public long getDuration() {
		return duration;
	}
    
    public long getElapsedTime() {
		return elapsedTime;
	}
    
    public long getStartTime() {
		return startTime;
	}
    
    public void setDuration(long duration) {
		this.duration = duration;
	}
    
    public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
    
    public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
