package com.sdjt.rzgatewaygov.gateway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stepinfo")
public class GatewayStepInfo {

    @Id
    @Column(name = "SID")
    String sid;
    String BUSID;
    String STEPNAME;
    String ACTORNAME;
    String STARTAT;
    String ENDAT;
    String YESNO;
    String DESCR;
    @Column(name="ISSUCCESS")
    String isSuccess;
    String MESSAGE;

    public String getSID() {
        return sid;
    }

    public String getSTEPNAME() {
        return STEPNAME;
    }

    public void setSTEPNAME(String STEPNAME) {
        this.STEPNAME = STEPNAME;
    }

    public String getACTORNAME() {
        return ACTORNAME;
    }

    public void setACTORNAME(String ACTORNAME) {
        this.ACTORNAME = ACTORNAME;
    }

    public String getSTARTAT() {
        return STARTAT;
    }

    public void setSTARTAT(String STARTAT) {
        this.STARTAT = STARTAT;
    }

    public String getENDAT() {
        return ENDAT;
    }

    public void setENDAT(String ENDAT) {
        this.ENDAT = ENDAT;
    }

    public String getYESNO() {
        return YESNO;
    }

    public void setYESNO(String YESNO) {
        this.YESNO = YESNO;
    }

    public String getDESCR() {
        return DESCR;
    }

    public void setDESCR(String DESCR) {
        this.DESCR = DESCR;
    }

    public void setSID(String SID) {
        this.sid = SID;
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

    @Override
    public String toString() {
        return "GatewayStepInfo{" +
                "SID='" + sid + '\'' +
                ", BUSID='" + BUSID + '\'' +
                ", STEPNAME='" + STEPNAME + '\'' +
                ", ACTORNAME='" + ACTORNAME + '\'' +
                ", STARTAT='" + STARTAT + '\'' +
                ", ENDAT='" + ENDAT + '\'' +
                ", YESNO='" + YESNO + '\'' +
                ", DESCR='" + DESCR + '\'' +
                ", isSuccess='" + isSuccess + '\'' +
                ", MESSAGE='" + MESSAGE + '\'' +
                '}';
    }
}
