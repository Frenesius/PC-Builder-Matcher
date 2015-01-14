package threads;

import java.util.concurrent.Callable;

import components.Memory;
import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;
import components.Hardware;

public class RamThread implements Runnable{
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardGeheugenType;
	Hardware h = new Hardware();

	public void run(){
		try{
			h = call();
		}catch(Exception e){

		}
	}
	public RamThread(String motherboardGeheugenType){
		this.motherboardGeheugenType = motherboardGeheugenType;
	}
	public Hardware call() throws Exception {
		return matchMobo.matchRamBasedOnMobo(this.motherboardGeheugenType);
	}
	public Hardware getHardware(){
		return this.h;
	}
}
