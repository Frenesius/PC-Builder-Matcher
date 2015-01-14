package threads;

import java.util.concurrent.Callable;

import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;

import components.Hardware;

public class GpuThread implements Runnable{
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardCardInterface;
	Hardware h = new Hardware();

	public void run(){
		try{
			h = call();
		}catch(Exception e){

		}
	}
	public GpuThread(String motherboardCardInterface){
		this.motherboardCardInterface = motherboardCardInterface;
	}
	
	public Hardware call() throws Exception {
		return matchMobo.matchGpuBasedOnMobo(this.motherboardCardInterface);
	}
	public Hardware getHardware(){
		return this.h;
	}
}
