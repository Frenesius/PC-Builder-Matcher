package main;

import java.util.ArrayList;
import java.util.List;

import matcher.MatcherMain;

import org.neo4j.graphdb.GraphDatabaseService;

import databasemanager.Neo4jManager;

public class Main {

	public static void main(String[] args){
	    Neo4jManager a = new Neo4jManager();
	    GraphDatabaseService b;
	    Main main = new Main();
	    MatcherMain matcher = new MatcherMain();
	    
	    List list;
	    ArrayList arr = new ArrayList();
	    ArrayList webInput = new ArrayList();
	    
	    b = a.openNeo4jDatabase();
	    arr = a.getAllNodes(b);
	    webInput = main.inputWebserver();
	    
	    ArrayList matchedComponents = matcher.getNodesByInput(webInput);
	    
	}
	public static ArrayList inputWebserver(){
		ArrayList a = new ArrayList();
		String CPU = null;
		String GPU = null;
		String Memory = null;
		String HDD = null;
		String SSD = null;
		String PSU = null;
		String Case = null;
		String Motherboard = null;
		String OpticalDrive = null;
		String SoundCard = null;
		
		CPU = "{'SKU':'587505-B21',"
				+ "'Laagste prijs':' ',"
				+ "'Uitvoering':'HP Intel Xeon L5630',"
				+ "'EAN':'0884962471586, 4948382686046',"
				+ "'Tweakers ID':'356597',"
				+ "'Aantal aanbieders':'8 winkels',"
				+ "'Merk':'HP',"
				+ "'Eerste prijsvermelding':'vrijdag 15 november 2013',"
				+ "'Snelheid':'2,13GHz',"
				+ "'CPU Cache Level 3':'12MB',"
				+ "'Aantal cores':'Quad Core',"
				+ "'Socket':'1366',"
				+ "'Procestechnologie':'32 nm',"
				+ "'Thermal Design Power':'40W',"
				+ "'url':'http://tweakers.net/pricewatch/356597/hp-intel-xeon-l5630/'}";
		
		a.add(CPU);
		a.add(GPU);
		a.add(Memory);
		a.add(HDD);
		a.add(SSD);
		a.add(PSU);
		a.add(Case);
		a.add(Motherboard);
		a.add(OpticalDrive);
		a.add(SoundCard);
		return a;
	}
}	