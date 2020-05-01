package com.mmkj.usercenter.model.entity;

public class CreditManagerEntity {
    /**
     * auditStatus : 2
     * creditBalance : 3500.0
     * currentIndex : 2
     * cashDeposit : null
     * feeProcedurePay : null
     * feeProcedureRepay : null
     * repayAmount : null
     * isPaid : false
     * isPromote : false
     * isVa : false
     * hasWithdrawalCode : true
     * isRefund : true
     * isDebt : false
     * payments : null
     */

    private int auditStatus;
    private double creditBalance;
    private int currentIndex;

    private boolean isPaid;
    private boolean isWithdrawal;
    private boolean isVa;
    private boolean hasWithdrawalCode;
    private boolean isRefund;
    private boolean isDebt;

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }


    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
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

    public boolean isVa() {
        return isVa;
    }

    public void setVa(boolean va) {
        isVa = va;
    }

    public boolean isRefund() {
        return isRefund;
    }

    public void setRefund(boolean refund) {
        isRefund = refund;
    }

    public boolean isDebt() {
        return isDebt;
    }

    public void setDebt(boolean debt) {
        isDebt = debt;
    }

    public boolean isIsVa() {
        return isVa;
    }

    public void setIsVa(boolean isVa) {
        this.isVa = isVa;
    }

    public boolean isHasWithdrawalCode() {
        return hasWithdrawalCode;
    }

    public void setHasWithdrawalCode(boolean hasWithdrawalCode) {
        this.hasWithdrawalCode = hasWithdrawalCode;
    }

    public boolean isIsRefund() {
        return isRefund;
    }

    public void setIsRefund(boolean isRefund) {
        this.isRefund = isRefund;
    }

    public boolean isIsDebt() {
        return isDebt;
    }

    public void setIsDebt(boolean isDebt) {
        this.isDebt = isDebt;
    }


}
