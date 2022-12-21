package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.w3c.dom.Node;  
//import org.w3c.dom.NodeList;  

public class XMLParser {	
	
	public static Model getXMLDataFromFile(String file) throws FileNotFoundException, IOException, InvalidClassException{
		throw new FileNotFoundException();
	}
	/*
	 * partially adapted from https://www.javatpoint.com/how-to-read-xml-file-in-java 
	public static Model getXMLDataFromFile(String file) throws FileNotFoundException, IOException, InvalidClassException{
		file = checkExtension(file);
		try  {   
			//an instance of factory that gives a document builder  
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			//an instance of builder to parse the specified xml file  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize(); 
			
			System.out.println(doc);
			
			
			
			Element m = (Element) doc.getChildNodes().item(0);
			NodeList airport = m.getChildNodes();
			
			Airport air = new Airport();
			
			for(int a = 0; a<airport.getLength();a++) {
				Element airportElement = (Element) airport.item(a);
				Number resa = Integer.getInteger(airportElement.getAttribute("RESA"));
				Number strip = Integer.getInteger(airportElement.getAttribute("StripEnd"));
				
				System.out.println("resa = "+ resa);
				System.out.println("strip = "+ strip);
				System.out.println(airportElement);
				
				
				air.setResa(resa);
				air.setStripEnd(strip);
				ArrayList<Runway> runwaysToAdd = new ArrayList<Runway>();
				
				NodeList Runways = airportElement.getChildNodes();
				for(int b = 0;b<Runways.getLength();b++) {
					Element RunwaysElement = (Element) Runways.item(b);
					NodeList runway = RunwaysElement.getChildNodes();
					
					for(int c = 0;c<runway.getLength();c++) {
						Element runwayElement = (Element) runway.item(c);
						Runway newRunway;
						HashMap<String,LogicalRunway> logics = new HashMap<String,LogicalRunway>();
						Obstacle newObs = null;
						NodeList logRuns = runwayElement.getElementsByTagName("LogicalRunways");
						for(int d = 0;d<logRuns.getLength();d++) {
							Element logRunsElement = (Element) logRuns.item(d);
							NodeList logRun = logRunsElement.getChildNodes();
							for(int e = 0;e<logRun.getLength();e++) {
								Element logicalRunway = (Element) logRun;
								LogicalRunway log;
								
						        Number lda = Integer.getInteger(logicalRunway.getAttribute("LDA"));
						        Number tora = Integer.getInteger(logicalRunway.getAttribute("TORA"));
						        Number toda = Integer.getInteger(logicalRunway.getAttribute("TODA"));
						        Number asda = Integer.getInteger(logicalRunway.getAttribute("ASDA"));
						        Number heading = Integer.getInteger(logicalRunway.getAttribute("Heading"));
						        Character letter = logicalRunway.getAttribute("Letter").toCharArray()[0];
						        String com = heading +letter.toString();
						        Number stopway = Integer.getInteger(logicalRunway.getAttribute("Stopway"));
						        Number clearway = Integer.getInteger(logicalRunway.getAttribute("Clearway"));
						        Number displacedThreshold = Integer.getInteger(logicalRunway.getAttribute("DisplacedThreshold"));
								
						        log = new LogicalRunway(tora,  toda,  asda,  lda,  heading,  letter,  stopway,  clearway,  displacedThreshold);
						        logics.put(com, log);
								
								
							}
						}
						
						
						NodeList obs = runwayElement.getElementsByTagName("Obstacle");
						for(int o = 0; o<obs.getLength();o++) {
							Element obsEle = (Element) obs.item(o);
							
							
							if(obsEle.hasAttribute("ObstaclePosition")) {
								HashMap<String,Number> position = new HashMap<String,Number>();
								String hashDump = obsEle.getAttribute("ObstaclePosition");
								String[] pairs = hashDump.split(",");
								for(String s:pairs) {
									String[] vals = s.split("=");
									position.put(vals[0], Integer.getInteger(vals[1]));
								}
								
								Number height = Integer.getInteger(obsEle.getAttribute("Height"));
								Number blastProtection = Integer.getInteger(obsEle.getAttribute("BlastProtection"));
							
								newObs = new Obstacle(position,height,blastProtection);
							} else if(obsEle.hasAttribute("DistanceFromLeft")) {
								Number height = Integer.getInteger(obsEle.getAttribute("Height"));
								Number distanceFromLeft = Integer.getInteger(obsEle.getAttribute("DistanceFromLeft"));
								Number distanceFromRight = Integer.getInteger(obsEle.getAttribute("DistanceFromRight"));
								Number distanceFromCentre = Integer.getInteger(obsEle.getAttribute("DistanceFromCentre"));
								Number blastProtection = Integer.getInteger(obsEle.getAttribute("BlastProtection"));
								
								newObs = new Obstacle(height, distanceFromLeft, distanceFromRight, distanceFromCentre, blastProtection);
							}
								
							}
						
						newRunway = new Runway(logics,newObs);
						runwaysToAdd.add(newRunway);
						}
						
					
					
				}
				
				
				air.addRunways(runwaysToAdd);
				
			}
			
			Model mod = new Model();
			mod.setAirport(air);
			mod.addRunway(air.getRunways().get(0));
			
			return mod;
			
		} catch(Exception e) {
			throw new IOException();
		}
		
	}
	*/
	
