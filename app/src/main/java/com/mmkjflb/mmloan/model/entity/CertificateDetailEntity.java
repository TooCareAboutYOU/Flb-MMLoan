package com.mmkjflb.mmloan.model.entity;

public class CertificateDetailEntity {


    /**
     * userId : 101904011007231710
     * bidId : 2087
     * billId : 2087
     * collectionBank : BCA
     * collectionAccountNumber : 0999-327818
     * owner : LIANLIAN GAO
     * payoutBank : T88856
     * payoutBankName : 78866
     * transferCertificate : http://adminimgflb.358fintech.com/FmODd3Wcr4UrOrunDJs3SBk3q3Mh
     */

    private long userId;
    private int bidId;
    private int billId;
    private String collectionBank;
    private String collectionAccountNumber;
    private String owner;
    private String payoutBank;
    private String payoutBankName;
    private String transferCertificate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public String getCollectionBank() {
        return collectionBank;
    }

    public void setCollectionBank(String collectionBank) {
        this.collectionBank = collectionBank;
    }

    public String getCollectionAccountNumber() {
        return collectionAccountNumber;
    }

    public void setCollectionAccountNumber(String collectionAccountNumber) {
        this.collectionAccountNumber = collectionAccountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPayoutBank() {
        return payoutBank;
    }

    public void setPayoutBank(String payoutBank) {
        this.payoutBank = payoutBank;
    }

    public String getPayoutBankName() {
        return payoutBankName;
    }

    public void setPayoutBankName(String payoutBankName) {
        this.payoutBankName = payoutBankName;
    }

    public String getTransferCertificate() {
        return transferCertificate;
    }

    public void setTransferCertificate(String transferCertificate) {
        this.transferCertificate = transferCertificate;
    }
}
