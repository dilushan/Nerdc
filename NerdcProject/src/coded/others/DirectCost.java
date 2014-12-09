/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coded.others;

/**
 *
 * @author Shehan
 */

class DirectCost{
  private double  MaterialOthers;
  private double  OT;
  private double  TravellingSubsistence;
  private double  Transport;
  private double  dcost;

    public double getMaterialOthers() {
        return MaterialOthers;
    }

    public void setMaterialOthers(double MaterialOthers) {
        this.MaterialOthers = MaterialOthers;
    }

    public double getOT() {
        return OT;
    }

    public void setOT(double OT) {
        this.OT = OT;
    }

    public double getTravellingSubsistence() {
        return TravellingSubsistence;
    }

    public void setTravellingSubsistence(double TravellingSubsistence) {
        this.TravellingSubsistence = TravellingSubsistence;
    }

    public double getTransport() {
        return Transport;
    }

    public void setTransport(double Transport) {
        this.Transport = Transport;
    }

    public double getDcost() {
        this.dcost=this.MaterialOthers+this.OT+this.Transport+this.TravellingSubsistence;
        return dcost;
    }

    public void setDcost(double dcost) {
        this.dcost = dcost;
    }
  
      
}

