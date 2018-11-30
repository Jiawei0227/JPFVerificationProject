package edu.ut.verify.core.dataconstraint;

import java.util.HashMap;
import java.util.Map;

public class Formula {
    private Map<String,String[]> form;

    public Formula(){
        this.form = new HashMap<>();
    }

    /** ie: formula : a = b + c
     *  @param left the left part of formula
     *  @param right ...right .....
     *  left = a, right = [b,+,c];
     */
    public void putFormula(String left,String[] right){
        this.form.put(left,right);
    }

    public Map<String, String[]> getForm() {
        return form;
    }

    public void setForm(Map<String, String[]> form) {
        this.form = form;
    }

    public String toString(){
        String res = "";
        for(String str : form.keySet()){
            res = res+str+" = "+form.get(str)[0]+form.get(str)[1]+form.get(str)[2] +"\r\n";
        }

        return res;
    }
}
