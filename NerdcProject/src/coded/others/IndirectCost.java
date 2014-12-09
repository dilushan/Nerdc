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

class IndirectCost{
  private double Labour;
  private double Overhead;
  private double Recoveries;
  private double Others;
  private double icost;

    public double getLabour() {
        return Labour;
    }

    public void setLabour(double Labour) {
        this.Labour = Labour;
    }

    public double getOverhead() {
        return Overhead;
    }

    public void setOverhead(double Overhead) {
        this.Overhead = Overhead;
    }

    public double getRecoveries() {
        return Recoveries;
    }

    public void setRecoveries(double Recoveries) {
        this.Recoveries = Recoveries;
    }

    public double getOthers() {
        return Others;
    }

    public void setOthers(double Others) {
        this.Others = Others;
    }

    public double getIcost() {
        this.icost=this.Labour+this.Others+this.Overhead+this.Recoveries;
        return icost;
    }

    public void setIcost(double icost) {
        this.icost = icost;
    }
  
          
}