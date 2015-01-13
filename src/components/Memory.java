package components;

public class Memory extends Hardware{
	private String geheugengrootte;
	private String fabrieksgarantie;
	private String geheugenspecificatie;
	private String serie;
	private String lowvoltageddr;
	private String modulegrootte;
	private String aantal;
	private String geheugencaslatency;
	private String geheugentype;
	private String prijspergbgeheugen;			// origineel Prijs per GB (geheugen)
	private String spanning;
	
	public String getGeheugengrootte() {
		return geheugengrootte;
	}
	public void setGeheugengrootte(String geheugengrootte) {
		this.geheugengrootte = geheugengrootte;
	}
	public String getFabrieksgarantie() {
		return fabrieksgarantie;
	}
	public void setFabrieksgarantie(String fabrieksgarantie) {
		this.fabrieksgarantie = fabrieksgarantie;
	}
	public String getGeheugenspecificatie() {
		return geheugenspecificatie;
	}
	public void setGeheugenspecificatie(String geheugenspecificatie) {
		this.geheugenspecificatie = geheugenspecificatie;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getLowvoltageddr() {
		return lowvoltageddr;
	}
	public void setLowvoltageddr(String lowvoltageddr) {
		this.lowvoltageddr = lowvoltageddr;
	}
	public String getModulegrootte() {
		return modulegrootte;
	}
	public void setModulegrootte(String modulegrootte) {
		this.modulegrootte = modulegrootte;
	}
	public String getAantal() {
		return aantal;
	}
	public void setAantal(String aantal) {
		this.aantal = aantal;
	}
	public String getGeheugencaslatency() {
		return geheugencaslatency;
	}
	public void setGeheugencaslatency(String geheugencaslatency) {
		this.geheugencaslatency = geheugencaslatency;
	}
	public String getGeheugentype() {
		return geheugentype;
	}
	public void setGeheugentype(String geheugentype) {
		this.geheugentype = geheugentype;
	}
	public String getPrijspergbgeheugen() {
		return prijspergbgeheugen;
	}
	public void setPrijspergbgeheugen(String prijspergbgeheugen) {
		this.prijspergbgeheugen = prijspergbgeheugen;
	}
	public String getSpanning() {
		return spanning;
	}
	public void setSpanning(String spanning) {
		this.spanning = spanning;
	}
}
