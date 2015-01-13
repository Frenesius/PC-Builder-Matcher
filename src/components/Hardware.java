package components;

public class Hardware {
	private String sku;
	private String laagsteprijs;
	private String ean;
	private String tweakersid;
	private String aantalaanbieders;
	private String eersteprijsvermelding;
	private String merk;
	private String uitvoering;
	private String url;
	private Boolean isEmpty;
	private Boolean isMatched;

	//Data from the MySQL Database
	private String shopname;
	private String delivery;
	private String priceex;
	private String priceinc;
	private String linkshop;
	private String timestamp;
	private String hardwaresort;

	public Boolean getIsMatched() {
		return isMatched;
	}
	public void setIsMatched(Boolean isMatched) {
		this.isMatched = isMatched;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getPriceex() {
		return priceex;
	}
	public void setPriceex(String priceex) {
		this.priceex = priceex;
	}
	public String getPriceinc() {
		return priceinc;
	}
	public void setPriceinc(String priceinc) {
		this.priceinc = priceinc;
	}
	public String getLinkshop() {
		return linkshop;
	}
	public void setLinkshop(String linkshop) {
		this.linkshop = linkshop;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getLaagsteprijs() {
		return laagsteprijs;
	}
	public void setLaagsteprijs(String laagsteprijs) {
		this.laagsteprijs = laagsteprijs;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getTweakersid() {
		return tweakersid;
	}
	public void setTweakersid(String tweakersid) {
		this.tweakersid = tweakersid;
	}
	public String getAantalaanbieders() {
		return aantalaanbieders;
	}
	public void setAantalaanbieders(String aantalaanbieders) {
		this.aantalaanbieders = aantalaanbieders;
	}
	public String getEersteprijsvermelding() {
		return eersteprijsvermelding;
	}
	public void setEersteprijsvermelding(String eersteprijsvermelding) {
		this.eersteprijsvermelding = eersteprijsvermelding;
	}
	public String getMerk() {
		return merk;
	}
	public void setMerk(String merk) {
		this.merk = merk;
	}
	public String getUitvoering() {
		return uitvoering;
	}
	public void setUitvoering(String uitvoering) {
		this.uitvoering = uitvoering;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHardwaresort() {
		return hardwaresort;
	}
	public void setHardwaresort(String hardwaresort) {
		this.hardwaresort = hardwaresort;
	}
	public Boolean getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
}