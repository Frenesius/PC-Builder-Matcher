package threads;

import java.util.concurrent.Callable;

import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;
import components.Hardware;


public class CpuThread implements Runnable{
	
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardSocket;
	Hardware h = new Hardware();


	public CpuThread(String motherboardSocket){

		this.motherboardSocket = motherboardSocket;
	}

	public void run(){
		try{
			h = call();
		}catch(Exception e){}
	}

	//Run
	public Hardware call() throws Exception {
		return matchMobo.matchCpuBasedOnMobo(this.motherboardSocket);
	}
	public Hardware getHardware(){
		return this.h;
	}
}
