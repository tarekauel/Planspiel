package Server;

import Constant.Constant;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 18:11
 */

public class MarketResearch extends Department {

    public MarketResearch(Company c) throws Exception {
        super(c, "Marktforschung",Constant.DepartmentFixcost.MARKET_RESEARCH);
    }
    
    //TODO: flag fuer isbookedfornextround automatische kostenverursachung

}
