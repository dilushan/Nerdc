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
public class ProjectIncome {

    private double ExpectedIncome;
    private double InvoiceIncome;
    private double ReceivedAmount;
    private double dueAmount;
    private double result;

    public double calcResult() {
        result = this.InvoiceIncome;
        return result;
    }

    public double getExpectedIncome() {
        return ExpectedIncome;
    }

    public void setExpectedIncome(double ExpectedIncome) {
        this.ExpectedIncome = ExpectedIncome;
    }

    public double getInvoiceIncome() {
        return InvoiceIncome;
    }

    public void setInvoiceIncome(double InvoiceIncome) {
        this.InvoiceIncome = InvoiceIncome;
    }

    public double getReceivedAmount() {
        return ReceivedAmount;
    }

    public void setReceivedAmount(double ReceivedAmount) {
        this.ReceivedAmount = ReceivedAmount;
    }

    public double getDueAmount() {
        this.dueAmount = this.InvoiceIncome - this.ReceivedAmount;
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

}
