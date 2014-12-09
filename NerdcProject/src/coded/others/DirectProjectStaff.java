/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coded.others;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Shehan
 */
class DirectProjectStaff {

    ArrayList<Employee> list = new ArrayList<Employee>();
    private double sumweight;
    private double precentage;
    private Employee ele;

    public void calcIntensive(double profit) {
        double unitVal = (profit * precentage) / (this.getSumweight() * 100);
        Iterator<Employee> it = list.iterator();
        while (it.hasNext()) {
            ele = it.next();
            ele.Addincentive(ele.getWeight() * unitVal);
        }
    }

    public ArrayList<Employee> getList() {
        return list;
    }

    public void setList(ArrayList<Employee> list) {
        this.list = list;
    }

    public double getPrecentage() {
        return precentage;
    }

    public void setPrecentage(double precentage) {
        this.precentage = precentage;
    }

    public boolean addEmployee(Employee ele) {
        list.add(ele);
        return true;
    }

    public double getSumweight() {
        Iterator<Employee> it = list.iterator();
        this.sumweight = 0;
        while (it.hasNext()) {
            this.sumweight += it.next().getWeight();
        }
        return sumweight;
    }

    public void setSumweight(double sumweight) {
        this.sumweight = sumweight;
    }

}
