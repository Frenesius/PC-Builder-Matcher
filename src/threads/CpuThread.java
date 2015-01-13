package threads;

import java.util.concurrent.Callable;

import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;
import components.Hardware;


public class CpuThread implements Callable<Hardware>{
	
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardSocket;
	
	public CpuThread(String motherboardSocket){
		this.motherboardSocket = motherboardSocket;
	}

	public Hardware call() throws Exception {
		return matchMobo.matchCpuBasedOnMobo(this.motherboardSocket);
	}
		
}
