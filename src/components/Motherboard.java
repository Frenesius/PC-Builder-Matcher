package components;

public class Motherboard extends Hardware{
	private String cardinterface;	// origineel Card Interface (moederbord)
	private String linkinterface;
	private String videouit;
	private String serie;
	private String verbindingusbfw; //origineel Verbinding (USB/FW)
	private String raidmodi;			// origineel Raid-modi
	private String audiouitgangen;
	private String geheugentype; // origineel Geheugentype (moederbord)
	private String verbindingethernet; //origineel verbinding (ethernet)
	private String moederbordchipset;
	private String netwerkchip;
	private String verbindingoverige; // origineel verbinding (overige)
	private String maximumgeheugengrootte;
	private String aantalpciex16slots; //origineel Aantal PCI-e x16 slots		CHANGED THIS, WATCHOUT FOR BUGS
	private String bluetoothaanwezig;
	private String audiochip;
	private String hardeschijfbusintern;  // origineel Hardeschijf bus (intern)
	private String aantalsockets;
	private String formfactor;
	private String biosuefi;			// origineel BIOS of UEFI
	private String dualofsinglebiosuefi;	// origineel Dual of Single BIOS/UEFI
	private String audiokanalen;
	private String socket;
	
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
		return aantalpciex16slots;
	}
	public void setAantalpciex16slots(String aantalpciex16slots) {
		aantalpciex16slots = aantalpciex16slots;
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
