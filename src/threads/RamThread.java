package threads;

import java.util.concurrent.Callable;

import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;
import components.Hardware;

public class RamThread implements Callable<Hardware>{
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardGeheugenType;
	
	public RamThread(String motherboardGeheugenType){
		this.motherboardGeheugenType = motherboardGeheugenType;
	}

	public Hardware call() throws Exception {
		return matchMobo.matchRamBasedOnMobo(this.motherboardGeheugenType);
	}
}
