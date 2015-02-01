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
public class CpuThread implements Runnable{
	MatcherMain matcherMain = new MatcherMain();
	MatcherMotherboardCompatibility matchMobo = matcherMain.matchMobo;
	String motherboardSocket;
	Hardware hardware = new Hardware();

	public CpuThread(String motherboardSocket){
		this.motherboardSocket = motherboardSocket;
	}

	public void run(){
		try{
			hardware = call();
		}catch(Exception e){}
	}

	public Hardware call() throws Exception {
		return matchMobo.matchCpuBasedOnMobo(this.motherboardSocket);
	}
	public Hardware getHardware(){
		return this.hardware;
	}
}
