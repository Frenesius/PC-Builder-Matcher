package components;

public class Motherboard extends Hardware{
	String cardinterface;	// origineel Card Interface (moederbord)
	String linkinterface;
	String videouit;
	String serie;
	String verbindingusbfw; //origineel Verbinding (USB/FW)
	String raidmodi;			// origineel Raid-modi
	String audiouitgangen;
	String geheugentype; // origineel Geheugentype (moederbord)
	String verbindingethernet; //origineel verbinding (ethernet)
	String moederbordchipset;
	String netwerkchip;
	String verbindingoverige; // origineel verbinding (overige)
	String maximumgeheugengrootte;
	String Aantalpciex16slots; //origineel Aantal PCI-e x16 slots
	String bluetoothaanwezig;
	String audiochip;
	String hardeschijfbusintern;  // origineel Hardeschijf bus (intern)
	String aantalsockets;
	String formfactor;
	String biosuefi;			// origineel BIOS of UEFI
	String dualofsinglebiosuefi;	// origineel Dual of Single BIOS/UEFI	
	String audiokanalen;
	String socket;
	
	public String getAantalsockets() {
		return aantalsockets;
	}
	public void setAantalsockets(String aantalsockets) {
		this.aantalsockets = aantalsockets;
	}
	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public String getCardinterface() {
		return cardinterface;
	}
	public void setCardinterface(String cardinterfacemobo) {
		this.cardinterface = cardinterfacemobo;
	}
	public String getLinkinterface() {
		return linkinterface;
	}
	public void setLinkinterface(String linkinterface) {
		this.linkinterface = linkinterface;
	}
	public String getVideouit() {
		return videouit;
	}
	public void setVideouit(String videouit) {
		this.videouit = videouit;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getVerbindingusbfw() {
		return verbindingusbfw;
	}
	public void setVerbindingusbfw(String verbindingusbfw) {
		this.verbindingusbfw = verbindingusbfw;
	}
	public String getRaidmodi() {
		return raidmodi;
	}
	public void setRaidmodi(String raidmodi) {
		this.raidmodi = raidmodi;
	}
	public String getAudiouitgangen() {
		return audiouitgangen;
	}
	public void setAudiouitgangen(String audiouitgangen) {
		this.audiouitgangen = audiouitgangen;
	}
	public String getGeheugentype() {
		return geheugentype;
	}
	public void setGeheugentype(String geheugentypemobo) {
		this.geheugentype = geheugentypemobo;
	}
	public String getVerbindingethernet() {
		return verbindingethernet;
	}
	public void setVerbindingethernet(String verbindingethernet) {
		this.verbindingethernet = verbindingethernet;
	}
	public String getMoederbordchipset() {
		return moederbordchipset;
	}
	public void setMoederbordchipset(String moederbordchipset) {
		this.moederbordchipset = moederbordchipset;
	}
	public String getNetwerkchip() {
		return netwerkchip;
	}
	public void setNetwerkchip(String netwerkchip) {
		this.netwerkchip = netwerkchip;
	}
	public String getVerbindingoverige() {
		return verbindingoverige;
	}
	public void setVerbindingoverige(String verbindingoverige) {
		this.verbindingoverige = verbindingoverige;
	}
	public String getMaximumgeheugengrootte() {
		return maximumgeheugengrootte;
	}
	public void setMaximumgeheugengrootte(String maximumgeheugengrootte) {
		this.maximumgeheugengrootte = maximumgeheugengrootte;
	}
	public String getAantalpciex16slots() {
		return Aantalpciex16slots;
	}
	public void setAantalpciex16slots(String aantalpciex16slots) {
		Aantalpciex16slots = aantalpciex16slots;
	}
	public String getBluetoothaanwezig() {
		return bluetoothaanwezig;
	}
	public void setBluetoothaanwezig(String bluetoothaanwezig) {
		this.bluetoothaanwezig = bluetoothaanwezig;
	}
	public String getAudiochip() {
		return audiochip;
	}
	public void setAudiochip(String audiochip) {
		this.audiochip = audiochip;
	}
	public String getHardeschijfbusintern() {
		return hardeschijfbusintern;
	}
	public void setHardeschijfbusintern(String hardeschijfbusintern) {
		this.hardeschijfbusintern = hardeschijfbusintern;
	}
	public String getFormfactor() {
		return formfactor;
	}
	public void setFormfactor(String formfactor) {
		this.formfactor = formfactor;
	}
	public String getBiosuefi() {
		return biosuefi;
	}
	public void setBiosuefi(String biosuefi) {
		this.biosuefi = biosuefi;
	}
	public String getDualofsinglebiosuefi() {
		return dualofsinglebiosuefi;
	}
	public void setDualofsinglebiosuefi(String dualofsinglebiosuefi) {
		this.dualofsinglebiosuefi = dualofsinglebiosuefi;
	}
	public String getAudiokanalen() {
		return audiokanalen;
	}
	public void setAudiokanalen(String audiokanalen) {
		this.audiokanalen = audiokanalen;
	}	
}
