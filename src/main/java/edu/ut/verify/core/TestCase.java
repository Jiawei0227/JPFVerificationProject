package edu.ut.verify.core;

import java.util.HashMap;
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
}
