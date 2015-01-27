package parsing;

import java.util.ArrayList;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.core.NodeProxy;

import components.CPU;
import components.GPU;
import components.Memory;
import components.Motherboard;

public class ParseHardware {		//Needs optimizing.
	//TODO ALLES!!!!
	public ArrayList parseQueryToGPUObject(GraphDatabaseService db, ArrayList components){
		ArrayList componentsArray = new ArrayList();
		for(int i = 0; i<components.size();i++){
			GPU gpu = new GPU();
			Transaction tx =  db.beginTx();
			try{
				NodeProxy node = (NodeProxy) components.get(i);
				//Hardware
				gpu.setEan(node.getProperty("EAN").toString());
				gpu.setSku(node.getProperty("SKU").toString());
				gpu.setTweakersid(node.getProperty("Tweakers ID").toString());
				gpu.setAantalaanbieders(node.getProperty("Aantal aanbieders").toString());
				gpu.setEersteprijsvermelding(node.getProperty("Eerste prijsvermelding").toString());
				gpu.setMerk(node.getProperty("Merk").toString());
				gpu.setUitvoering(node.getProperty("Uitvoering").toString());
				gpu.setUrl(node.getProperty("url").toString());
				gpu.setIsEmpty(false);
				gpu.setIsMatched(true);

				//Gpu specific
				gpu.setGeheugengrootte(node.getProperty("Geheugengrootte").toString());
				gpu.setVideochipfabrikant(node.getProperty("Videochipfabrikant").toString());
				gpu.setGeheugenbusbreedte(node.getProperty("Geheugen Busbreedte").toString());
				gpu.setChipsetgeneratie(node.getProperty("Chipset generatie").toString());
				gpu.setOpenglversie(node.getProperty("OpenGL versie").toString());
				gpu.setTypekoeling(node.getProperty("Type koeling").toString());
				gpu.setAantalslots(node.getProperty("Aantal slots").toString());
				gpu.setCardinterfacevideo(node.getProperty("Card Interface (Video)").toString());
				gpu.setGeheugentypevideokaarten(node.getProperty("Geheugen Type (videokaarten)").toString());
				gpu.setVerkoopstatus(node.getProperty("Verkoopstatus").toString());
				gpu.setAantalpinsvideokaarten(node.getProperty("Aantal pins (videokaarten)").toString());
				gpu.setLinkinterface(node.getProperty("Link Interface").toString());
				gpu.setNominalesnelheidvideochip(node.getProperty("Nominale snelheid videochip").toString());
				gpu.setVideouit(node.getProperty("Video uit").toString());
				gpu.setLengte(node.getProperty("Lengte").toString());
				gpu.setMaximaleturbofrequentie(node.getProperty("Maximale turbo frequentie").toString());
				gpu.setGeheugensnelheid(node.getProperty("Geheugen Snelheid").toString());
				gpu.setShadermodel(node.getProperty("Shader model").toString());
				gpu.setDirectxversie(node.getProperty("DirectX versie").toString());
				gpu.setVideochip(node.getProperty("Videochip").toString());
				gpu.setAantal6pins(node.getProperty("Aantal 6 pins").toString());
				gpu.setProduct(node.getProperty("Product").toString());

				tx.success();
			}catch(Exception e){
				gpu.setIsEmpty(true);
				gpu.setIsMatched(false);
			} finally {
				componentsArray.add(gpu);
				tx.close();
			}
		}
		return componentsArray;
	}

