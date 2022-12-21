package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class XMLParser {
    public static void importObstacles(String file) throws IOException {

        if(!file.endsWith(".xml")){
            throw new FileNotFoundException();
        }

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringElementContentWhitespace(true);
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList obstacles = doc.getElementsByTagName("Obstacle");


            for(int o = 0; o<obstacles.getLength();o++){
                Element el = (Element) obstacles.item(o);
                String nm = el.getAttribute("Name");
                Number height = Integer.valueOf(el.getAttribute("Height"));
                Number blast = Integer.valueOf(el.getAttribute("Blast"));


                ObstacleWrapper obstacle = new ObstacleWrapper(nm,new HashMap<>(),height,blast);

                PredefinedObstacles.add(obstacle);
            }



        }catch(Exception e){
            e.printStackTrace();
            throw new IOException();
        }
    }

	public static void saveDataToFileAsXML(File file,Model m) throws FileNotFoundException, IOException {
        if(!file.getAbsolutePath().endsWith(".xml")) {
            throw new FileNotFoundException();
        }



		try {

			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();

			// create model
			//Element model = doc.createElement("model");
			//doc.appendChild(model);

			//create airport plural
			Element multiAirports = doc.createElement("Airports");
			doc.appendChild(multiAirports);

			for(Airport airportObject:m.getAllAirports()) {


				//create airport
				Element airp = doc.createElement("Airport");
				//create attributes of airport
				Attr resa = doc.createAttribute("RESA");
				resa.setNodeValue(airportObject.getResa().toString());
				Attr strip = doc.createAttribute("StripEnd");
				strip.setNodeValue(airportObject.getStripEnd().toString());
				Attr nm = doc.createAttribute("Name");
				nm.setNodeValue(airportObject.getName());


				airp.setAttributeNode(nm);
				airp.setAttributeNode(resa);
				airp.setAttributeNode(strip);
				//add airport as child of model
				multiAirports.appendChild(airp);


				//create runways (plural) to hold many runway objects
				Element Runways = doc.createElement("Runways");
				airp.appendChild(Runways);





				//for all the runways associated with one airport
				for(Runway r :airportObject.getRunways()) {
					//add specific runway
					Element newRun = doc.createElement("Runway");
					//add to parent class
					Runways.appendChild(newRun);

                    Attr name = doc.createAttribute("Name");
                    name.setNodeValue(r.getName());
                    newRun.setAttributeNode(name);

					//add LogicalRunway plural class
					Element lrs = doc.createElement("LogicalRunways");
					//add to the specific runway it belongs to
					newRun.appendChild(lrs);
					//add all the logical runways
					for(LogicalRunway lr: r.getLogicalRunways().values()) {
						Element newLR = doc.createElement("LogicalRunway");


						Attr asda = doc.createAttribute("ASDA");
						asda.setNodeValue(lr.getASDA().toString());

						Attr clearway = doc.createAttribute("Clearway");
						clearway.setNodeValue(lr.getClearway().toString());

						Attr displacedThreshold = doc.createAttribute("DisplacedThreshold");
						displacedThreshold.setNodeValue(lr.getDisplacedThreshold().toString());

						Attr heading = doc.createAttribute("Heading");
						String headingval = lr.getHeading().toString();
						if(headingval.length()<2){
						    headingval = "0"+headingval;
                        }

						heading.setNodeValue(headingval);

						Attr lda = doc.createAttribute("LDA");
						lda.setNodeValue(lr.getLDA().toString());

						Attr letter = doc.createAttribute("Letter");
						letter.setNodeValue(lr.getLetter().toString());

						Attr stopway = doc.createAttribute("Stopway");
						stopway.setNodeValue(lr.getStopway().toString());

						Attr toda = doc.createAttribute("TODA");
						toda.setNodeValue(lr.getTODA().toString());

						Attr tora = doc.createAttribute("TORA");
						tora.setNodeValue(lr.getTORA().toString());


						newLR.setAttributeNode(asda);
						newLR.setAttributeNode(clearway);
						newLR.setAttributeNode(displacedThreshold);
						newLR.setAttributeNode(heading);
						newLR.setAttributeNode(lda);
						newLR.setAttributeNode(letter);
						newLR.setAttributeNode(stopway);
						newLR.setAttributeNode(toda);
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
						    if(r.getObstacle().getDistanceFromCentre() != null){
                                obs.setAttribute("DistanceFromCentre", r.getObstacle().getDistanceFromCentre().toString());
                            }

						}
						obs.setAttribute("BlastProtection", r.getObstacle().getBlastProtection().toString());
					}
				}
			}
			//model.appendChild(multiAirports);


			// create the xml file
			//transform the DOM Object to an XML File
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");

			DOMSource domSource = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(file);

			transformer.transform(domSource, streamResult);



		} catch (ParserConfigurationException pce) {
			//System.out.println("pcs");
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			//System.out.println("tfe");
			tfe.printStackTrace();
		} catch (Exception e) {
			//System.out.println("other");
		}

	}
	
	public static void saveDataToFileAsXML(String file,Model m) throws IOException {
		File f = new File(file);
		saveDataToFileAsXML(f,m);
	}
	
	/*
	 * partially adapted from https://www.javatpoint.com/how-to-read-xml-file-in-java 
	 */
	public static Model getXMLDataFromFile(String file) throws FileNotFoundException, IOException {

        if(!file.endsWith(".xml")) {
            throw new FileNotFoundException();
        }
		try  {
			//an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringElementContentWhitespace(true);

			//an instance of builder to parse the specified xml file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.normalize();
			Model mod = new Model();


			Element m = doc.getDocumentElement();
			NodeList airports = m.getChildNodes();



            ArrayList<Airport> airportsout = new ArrayList<>();
			for(int a = 0; a<airports.getLength();a++) {
                Airport air = new Airport();
				Element airportElement = (Element) airports.item(a);

				String nm = airports.item(a).getAttributes().item(0).getNodeValue();
				Number resa = Integer.parseInt(airports.item(a).getAttributes().item(1).getNodeValue());
				Number strip = Integer.parseInt(airportElement.getAttributes().item(2).getNodeValue());

				air.setName(nm);
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

						String name = runwayElement.getAttributes().item(0).getNodeValue();
						HashMap<String,LogicalRunway> logics = new HashMap<String,LogicalRunway>();
						Obstacle newObs = null;
						NodeList logRuns = runwayElement.getElementsByTagName("LogicalRunways");
						for(int d = 0;d<logRuns.getLength();d++) {

							Element logRunsElement = (Element) logRuns.item(d);
							NodeList logRun = logRunsElement.getChildNodes();

							for(int e = 0;e<logRun.getLength();e++) {

								Element logicalRunway = (Element) logRun.item(e);
								LogicalRunway log;

								String lda = logicalRunway.getAttributes().item(4).getNodeValue();
								String tora = logicalRunway.getAttributes().item(8).getNodeValue();
								String toda = logicalRunway.getAttributes().item(7).getNodeValue();
								String asda = logicalRunway.getAttributes().item(0).getNodeValue();
								String heading = logicalRunway.getAttributes().item(3).getNodeValue();
								Character letter = logicalRunway.getAttributes().item(5).getNodeValue().toCharArray()[0];
								String com = heading +letter.toString();
								String stopway = logicalRunway.getAttributes().item(6).getNodeValue();
								String clearway = logicalRunway.getAttributes().item(1).getNodeValue();
								String displacedThreshold = logicalRunway.getAttributes().item(2).getNodeValue();

								log = new LogicalRunway(tora,  toda,  asda,  lda,  heading,  letter,  stopway,  clearway,  displacedThreshold);

								logics.put(com, log);



							}
						}

						NodeList obs = runwayElement.getElementsByTagName("Obstacle");
						for(int o = 0; o<obs.getLength();o++) {
							Element obsEle = (Element) obs.item(o);

							if(obsEle.getAttributes().getLength() == 3) {
								HashMap<String,Number> position = new HashMap<String,Number>();
								String hashDump = obsEle.getAttributes().item(2).getNodeValue();

								String[] pairs = hashDump.split(", ");
								pairs[0] = pairs[0].replace("{", "").replace("}", "");
								pairs[pairs.length-1] = pairs[pairs.length-1].replace("{", "").replace("}", "");


								for(String s:pairs) {
									String[] vals = s.split("=");
									position.put(vals[0], Integer.parseInt(vals[1]));
								}


								Number height = Integer.parseInt(obsEle.getAttributes().item(1).getNodeValue());
								Number blastProtection = Integer.parseInt(obsEle.getAttributes().item(0).getNodeValue());

								newObs = new Obstacle(position,height,blastProtection);
							} else {
                                HashMap<String,Number> position = new HashMap<String,Number>();
                                String hashDump = obsEle.getAttributes().item(3).getNodeValue();

                                String[] pairs = hashDump.split(", ");
                                pairs[0] = pairs[0].replace("{", "").replace("}", "");
                                pairs[pairs.length-1] = pairs[pairs.length-1].replace("{", "").replace("}", "");


                                for(String s:pairs) {
                                    String[] vals = s.split("=");
                                    position.put(vals[0], Integer.parseInt(vals[1]));
                                }


                                Number height = Integer.parseInt(obsEle.getAttributes().item(2).getNodeValue());
                                Number blastProtection = Integer.parseInt(obsEle.getAttributes().item(0).getNodeValue());

								Number distanceFromCentre = Integer.parseInt(obsEle.getAttributes().item(1).getNodeValue());


								newObs = new Obstacle(position,height,blastProtection,distanceFromCentre);
							}

						}


						newRunway = new Runway(name,logics,newObs);

						runwaysToAdd.add(newRunway);
					}



				}

				air.addRunways(runwaysToAdd);
                airportsout.add(air);


			}
			mod.addAirports(airportsout);
			mod.setCurrentAirportAndRunway(airportsout.get(0),airportsout.get(0).getRunways().get(0));




			return mod;

		} catch(Exception e) {
		    e.printStackTrace();
			throw new IOException();
		}
	}

    public static ArrayList<Airport> getAirportXMLDataFromFile(String file) throws FileNotFoundException, IOException {

        if(!file.endsWith(".xml")) {
            throw new FileNotFoundException();
        }
        try  {
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Model mod = new Model();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList airports = doc.getElementsByTagName("Airport");

            ArrayList<Airport> airportsout = new ArrayList<>();
            for(int a = 0; a<airports.getLength();a++) {
                Airport air = new Airport();
                Element airportElement = (Element) airports.item(a);

                String nm = airports.item(a).getAttributes().item(0).getNodeValue();
                Number resa = Integer.parseInt(airports.item(a).getAttributes().item(1).getNodeValue());
                Number strip = Integer.parseInt(airportElement.getAttributes().item(2).getNodeValue());

                air.setName(nm);
                air.setResa(resa);
                air.setStripEnd(strip);

                ArrayList<Runway> runwaysToAdd = new ArrayList<Runway>();

                NodeList Runways = airportElement.getElementsByTagName("Runway");

                for(int b = 0;b<Runways.getLength();b++) {

                    Element RunwaysElement = (Element) Runways.item(b);
                    NodeList logicals = RunwaysElement.getElementsByTagName("LogicalRunway");
                    Runway newRunway;
                    String name = RunwaysElement.getAttributes().item(0).getNodeValue();
                    HashMap<String, LogicalRunway> logics = new HashMap<String, LogicalRunway>();
                    Obstacle newObs = null;

                    for(int c = 0;c<logicals.getLength();c++) {
                        Element logical = (Element) logicals.item(c);
                        LogicalRunway log;
                        String lda = logical.getAttributes().item(4).getNodeValue();
                        String tora = logical.getAttributes().item(8).getNodeValue();
                        String toda = logical.getAttributes().item(7).getNodeValue();
                        String asda = logical.getAttributes().item(0).getNodeValue();
                        String heading = logical.getAttributes().item(3).getNodeValue();
                        Character letter = logical.getAttributes().item(5).getNodeValue().toCharArray()[0];
                        String com = heading + letter.toString();
                        String stopway = logical.getAttributes().item(6).getNodeValue();
                        String clearway = logical.getAttributes().item(1).getNodeValue();
                        String displacedThreshold = logical.getAttributes().item(2).getNodeValue();

                        if(heading.length()<2){
                            throw new Exception("Heading length less than 2 digits");
                        }

                        log = new LogicalRunway(tora, toda, asda, lda, heading, letter, stopway, clearway, displacedThreshold);

                        logics.put(com, log);
                    }

                    NodeList obs = RunwaysElement.getElementsByTagName("Obstacle");

                    Element obsEle = (Element) obs.item(0);
                    try {
                        if (obsEle.getAttributes().getLength() == 3) {
                            throw new Exception("Obstacle Attributes not properly formatted in XML");

                        } else {
                            HashMap<String, Number> position = new HashMap<String, Number>();
                            String hashDump = obsEle.getAttributes().item(3).getNodeValue();

                            String[] pairs = hashDump.split(", ");
                            pairs[0] = pairs[0].replace("{", "").replace("}", "");
                            pairs[pairs.length - 1] = pairs[pairs.length - 1].replace("{", "").replace("}", "");


                            for (String s : pairs) {
                                String[] vals = s.split("=");
                                position.put(vals[0], Integer.parseInt(vals[1]));
                            }


                            Number height = Integer.parseInt(obsEle.getAttributes().item(2).getNodeValue());
                            Number blastProtection = Integer.parseInt(obsEle.getAttributes().item(0).getNodeValue());

                            Number distanceFromCentre = Integer.parseInt(obsEle.getAttributes().item(1).getNodeValue());


                            newObs = new Obstacle(position, height, blastProtection, distanceFromCentre);


                        }
                        newRunway = new Runway(name,logics,newObs);
                    }catch (NullPointerException e){
                        newRunway = new Runway(name,logics);
                    }

                    for(Runway run: runwaysToAdd){
                        if(run.getName().equals(air.getName())){
                            throw new Exception("Runways with same name present in XML file\nPlease name them differently.");
                        }
                    }
                    runwaysToAdd.add(newRunway);



                }

                air.addRunways(runwaysToAdd);

                for(Airport airpt: airportsout){
                    if(airpt.getName().equals(air.getName())){
                        throw new Exception("Airports with same name present in XML file\nPlease name them differently.");
                    }
                }
                airportsout.add(air);


            }


            return airportsout;

        } catch(Exception e) {

            throw new IOException(e.getMessage());
        }
    }
		
}

