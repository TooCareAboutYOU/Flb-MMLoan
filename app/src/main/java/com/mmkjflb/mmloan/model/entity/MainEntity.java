package com.mmkjflb.mmloan.model.entity;

/**
 * Created by Administrator on 2017/10/28.
 */

public class MainEntity {
    private int withdrawalNoStatus;//是否有取款码 1.取款码可用 -2 取消

    public int getWithdrawalNoStatus() {
        return withdrawalNoStatus;
    }

    public void setWithdrawalNoStatus(int withdrawalNoStatus) {
        this.withdrawalNoStatus = withdrawalNoStatus;
    }

    private boolean isCancelWithdrawalNo;//取款码是否取消

    public boolean isCancelWithdrawalNo() {
        return isCancelWithdrawalNo;
    }

    public void setCancelWithdrawalNo(boolean cancelWithdrawalNo) {
        isCancelWithdrawalNo = cancelWithdrawalNo;
    }

    private boolean isBeingProcessed;//是否是银行处理中

    private boolean lastTimeBorrow;

    public boolean isLastTimeBorrow() {
        return lastTimeBorrow;
    }

    public void setLastTimeBorrow(boolean lastTimeBorrow) {
        this.lastTimeBorrow = lastTimeBorrow;
    }

    private double amount;
    private double availableBalance;
    private double creditBalance;
    private double hasUseBalance;
    private String loanTime;
    private double overdueFine;
    private boolean overdueMark;
    private int overdueTime;
    private double repaymentAmount;
    private String repaymentTime;
    private int creditStatus;//
    private boolean isPaid;  //是否缴纳保证金
    private boolean isWithdrawal;  //是否拥有提升额度资格

    private int buttonDisabled;//
    private int bidId;//
    private int isCode; //0：表示没有还款码，还款界面为选择还款渠道；1：为还款过，显示还款码

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    private int billId;
    private boolean hasWithdrawalCode;//是否生成保证金取款码

    public void setHasWithdrawalCode(boolean hasWithdrawalCode) {
        this.hasWithdrawalCode = hasWithdrawalCode;
    }

    public boolean isHasWithdrawalCode() {
        return hasWithdrawalCode;
    }

    public boolean isBeingProcessed() {
        return isBeingProcessed;
    }

    public void setBeingProcessed(boolean beingProcessed) {
        isBeingProcessed = beingProcessed;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isWithdrawal() {
        return isWithdrawal;
    }

    public void setWithdrawal(boolean withdrawal) {
        isWithdrawal = withdrawal;
    }

    public int getIsCode() {
        return isCode;
    }

    public void setIsCode(int isCode) {
        this.isCode = isCode;
    }

    public int getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(int creditStatus) {
        this.creditStatus = creditStatus;
    }

    public int getBidsStatus() {
        return bidsStatus;
    }

    public void setBidsStatus(int bidsStatus) {
        this.bidsStatus = bidsStatus;
    }

    private int bidsStatus;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public double getHasUseBalance() {
        return hasUseBalance;
    }

    public void setHasUseBalance(double hasUseBalance) {
        this.hasUseBalance = hasUseBalance;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public double getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(double overdueFine) {
        this.overdueFine = overdueFine;
    }

    public boolean isOverdueMark() {
        return overdueMark;
    }

    public void setOverdueMark(boolean overdueMark) {
        this.overdueMark = overdueMark;
    }

    public int getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(int overdueTime) {
        this.overdueTime = overdueTime;
    }

    public double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public String getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(String repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public int getButtonDisabled() {
        return buttonDisabled;
    }

    public void setButtonDisabled(int buttonDisabled) {
        this.buttonDisabled = buttonDisabled;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"withdrawalNoStatus\":")
                .append(withdrawalNoStatus);
        sb.append(",\"isCancelWithdrawalNo\":")
                .append(isCancelWithdrawalNo);
        sb.append(",\"isBeingProcessed\":")
                .append(isBeingProcessed);
        sb.append(",\"lastTimeBorrow\":")
                .append(lastTimeBorrow);
        sb.append(",\"amount\":")
                .append(amount);
        sb.append(",\"availableBalance\":")
                .append(availableBalance);
        sb.append(",\"creditBalance\":")
                .append(creditBalance);
        sb.append(",\"hasUseBalance\":")
                .append(hasUseBalance);
        sb.append(",\"loanTime\":\"")
                .append(loanTime).append('\"');
        sb.append(",\"overdueFine\":")
                .append(overdueFine);
        sb.append(",\"overdueMark\":")
                .append(overdueMark);
        sb.append(",\"overdueTime\":")
                .append(overdueTime);
        sb.append(",\"repaymentAmount\":")
                .append(repaymentAmount);
        sb.append(",\"repaymentTime\":\"")
                .append(repaymentTime).append('\"');
        sb.append(",\"creditStatus\":")
                .append(creditStatus);
        sb.append(",\"isPaid\":")
                .append(isPaid);
        sb.append(",\"isWithdrawal\":")
                .append(isWithdrawal);
        sb.append(",\"buttonDisabled\":")
                .append(buttonDisabled);
        sb.append(",\"bidId\":")
                .append(bidId);
        sb.append(",\"billId\":")
                .append(billId);
        sb.append(",\"hasWithdrawalCode\":")
                .append(hasWithdrawalCode);
        sb.append(",\"bidsStatus\":")
                .append(bidsStatus);
        sb.append('}');
        return sb.toString();
    }
}