	public ArrayList parseQueryToCPUObject(GraphDatabaseService db, ArrayList components){
		ArrayList componentsArray = new ArrayList();
		for(int i = 0; i<components.size();i++){
			CPU cpu = new CPU();
			Transaction tx =  db.beginTx();
			try{
				NodeProxy node = (NodeProxy) components.get(i);
				//Hardware
				cpu.setEan(node.getProperty("EAN").toString());
				cpu.setSku(node.getProperty("SKU").toString());
				cpu.setTweakersid(node.getProperty("Tweakers ID").toString());
				cpu.setAantalaanbieders(node.getProperty("Aantal aanbieders").toString());
				cpu.setEersteprijsvermelding(node.getProperty("Eerste prijsvermelding").toString());
				cpu.setMerk(node.getProperty("Merk").toString());
				cpu.setUitvoering(node.getProperty("Uitvoering").toString());
				cpu.setUrl(node.getProperty("url").toString());
				cpu.setIsEmpty(false);
				cpu.setIsMatched(true);

				//Cpu specific
				cpu.setSnelheid(node.getProperty("Snelheid").toString());
				cpu.setAantalcores(node.getProperty("Aantal cores").toString());
				cpu.setSocket(node.getProperty("Socket").toString());
				cpu.setProcestechnologie(node.getProperty("Procestechnologie").toString());
				cpu.setThermaldesignpower(node.getProperty("Thermal Design Power").toString());

				tx.success();
			}catch(Exception e){
				cpu.setIsEmpty(true);
				cpu.setIsMatched(false);
			}finally {
				componentsArray.add(cpu);
				tx.close();}
		}
		return componentsArray;
	}
	public ArrayList parseQueryToMotherboardObject(GraphDatabaseService db, ArrayList components){
		ArrayList componentsArray = new ArrayList();
		for(int i = 0; i<components.size();i++){
			Motherboard mb = new Motherboard();
			Transaction tx =  db.beginTx();
			try{
				NodeProxy node = (NodeProxy) components.get(i);
				//Hardware
				mb.setEan(node.getProperty("EAN").toString());
				mb.setSku(node.getProperty("SKU").toString());
				mb.setTweakersid(node.getProperty("Tweakers ID").toString());
				mb.setAantalaanbieders(node.getProperty("Aantal aanbieders").toString());
				mb.setEersteprijsvermelding(node.getProperty("Eerste prijsvermelding").toString());
				mb.setMerk(node.getProperty("Merk").toString());
				mb.setUitvoering(node.getProperty("Uitvoering").toString());
				mb.setUrl(node.getProperty("url").toString());
				mb.setIsEmpty(false);
				mb.setIsMatched(true);

				//Mobo specific
				mb.setGeheugentype(node.getProperty("Geheugentype (moederbord)").toString().toLowerCase());
				mb.setSocket(node.getProperty("Socket").toString().toLowerCase());
				mb.setCardinterface(node.getProperty("Card Interface (moederbord)").toString().toLowerCase());
				mb.setLinkinterface(node.getProperty("Link Interface").toString());
				mb.setVideouit(node.getProperty("Video uit").toString());
				mb.setVerbindingusbfw(node.getProperty("Verbinding (USB/FW)").toString());
				mb.setRaidmodi(node.getProperty("Raid-modi").toString());
				mb.setAudiouitgangen(node.getProperty("Audio uitgangen").toString());
				mb.setVerbindingethernet(node.getProperty("Verbinding (Ethernet)").toString());
				mb.setMoederbordchipset(node.getProperty("Moederbordchipset").toString());
				mb.setNetwerkchip(node.getProperty("Netwerkchip").toString());
				mb.setVerbindingoverige(node.getProperty("Verbinding (overige)").toString());
				mb.setMaximumgeheugengrootte(node.getProperty("Maximum geheugengrootte").toString());
				mb.setAantalpciex16slots(node.getProperty("Aantal PCI-e x16 slots").toString());
				mb.setBluetoothaanwezig(node.getProperty("Bluetooth aanwezig").toString());
				mb.setAudiochip(node.getProperty("Audiochip").toString());
				mb.setHardeschijfbusintern(node.getProperty("Hardeschijf bus (intern)").toString());
				mb.setAantalsockets(node.getProperty("Aantal sockets").toString());
				mb.setFormfactor(node.getProperty("Form Factor").toString());
				mb.setBiosuefi(node.getProperty("BIOS of UEFI").toString());
				mb.setDualofsinglebiosuefi(node.getProperty("Dual of Single BIOS/UEFI").toString());
				mb.setAudiokanalen(node.getProperty("Audio kanalen").toString());

				tx.success();
			}catch(Exception e){
				mb.setIsEmpty(true);
				mb.setIsMatched(false);
			}finally {
				componentsArray.add(mb);
				tx.close();}
		}
		return componentsArray;
	}
	public ArrayList parseQueryToRAMObject(GraphDatabaseService db, ArrayList components){
		ArrayList componentsArray = new ArrayList();
		for(int i = 0; i<components.size();i++){
			Memory ram = new Memory();

			//TODO
			Transaction tx =  db.beginTx();
			try{
				NodeProxy node = (NodeProxy) components.get(i);
				//Hardware
				ram.setEan(node.getProperty("EAN").toString());
				ram.setSku(node.getProperty("SKU").toString());
				ram.setTweakersid(node.getProperty("Tweakers ID").toString());
				ram.setAantalaanbieders(node.getProperty("Aantal aanbieders").toString());
				ram.setEersteprijsvermelding(node.getProperty("Eerste prijsvermelding").toString());
				ram.setMerk(node.getProperty("Merk").toString());
				ram.setUitvoering(node.getProperty("Uitvoering").toString());
				ram.setUrl(node.getProperty("url").toString());
				ram.setIsEmpty(false);
				ram.setIsMatched(true);

				//Ram specific
				ram.setGeheugengrootte(node.getProperty("Geheugengrootte").toString());
				ram.setFabrieksgarantie(node.getProperty("Fabrieksgarantie").toString());
				ram.setGeheugenspecificatie(node.getProperty("Geheugen Specificatie").toString());
				ram.setSerie(node.getProperty("Serie").toString());
				ram.setLowvoltageddr(node.getProperty("Low Voltage DDR").toString());
				ram.setModulegrootte(node.getProperty("Modulegrootte").toString());
				ram.setGeheugencaslatency(node.getProperty("Geheugen CAS Latency").toString());
				ram.setGeheugentype(node.getProperty("Geheugentype").toString());
				ram.setPrijspergbgeheugen(node.getProperty("Prijs per GB (geheugen)").toString());
				ram.setSpanning(node.getProperty("Spanning").toString());
				ram.setAantal(node.getProperty("Aantal").toString());

				tx.success();
			}catch(Exception e){
				ram.setIsEmpty(true);
				ram.setIsMatched(false);
			}finally {
				componentsArray.add(ram);
				tx.close();}
		}
		return componentsArray;
	}
	public static boolean isASubClass(Class classTypeWeWant, Object objectWeHave) {
	    return classTypeWeWant.isAssignableFrom(objectWeHave.getClass());
	}
}
