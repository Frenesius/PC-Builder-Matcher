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
public class GpuThread implements Runnable{
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardCardInterface;
	Hardware hardware = new Hardware();

	public void run(){
		try{
			hardware = call();
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
		return this.hardware;
	}
}
