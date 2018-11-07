package edu.ut.verify.statechart2java;

/**
 * Created by Jerry Wang on 2018/11/5.
 */

import edu.ut.verify.core.event.Event;
import edu.ut.verify.core.state.NState;
import edu.ut.verify.core.state.State;
import edu.ut.verify.core.StateChart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMIPaser {

    public static StateChart parser(File f) {
        /**
        // return object
        StateChart stateChart = new StateChart();

        ArrayList<State> allStates = new ArrayList<State>();
        ArrayList<Event> allEvents = new ArrayList<Event>();

        //TODO Your parser code goes here
        // you should implement the code below
        //stateChart.setStartState(startState);
        //stateChart.setStartState(endState);
        // add all the states and events inside the list
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);
            doc.getDocumentElement().normalize();

            //get the initial region
            NodeList nList = doc.getElementsByTagName("region");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;

            //all states under current region
            Element elem;
            NodeList vertexList = eElement.getElementsByTagName("subvertex");
            for (int i = 0; i < vertexList.getLength(); i++) {
                elem = (Element) vertexList.item(i);
                State state = new NState();
                // If the state has no name
                if (elem.getAttribute("name").length() != 0) {
                    state.setName(elem.getAttribute("name"));
                }
                state.setId(elem.getAttribute("xmi:id"));
                allStates.add(state);
            }

            //all events under current region
            vertexList = eElement.getElementsByTagName("transition");
            for (int i = 0; i < vertexList.getLength(); i++) {
                elem = (Element) vertexList.item(i);
                Event event = new Event();
                // If the transition has no name
                Node ownedMember = elem.getElementsByTagName("ownedMember").item(0);
                if (ownedMember.getAttributes("name").length() != 0) {
                    event.setName(elem.getAttribute("name"));
                }
                event.setID(elem.getAttribute("xmi:id"));
                String start = elem.getAttribute("source");
                String end = elem.getAttribute("target");
                State startState = allStates.getState(start);
                State endState = allStates.getState(end);
                event.setStartState(startState);
                event.setEndState(endState);
                allEvents.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // populate the events
        stateChart.setStateList(allStates);
        stateChart.setEventList(allEvents);

        return stateChart;
         **/
        return null;
    }

    public static void main(String[] args) {
        String fileName = "/Users/irenezhang/Documents/school/Verification/SampleXMI/sample.xmi";
        File inputFile = new File(fileName);
        parser(inputFile);
    }



}
