package Server;

import Constant.Constant;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 18:11
 */

public class MarketResearch extends Department {
	
	private boolean isBooked;

    public MarketResearch(Company c) throws Exception {
        super(c, "Marktforschung",Constant.DepartmentFixcost.MARKET_RESEARCH);
        
    }
    
    public void setIsBooked(boolean isBooked){
    	this.isBooked = isBooked;
    }
    
    public boolean getIsBooked(){
    	return isBooked;
    }
    //TODO: flag fuer isbookedfornextround automatische kostenverursachung

}
