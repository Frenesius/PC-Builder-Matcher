package matcher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import components.Hardware;
import databasemanager.MySqlManager;

public class PriceComponent {
	MySqlManager mysqlManager;
	Connection mysqlConn;
	PriceComponent() throws SQLException{
		this.mysqlManager = new MySqlManager();
		this.mysqlConn = mysqlManager.connectDB();
		}

	public ArrayList getPricesByComponent(ArrayList component){
		ArrayList newComponentList = new ArrayList();
		for(int i = 0;i<component.size();i++){
			Hardware hardware = (Hardware) component.get(i);
			ResultSet rs  = mysqlManager.getPricesByComponentEan(this.mysqlConn, hardware.getEan());
			try {
				if(rs.next()){
					hardware.setEan(rs.getString(rs.findColumn("ean")));
					hardware.setShopname(rs.getString(rs.findColumn("shopname")));
					hardware.setDelivery(rs.getString(rs.findColumn("delivery")));
					hardware.setPriceex(rs.getString(rs.findColumn("priceex")));
					hardware.setPriceinc(rs.getString(rs.findColumn("priceinc")));
					hardware.setLinkshop(rs.getString(rs.findColumn("linkshop")));
					hardware.setTimestamp((rs.getString(rs.findColumn("max(timestamp)"))));
				}
			}catch(SQLException e){e.printStackTrace();}
			newComponentList.add(hardware);
		}
		return newComponentList;
	}
	public Hardware comparePricesFromComponentArray(ArrayList component){
		float lowstPrice = 100;
		int cheapestIndex = 0;
		for(int i = 0; i<component.size();i++){
			Hardware h = (Hardware) component.get(i);
			float price = 0;
			try{
				price = Float.parseFloat(h.getPriceex());
				if(price<lowstPrice){
					lowstPrice = price;
					cheapestIndex = i;
				}
			}catch(Exception e){continue;}
		}
		return (Hardware) component.get(cheapestIndex);
	}
	public void getLowestPrice(float[] priceList){
		float smallestNumber = priceList[0];
		for(int i = 0; i<priceList.length;i++){
			if(priceList[i]<smallestNumber)
				smallestNumber = priceList[i];
		}
	}

} 

