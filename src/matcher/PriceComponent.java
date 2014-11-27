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
	public ResultSet getPricesByComponentEan(String EAN){
		String query = "SELECT ean, shopname, delivery, priceex, priceinc, linkshop, max(timestamp) "
					 + "FROM hardwareprice.hardwareprice "
					 + "WHERE ean = '"+EAN+"' "
					 + "GROUP BY shopname;";
		ResultSet rs = this.mysqlManager.executeQueryMySql(this.mysqlConn, query);
		return rs;
	}
	public ArrayList getPricesByComponent(ArrayList component){
		ArrayList newComponentList = new ArrayList();
		for(int i = 0;i<component.size();i++){
			Hardware h = (Hardware) component.get(i);
			ResultSet rs  = this.getPricesByComponentEan(h.getEan());
			try {
				if(rs.next()){
					//System.out.println("=============================");
					h.setEan(rs.getString(rs.findColumn("ean")));
					h.setShopname(rs.getString(rs.findColumn("shopname")));
					h.setDelivery(rs.getString(rs.findColumn("delivery")));
					h.setPriceex(rs.getString(rs.findColumn("priceex")));
					h.setPriceinc(rs.getString(rs.findColumn("priceinc")));
					h.setLinkshop(rs.getString(rs.findColumn("linkshop")));
					h.setTimestamp((rs.getString(rs.findColumn("max(timestamp)"))));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newComponentList.add(h);
		}
		return newComponentList;
	}
	public Hardware comparePricesFromComponentArray(ArrayList component){
		float smallestNumber = 100;
		int cheapestIndex = 0;
		for(int i = 0; i<component.size();i++){
			Hardware h = (Hardware) component.get(i);
			float f = 0;
			try{
				f = Float.parseFloat(h.getPriceex());
				if(f<smallestNumber){
					smallestNumber = f;
					cheapestIndex = i;
				}
			}catch(Exception e){
			}
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

