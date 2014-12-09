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
public class Project {

    private boolean ApprovalObtainForIncentiveScheme;
    private boolean ProjectIsCompleted;
    private String ProjectNo;
    private String ProjectName;
    private String Department;
    private String StartingDate;
    private String CompletionDate;
    private DirectProjectStaff dstaff;
    private IndirectProjectStaff instaff;
    private OtherIncomeGeneratingStaff otherstaff;
    private Pool pool;
    private ResearchPool repool;
    private ProjectIncome pincome;
    private DirectCost dcost;
    private IndirectCost icost;
    private double sharedPresentage;
    private double profit;
    private double sharedIncentive;

    public double calCprofit() {//calculate project profit
        double profit = 0;
        profit = pincome.getResult() - (dcost.getDcost() + icost.getIcost());
        return profit;
    }

    public double getSharedIncentive() {
        this.sharedIncentive = profit * this.sharedPresentage / 100;
        return sharedIncentive;
    }

    public void setSharedIncentive(double sharedIncentive) {
        this.sharedIncentive = sharedIncentive;
    }

    public double getSharedPresentage() {
        return sharedPresentage;
    }

    public void setSharedPresentage(double sharedPresentage) {
        this.sharedPresentage = sharedPresentage;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public boolean isApprovalObtainForIncentiveScheme() {
        return ApprovalObtainForIncentiveScheme;
    }

    public void setApprovalObtainForIncentiveScheme(boolean ApprovalObtainForIncentiveScheme) {
        this.ApprovalObtainForIncentiveScheme = ApprovalObtainForIncentiveScheme;
    }

    public boolean isProjectIsCompleted() {
        return ProjectIsCompleted;
    }

    public void setProjectIsCompleted(boolean ProjectIsCompleted) {
        this.ProjectIsCompleted = ProjectIsCompleted;
    }

    public String getProjectNo() {
        return ProjectNo;
    }

    public void setProjectNo(String ProjectNo) {
        this.ProjectNo = ProjectNo;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getStartingDate() {
        return StartingDate;
    }

    public void setStartingDate(String StartingDate) {
        this.StartingDate = StartingDate;
    }

    public String getCompletionDate() {
        return CompletionDate;
    }

    public void setCompletionDate(String CompletionDate) {
        this.CompletionDate = CompletionDate;
    }

    public DirectProjectStaff getDstaff() {
        return dstaff;
    }

    public void setDstaff(DirectProjectStaff dstaff) {
        this.dstaff = dstaff;
    }

    public IndirectProjectStaff getInstaff() {
        return instaff;
    }

    public void setInstaff(IndirectProjectStaff instaff) {
        this.instaff = instaff;
    }

    public OtherIncomeGeneratingStaff getOtherstaff() {
        return otherstaff;
    }

    public void setOtherstaff(OtherIncomeGeneratingStaff otherstaff) {
        this.otherstaff = otherstaff;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public ResearchPool getRepool() {
        return repool;
    }

    public void setRepool(ResearchPool repool) {
        this.repool = repool;
    }

    public ProjectIncome getPincome() {
        return pincome;
    }

    public void setPincome(ProjectIncome pincome) {
        this.pincome = pincome;
    }

    public DirectCost getDcost() {
        return dcost;
    }

    public void setDcost(DirectCost dcost) {
        this.dcost = dcost;
    }

    public IndirectCost getIcost() {
        return icost;
    }

    public void setIcost(IndirectCost icost) {
        this.icost = icost;
    }

}
