package edu.ut.verify.core;


public class SelfCircle {
    /**
     * if a sequence doesn't contain tranName,
     * using this boundary replace original boundary
     */
    private String tranName;
    private String varName;
    private String[] boundary;

    public SelfCircle(String tranName, String varName,String[] boundary){
        this.boundary = boundary;
        this.tranName = tranName;
        this.varName = varName;
    }

    public String getTranName() {
        return tranName;
    }

    public void setTranName(String tranName) {
        this.tranName = tranName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String[] getBoundary() {
        return boundary;
    }

    public void setBoundary(String[] boundary) {
        this.boundary = boundary;
    }
}
