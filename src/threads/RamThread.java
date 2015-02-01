package threads;

import matcher.MatcherMain;
import matcher.MatcherMotherboardCompatibility;
import components.Hardware;

/**
 * Thread class.
 * This will get the Hardware from Neo4j Database.
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
public class RamThread implements Runnable{
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardGeheugenType;
	Hardware hardware = new Hardware();

	public void run(){
		try{
			hardware = call();
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
		return this.hardware;
	}
}
