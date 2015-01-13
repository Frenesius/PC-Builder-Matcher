package threads;

import java.util.concurrent.Callable;

import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;

import components.Hardware;

public class GpuThread implements Callable<Hardware>{
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardCardInterface;
	
	public GpuThread(String motherboardCardInterface){
		this.motherboardCardInterface = motherboardCardInterface;
	}
	
	public Hardware call() throws Exception {
		return matchMobo.matchRamBasedOnMobo(this.motherboardCardInterface);
	}
}
