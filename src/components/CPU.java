package components;

/**
 * CPU class. Contains all attributes that a Processor has.
 * All the fields match the fields in the Neo4j Database.
 * Extends {@link components.Hardware}
 *
 * @author Frenesius
 * @since 1-1-2015
 * @version 0.1
 */
public class CPU extends Hardware{
	private String snelheid;
	private String cpumultiplier;
	private String aantalcores;
	private String socket;
	private String threadsnieuw;
	private String procestechnologie;
	private String thermaldesignpower;
	
	public String getSnelheid() {
		return snelheid;
	}
	public void setSnelheid(String snelheid) {
		this.snelheid = snelheid;
	}
	public String getCpumultiplier() {
		return cpumultiplier;
	}
	public void setCpumultiplier(String cpumultiplier) {
		this.cpumultiplier = cpumultiplier;
	}
	public String getAantalcores() {
		return aantalcores;
	}
	public void setAantalcores(String aantalcores) {
		this.aantalcores = aantalcores;
	}
	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public String getThreadsnieuw() {
		return threadsnieuw;
	}
	public void setThreadsnieuw(String threadsnieuw) {
		this.threadsnieuw = threadsnieuw;
	}
	public String getProcestechnologie() {
		return procestechnologie;
	}
	public void setProcestechnologie(String procestechnologie) {
		this.procestechnologie = procestechnologie;
	}
	public String getThermaldesignpower() {
		return thermaldesignpower;
	}
	public void setThermaldesignpower(String thermaldesignpower) {
		this.thermaldesignpower = thermaldesignpower;
	}
}
