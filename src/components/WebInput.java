package components;

import java.util.ArrayList;

public class WebInput {
	
	public ArrayList inputWebserverCPU(){
		//Possible input from webserver
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
		//Possible input from webserver
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


	public ArrayList inputWebserverMatchToMobo(){
		//Possible input from webserver
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
		CPU = "{'SKU':'BX80623E31230','Laagste prijs':' ','Beoordeling':' (2 reviews)','EAN':'0675901069526, 0735858217668','Type koeling':'Actieve fan','Tweakers ID':'282219','Aantal aanbieders':'1 winkel','Maximale turbo frequentie':'3,6GHz','Merk':'Intel','Eerste prijsvermelding':'donderdag 28 april 2011','Product':'Intel Xeon E3-1230','CPU sSpec Number':'SR00H','Snelheid':'3,2GHz','CPU Cache Level 3':'8MB','Bus snelheid':'4x100MHz','Geheugen Specificatie':'PC3-10600 (DDR3-1333)','CPU Instructieset':'EM64T, NX-bit, SSE, SSE2, SSE3, SSE4, SSE4.1, SSE4.2, XD','Aantal cores':'Quad Core','Serie':'Xeon','Socket':'1155','Ge\\xefntegreerde graphics':'Nee','Verkoopstatus (CPU)':'Boxed','Procestechnologie':'32 nm','Thermal Design Power':'80W','Virtualisatie':'Ja','Threads_oud':'4/8','url':'http://tweakers.net/pricewatch/282219/intel-xeon-e3-1230-boxed/'}";
	
		GPU = "{'SKU':'GTX 970 GAMING 4G, V316-001R',"
				+ "'Laagste prijs':' ',"
				+ "'Hoogste HDMI-versie':'HDMI 2.0',"
				+ "'Uitvoering':'GAMING 4G',"
				+ "'Beoordeling':' (7 reviews)',"
				+ "'Geheugengrootte':'4GB',"
				+ "'Videochipfabrikant':'Nvidia',"
				+ "'Geheugen Busbreedte':'256 bit',"
				+ "'EAN':'4719072365752',"
				+ "'Chipset generatie':'GeForce 900 Serie',"
				+ "'OpenGL versie':'4.4',"
				+ "'Type koeling':'Passieve fan',"
				+ "'Tweakers ID':'416125',"
				+ "'Aantal slots':'2x',"
				+ "'Card Interface (Video)':'PCI-e 3.0 x16',"
				+ "'Hoogte':'35mm',"
				+ "'Maximale resolutie':'4096x2160 (Cinema 4K)',"
				+ "'Geheugen Type (videokaarten)':'GDDR5',"
				+ "'Aantal aanbieders':'21 winkels',"
				+ "'Aantal pins (videokaarten)':'6 pins, 8 pins',"
				+ "'Link Interface':'Nvidia SLi',"
				+ "'Nominale snelheid videochip':'1,14GHz',"
				+ "'Aantal 8 pins':'1x',"
				+ "'Video uit':'DisplayPort, DVI-D, DVI-I, HDMI',"
				+ "'Lengte':'269mm',"
				+ "'Maximale turbo frequentie':'1,279GHz',"
				+ "'Geheugen Snelheid':'7,01GHz',"
				+ "'Shader model':'5.0',"
				+ "'Rekenkernen':'1.664',"
				+ "'Merk':'MSI',"
				+ "'Eerste prijsvermelding':'zaterdag 20 september 2014',"
				+ "'Bijzonderheden':'Fans draaien niet onder 60\\xb0C',"
				+ "'Breedte':'141mm',"
				+ "'DirectX versie':'12.0',"
				+ "'Videochip':'GeForce GTX 970',"
				+ "'Stroomverbruik':'148W',"
				+ "'Video Adapter':'DVI naar D-Sub adapter',"
				+ "'Aantal 6 pins':'1x',"
				+ "'Product':'MSI GeForce GTX 970',"
				+ "'Hoogste DisplayPort versie':'DisplayPort 1.2',"
				+ "'url':'http://tweakers.net/pricewatch/416125/msi-geforce-gtx-970-gaming-4g/'}";
		
		Memory = "{'SKU':'BLT2C4G3D1608ET3LX0C, BLT2C4G3D1608ET3LX0CEU','Laagste prijs':' ','Uitvoering':'Crucial Ballistix Tactical BLT2C4G3D1608ET3LX0CEU','Beoordeling':' (26 reviews)','Geheugengrootte':'8GB','EAN':'0649528762931','Tweakers ID':'323351','Aantal aanbieders':'32 winkels','Fabrieksgarantie':'Levenslang','Merk':'Crucial','Eerste prijsvermelding':'dinsdag 18 december 2012','Bijzonderheden':'Low Profile','Geheugen Specificatie':'PC3-12800 (DDR3-1600)','Serie':'Ballistix Tactical','Low Voltage DDR':'Ja (DDR3L)','Modulegrootte':'4GB','Aantal':'2x','Geheugen CAS Latency':'8','Geheugentype':'DDR3','Prijs per GB (geheugen)':'\\u20ac8,173','Spanning':'1,35V','url':'http://tweakers.net/pricewatch/323351/crucial-ballistix-tactical-blt2c4g3d1608et3lx0ceu/'}";
		
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
