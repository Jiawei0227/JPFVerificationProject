package edu.ut.verify.statechart2java;

/**
 * Created by Jerry Wang on 2018/11/5.
 */

import edu.ut.verify.core.Event;
import edu.ut.verify.core.State;
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

	public static StateChart parser(File f){
		// return object
		StateChart stateChart = new StateChart();

		ArrayList<State> allStates = new ArrayList<State>();
		ArrayList<Event> allEvents = new ArrayList<Event>();

		//TODO Your parser code goes here
		// you should implement the code below
		//stateChart.setStartState(startState);
		//stateChart.setStartState(endState);
		// add all the states and events inside the list


		// populate the events
		stateChart.setStateList(allStates);
		stateChart.setEventList(allEvents);

		return stateChart;
	}

	
	public static void parser() {
		
		try {
				
			File inputFile = new File("/Users/irenezhang/Documents/school/Verification/sample.xmi");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	        NodeList nList = doc.getElementsByTagName("region");		
	        Node nNode = nList.item(0);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            Element eElement = (Element) nNode;
            System.out.println("region : " 
               + eElement.getAttribute("xmi:id"));
            NodeList vertexList = eElement.getElementsByTagName("subvertex");
            Element ee = (Element) vertexList.item(0);
            System.out.println("subvertex : " 
                    + ee.getAttribute("xmi:id"));
            System.out.println("subvertex : " 
                    + ee.getAttribute("name"));
            /*
             * If the state/transition has no name
             * if (ee.getAttribute("name").length() == 0) {
            	System.out.println("name is null");
             */
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