	//partially adapted from https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
	public static void saveDataToFileAsXML(String file,Model m) throws FileNotFoundException, IOException {
			System.out.println("Saving");
			
			file = checkExtension(file);
			try {
				 
	            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = df.newDocumentBuilder();
	            Document doc = db.newDocument();
	 
	            // create model
	            Element model = doc.createElement("model");
	            doc.appendChild(model);
	            
	            //create airport
	            Element airp = doc.createElement("Airport");
	            //create attributes of airport
	            Attr resa = doc.createAttribute("RESA");
	            resa.setNodeValue(m.getAirport().getResa().toString());
	            Attr strip = doc.createAttribute("StripEnd");
	            strip.setNodeValue(m.getAirport().getStripEnd().toString());
	            airp.setAttributeNode(resa);
	            airp.setAttributeNode(strip);
	            //add airport as child of model
	            model.appendChild(airp);
	            
	            
	            //create runways (plural) to hold many runway objects
	            Element Runways = doc.createElement("Runways");
	            airp.appendChild(Runways);
	            //for all the runways associated with one airport
	            for(Runway r :m.getRunways()) {
	            	//add specific runway
	            	Element newRun = doc.createElement("Runway");
	            	//add to parent class
	            	Runways.appendChild(newRun);
	            	
            		//add LogicalRunway plural class
           		 	Element lrs = doc.createElement("LogicalRunways");
           		 	//add to the specific runway it belongs to
           		 	newRun.appendChild(lrs);
	            	//add all the logical runways
	            	for(LogicalRunway lr: r.getLogicalRunways().values()) {
	            		Element newLR = doc.createElement("LogicalRunway");
	            		
	            		
	            		Attr asda = doc.createAttribute("ASDA");
	            	    asda.setNodeValue(lr.getASDA().toString());
	            	    newLR.setAttributeNode(asda);
	            	    Attr clearway = doc.createAttribute("Clearway");
	            	    clearway.setNodeValue(lr.getClearway().toString());
	            	    newLR.setAttributeNode(clearway);
	            	    Attr displacedThreshold = doc.createAttribute("DisplacedThreshold");
	            	    displacedThreshold.setNodeValue(lr.getDisplacedThreshold().toString());
	            	    newLR.setAttributeNode(displacedThreshold);
	            	    Attr heading = doc.createAttribute("Heading");
	            	    clearway.setNodeValue(lr.getHeading().toString());
	            	    newLR.setAttributeNode(heading);	            	    
	            	    Attr lda = doc.createAttribute("LDA");
	            	    lda.setNodeValue(lr.getLDA().toString());
	            	    newLR.setAttributeNode(lda);
	            	    Attr letter = doc.createAttribute("Letter");
	            	    clearway.setNodeValue(lr.getLetter().toString());
	            	    newLR.setAttributeNode(letter);
	            	    Attr stopway = doc.createAttribute("Stopway");
	            	    stopway.setNodeValue(lr.getStopway().toString());
	            	    newLR.setAttributeNode(stopway);
	            	    Attr toda = doc.createAttribute("TODA");
	            	    toda.setNodeValue(lr.getTODA().toString());
	            	    newLR.setAttributeNode(toda);
	            	    Attr tora = doc.createAttribute("TORA");
	            	    tora.setNodeValue(lr.getTORA().toString());
	            	    newLR.setAttributeNode(tora);
	            	    
	            		//add logical runway attri
	            		lrs.appendChild(newLR);
	            	}
	            	
	            	if(r.getObstacle() != null) {
	            	Element obs = doc.createElement("Obstacle");
	            	newRun.appendChild(obs);
	            	
	            	
	            	obs.setAttribute("Height", r.getObstacle().getHeight().toString());
	            	if(r.getObstacle().getObstaclePosition() != null) {
	            		obs.setAttribute("ObstaclePosition", r.getObstacle().getObstaclePosition().toString());
	            	} else if(r.getObstacle().getDistanceFromLeft() != null) {
	            		obs.setAttribute("DistanceFromLeft", r.getObstacle().getDistanceFromLeft().toString());
	            		obs.setAttribute("DistanceFromRight", r.getObstacle().getDistanceFromRight().toString());
	            		obs.setAttribute("DistanceFromCentre", r.getObstacle().getDistanceFromCentre().toString());
	            	}
	            	obs.setAttribute("BlastProtection", r.getObstacle().getBlastProtection().toString());
	            	}
	            }
	            
	           
	            // create the xml file
	            //transform the DOM Object to an XML File
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer transformer = tf.newTransformer();
	            DOMSource domSource = new DOMSource(doc);
	            StreamResult streamResult = new StreamResult(new File(file));
	 
	            //StreamResult result = new StreamResult(System.out);
	 
	            transformer.transform(domSource, streamResult);
	 
	            System.out.println("SAVED!");
	 
	        } catch (ParserConfigurationException pce) {
	        	System.out.println("pcs");
	            pce.printStackTrace();
	        } catch (TransformerException tfe) {
	        	System.out.println("tfe");
	            tfe.printStackTrace();
	        } catch (Exception e) {
	        	System.out.println("other");
	        }
		
	}
	
	private static String checkExtension(String s) {
		if(!s.contains(".xml")) {
			return (s + ".xml");
		} else { return s; }
	}
	
	

}
