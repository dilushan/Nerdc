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

public class Employee {

    private String name;
    private String EmployeeId;
    private double BasicSalary;
    private String Catagery;
    private String Designation;
    private String Department;
    private double incentive;
    private double weight;
    private double salary;

    public Employee(String name, String EmployeeId, double BasicSalary, String Catagery, String Designation, String Department, Double weight) {
        this.name = name;
        this.EmployeeId = EmployeeId;
        this.BasicSalary = BasicSalary;
        this.Catagery = Catagery;
        this.Designation = Designation;
        this.Department = Department;
        this.weight = weight;
    }

    public void salaryCalc() {// calculating  final salary
        salary = this.BasicSalary + incentive;
    }

    public void Addincentive(double incentive) {//add incentive value because he may involve several project 
        this.incentive += incentive;
    }

    public double getIncentive() {
        return incentive;
    }

    public void setIncentive(double incentive) {
        this.incentive = incentive;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public double getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(double BasicSalary) {
        this.BasicSalary = BasicSalary;
    }

    public String getCatagery() {
        return Catagery;
    }

    public void setCatagery(String Catagery) {
        this.Catagery = Catagery;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

}
