package edu.ut.verify.core;


public class SelfCircle {
    /**
     * if a sequence doesn't contain tranName,
     * using this boundary replace original boundary
     */
    private String tranName;
    private String varName;
    private int[] boundary;

    public SelfCircle(String tranName, String varName){
        this.boundary = new int[2];
        this.boundary[0] = -200;
        this.boundary[1] = 200;
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

    public void setHigh(int high){
        this.boundary[1] = high;
    }

    public void setLow(int low){
        this.boundary[0] = low;
    }

    public int[] getBoundary() {
        return boundary;
    }

    public void setBoundary(int[] boundary) {
        this.boundary = boundary;
    }

    public String toString(){
        if(this.varName != null)
            return ""+boundary[0]+" < "+varName+" < "+boundary[1];
        else
            return "";
    }
}
