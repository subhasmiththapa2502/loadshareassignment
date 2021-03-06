package com.subhasmith.loadshare.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseIFSCDataAPI {
    @Expose
    @SerializedName("BRANCH")
    private String branch;
    @Expose
    @SerializedName("CENTRE")
    private String centre;
    @Expose
    @SerializedName("DISTRICT")
    private String district;
    @Expose
    @SerializedName("STATE")
    private String state;
    @Expose
    @SerializedName("ADDRESS")
    private String address;
    @Expose
    @SerializedName("CONTACT")
    private String contact;
    @Expose
    @SerializedName("MICR")
    private String micr;
    @Expose
    @SerializedName("UPI")
    private String upi;
    @Expose
    @SerializedName("RTGS")
    private String rtgs;
    @Expose
    @SerializedName("CITY")
    private String city;
    @Expose
    @SerializedName("NEFT")
    private String neft;
    @Expose
    @SerializedName("IMPS")
    private String imps;
    @Expose
    @SerializedName("BANK")
    private String bank;
    @Expose
    @SerializedName("BANKCODE")
    private String bankCode;
    @Expose
    @SerializedName("IFSC")
    private String ifsc;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMicr() {
        return micr;
    }

    public void setMicr(String micr) {
        this.micr = micr;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public String getRtgs() {
        return rtgs;
    }

    public void setRtgs(String rtgs) {
        this.rtgs = rtgs;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeft() {
        return neft;
    }

    public void setNeft(String neft) {
        this.neft = neft;
    }

    public String getImps() {
        return imps;
    }

    public void setImps(String imps) {
        this.imps = imps;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }
}
