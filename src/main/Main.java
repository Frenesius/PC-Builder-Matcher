package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import matcher.MatcherMain;

import org.neo4j.graphdb.GraphDatabaseService;

import databasemanager.Neo4jManager;

public class Main {

	public static void main(String[] args) throws SQLException{
		Main main = new Main();
	    main.testMatcher();
	}
	public void testMatcher() throws SQLException{
		//Connections and objects
		Main main = new Main();
		Neo4jManager a = new Neo4jManager();
	    GraphDatabaseService b;
	    MatcherMain matcher = new MatcherMain();
	    b = a.openNeo4jDatabase();
	    
	    //Lists
	    
	    List list;
	    ArrayList arr = new ArrayList();
	    arr = a.getAllNodes(b);
	    a.closeNeo4jDatabase(b);
	    
		/*Case:
	    *	1 Krijg een arraylist van mo
	    *	2 Parse arraylist in onderdelen
	    *	3 Check compatibility
	    *	4 Maak nieuwe list met onderdelen
	    *
	    *
	    */
	    
	    /*
	     * 1 Is de input van mo
	     * Kijgt het in form van:
	     * null voor not selected.
	     * JSON voor geselecteerde onderdelen.
	     */
	    ArrayList webInput = new ArrayList();	 
	    webInput = main.inputWebserverMotherboard();
	    /*	2 Haalt op wat meegegeven is en parsed het in objecten. 
	    *	null betekend dat onderdeel niet meegegeven is
	    */
	    ArrayList matchedComponents = matcher.getNodesByInput(webInput);
	    
	    /*
	     * 3 Matcher
	     * 	Moet onderdelen matchen
	     */
	    ArrayList matchedHardware = new ArrayList();
	    matchedHardware = matcher.matchFromMotherboard(matchedComponents);
	    String aasd = "s";
	    aasd.isEmpty();
	}
	public ArrayList inputWebserverCPU(){
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
	public ArrayList inputWebserverMotherboard(){
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
		
		Motherboard = "{'SKU':'7924-002R, H97M-G43',"
				+ "'Laagste prijs':' ',"
				+ "'Uitvoering':'MSI H97M-G43',"
				+ "'Beoordeling':' (1 review)',"
				+ "'EAN':'4526541792427, 4719072345914',"
				+ "'Tweakers ID':'385560',"
				+ "'Aantal aanbieders':'25 winkels',"
				+ "'Link Interface':'ATi Crossfire',"
				+ "'Video uit':'D-Sub (VGA), DisplayPort, DVI-D, HDMI',"
				+ "'Merk':'MSI',"
				+ "'Eerste prijsvermelding':'zondag 11 mei 2014',"
				+ "'Bijzonderheden':'PCI-e 2.0 x16 slot draait in x4 modus.',"
				+ "'Socket':'1151',"
				+ "'Verbinding (USB/FW)':'8x USB 2.0, 6x USB 3.0',"
				+ "'Raid-modi':'0, 1, 1E, 5, 10',"
				+ "'Audio uitgangen':'Analoog (3,5mm), "
				+ "Digitaal Optisch (S/PDIF)',"
				+ "'Geheugentype (moederbord)':'4x DDR3',"
				+ "'Verbinding (Ethernet)':'Ethernet 1Gbps',"
				+ "'Moederbordchipset':'Intel H97',"
				+ "'Netwerkchip':'Realtek RTL8111G',"
				+ "'Verbinding (overige)':'PS/2',"
				+ "'Maximum geheugengrootte':'32GB',"
				+ "'Aantal PCI-e x16 slots':'2',"
				+ "'Bluetooth aanwezig':'Nee',"
				+ "'Audiochip':'Realtek ALC892',"
				+ "'Card Interface (moederbord)':'2x PCI-e 2.0 x1, PCI-e 2.0 x16, PCI-e 3.0 x16',"
				+ "'Hardeschijf bus (intern)':'M.2, 6x SATA-600',"
				+ "'Aantal sockets':'1',"
				+ "'Form Factor':'Micro-ATX (\\xb5ATX)',"
				+ "'BIOS of UEFI':'UEFI',"
				+ "'Dual of Single BIOS/UEFI':'Single',"
				+ "'Audio kanalen':'8 (7.1)'}";

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