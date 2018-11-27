package edu.ut.verify.core;

import java.util.Map;

public class TestCase {
    private Sequence sequence;
    private Map<String,Integer> values;
    private boolean valid;

    public TestCase(boolean valid, Sequence sequence,Map<String,Integer> values){
        this.sequence = sequence;
        this.valid = valid;
        this.values = values;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public Map<String, Integer> getValues() {
        return values;
    }

    public void setValues(Map<String, Integer> values) {
        this.values = values;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String toString(){
        String res = "";

        if(!valid)
            return "error path";
        else{
            if(values != null){
                for(String str : values.keySet()){
                    res = res +" "+ str +" = "+ values.get(str).toString();
                }
            }
            else
                res = "NO Input";
        }

        return res;
    }
}
