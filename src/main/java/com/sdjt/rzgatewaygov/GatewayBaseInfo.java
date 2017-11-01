package com.sdjt.rzgatewaygov;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "baseinfo")
//@NamedQuery(name= "findByIsSuccess", query = "select t from GatewayBaseInfo t where t.ISSUCCESS = ?1")
public class GatewayBaseInfo {

    @Id
    String SID;
    String APPID;
    String APPPWD;
    String ITEMCODE;
    String YWTYPE;
    String APPLICANT;
    String APPLICANTMOBILE;
    String APPLICANTIDCARD;
    String APPLICANTSEX;
    String APPLICANTCOMPANY;
    String APPLICANTADDRESS;
    String APPLICANTNATION;
    String APPLICANTDOB;
    String SLR;
    String SQSJ;
    String SLSJ;
    String BJSJ;
    String CNBJSJ;
    String BLSTTS;
    @Column(name = "ISSUCCESS")
    String isSuccess;
    String MESSAGE;
    String BUSID;

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public String getAPPPWD() {
        return APPPWD;
    }

    public void setAPPPWD(String APPPWD) {
        this.APPPWD = APPPWD;
    }

    public String getITEMCODE() {
        return ITEMCODE;
    }

    public void setITEMCODE(String ITEMCODE) {
        this.ITEMCODE = ITEMCODE;
    }

    public String getYWTYPE() {
        return YWTYPE;
    }

    public void setYWTYPE(String YWTYPE) {
        this.YWTYPE = YWTYPE;
    }

    public String getAPPLICANT() {
        return APPLICANT;
    }

    public void setAPPLICANT(String APPLICANT) {
        this.APPLICANT = APPLICANT;
    }

    public String getAPPLICANTMOBILE() {
        return APPLICANTMOBILE;
    }

    public void setAPPLICANTMOBILE(String APPLICANTMOBILE) {
        this.APPLICANTMOBILE = APPLICANTMOBILE;
    }

    public String getAPPLICANTIDCARD() {
        return APPLICANTIDCARD;
    }

    public void setAPPLICANTIDCARD(String APPLICANTIDCARD) {
        this.APPLICANTIDCARD = APPLICANTIDCARD;
    }

    public String getAPPLICANTSEX() {
        return APPLICANTSEX;
    }

    public void setAPPLICANTSEX(String APPLICANTSEX) {
        this.APPLICANTSEX = APPLICANTSEX;
    }

    public String getAPPLICANTCOMPANY() {
        return APPLICANTCOMPANY;
    }

    public void setAPPLICANTCOMPANY(String APPLICANTCOMPANY) {
        this.APPLICANTCOMPANY = APPLICANTCOMPANY;
    }

    public String getAPPLICANTADDRESS() {
        return APPLICANTADDRESS;
    }

    public void setAPPLICANTADDRESS(String APPLICANTADDRESS) {
        this.APPLICANTADDRESS = APPLICANTADDRESS;
    }

    public String getAPPLICANTNATION() {
        return APPLICANTNATION;
    }

    public void setAPPLICANTNATION(String APPLICANTNATION) {
        this.APPLICANTNATION = APPLICANTNATION;
    }

    public String getAPPLICANTDOB() {
        return APPLICANTDOB;
    }

    public void setAPPLICANTDOB(String APPLICANTDOB) {
        this.APPLICANTDOB = APPLICANTDOB;
    }

    public String getSLR() {
        return SLR;
    }

    public void setSLR(String SLR) {
        this.SLR = SLR;
    }

    public String getSQSJ() {
        return SQSJ;
    }

    public void setSQSJ(String SQSJ) {
        this.SQSJ = SQSJ;
    }

    public String getSLSJ() {
        return SLSJ;
    }

    public void setSLSJ(String SLSJ) {
        this.SLSJ = SLSJ;
    }

    public String getBJSJ() {
        return BJSJ;
    }

    public void setBJSJ(String BJSJ) {
        this.BJSJ = BJSJ;
    }

    public String getCNBJSJ() {
        return CNBJSJ;
    }

    public void setCNBJSJ(String CNBJSJ) {
        this.CNBJSJ = CNBJSJ;
    }

    public String getBLSTTS() {
        return BLSTTS;
    }

    public void setBLSTTS(String BLSTTS) {
        this.BLSTTS = BLSTTS;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getBUSID() {
        return BUSID;
    }

    public void setBUSID(String BUSID) {
        this.BUSID = BUSID;
    }
}
