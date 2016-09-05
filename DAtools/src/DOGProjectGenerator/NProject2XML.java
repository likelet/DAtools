package DOGProjectGenerator;

import java.awt.Color;
import java.io.*;
import java.util.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import java.text.DecimalFormat;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.*;


/**
 * <p>Project2XML.java</p> <p>Copyright (c) 2007-2011.The CUCKOO Workgroup, China</p>
 *
 * @author Ren Jian
 * @version 4.3
 */
public class NProject2XML {

    private final String DELIMIT = "#&%";
    private String filePath;
    public String VERSION = "DOG 2.0";
    private DecimalFormat df = new DecimalFormat("#0.0#");

    public NProject2XML(String filePath) {
        this.filePath = filePath;
    }

    public NProject2XML() {
        this.filePath = "gui/dog3/002.xml";
    }

    private Document buildDocument(NProject project) {
        Document doc = new Document();
        if (project != null) {
            LinkedList<NProtein> proteinList = project.getProteinList();
            int width = project.getWidth();
            int height = project.getHeight();
            float rate = project.getRate();

            Element projectElement = new Element("project");
            doc.setRootElement(projectElement);
            projectElement.setAttribute("version", VERSION);
            projectElement.setAttribute("mode", "nucleotide");
            projectElement.setAttribute("width", String.valueOf(width));
            projectElement.setAttribute("height", String.valueOf(height));
            projectElement.setAttribute("rate", df.format(rate).replaceAll(",", "."));

            for (Iterator<NProtein> it = proteinList.iterator(); it.hasNext();) {
                NProtein protein = it.next();
                projectElement.addContent(buildProtein(protein));
            }
        }
        return doc;
    }

    private Element buildProtein(NProtein protein) {
        Color color = protein.getColor();



        String parameter = protein.getParameter();
        String[] arr = parameter.split(DELIMIT);
        String start = arr[0];
        String startDirection = arr[1];
        String end = arr[2];
        String endDirection = arr[3];
        String height = arr[4];
        String size = arr[5];
        String gradient = arr[6];
        String texture = arr[7];
//         String strdash = arr[8];
//       String strokesize = arr[9];
        String proteinColorLineStr = arr[10];
        StringTokenizer ster = new StringTokenizer(proteinColorLineStr, ",");

        Color colorline = new Color(Integer.parseInt(ster.nextToken()), Integer.parseInt(ster.nextToken()),
                Integer.parseInt(ster.nextToken()));
        Element proteinElement = new Element("protein");
        proteinElement.setAttribute("vertical", String.valueOf(protein.getVertical()));
        proteinElement.setAttribute("horizontal", String.valueOf(protein.getHorizontal()));
        proteinElement.setAttribute("locked", String.valueOf(protein.isLocked()));

        //start
        Element proteinStartElement = new Element("start");
        proteinStartElement.setAttribute("direction", startDirection);
        proteinStartElement.addContent(start);

        //end
        Element proteinEndElement = new Element("end");
        proteinEndElement.setAttribute("direction", endDirection);
        proteinEndElement.addContent(end);

        //height
        Element proteinHeightElement = new Element("height");
        proteinHeightElement.addContent(height);
        proteinHeightElement.setAttribute("size", size);
        proteinHeightElement.setAttribute("color", color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        proteinHeightElement.setAttribute("colorline", colorline.getRed() + "," + colorline.getGreen() + "," + colorline.getBlue());

        proteinElement.addContent(proteinStartElement);
        proteinElement.addContent(proteinEndElement);
        proteinElement.addContent(proteinHeightElement);

        Element proteinGradientElement = new Element("gradient");
        Element proteinTextureElement = new Element("texture");
        proteinElement.addContent(proteinGradientElement);

        proteinElement.addContent(proteinTextureElement);
        Element proteinDashElement = new Element("dash");
        Element proteinStroikeSizeElement = new Element("linesize");

        proteinGradientElement.addContent(gradient);
        proteinTextureElement.addContent(texture);
        proteinDashElement.addContent(arr[8]);
        proteinStroikeSizeElement.addContent(arr[9]);
        proteinElement.addContent(proteinDashElement);
        proteinElement.addContent(proteinStroikeSizeElement);
        Element proteinFontElement = new Element("textfont");
        proteinFontElement.addContent("");
        proteinElement.addContent(proteinFontElement);
        LinkedList<NComponent> componentList = protein.getComponentList();
        for (Iterator<NComponent> it = componentList.iterator(); it.hasNext();) {
            NComponent component = it.next();
            if (component.getType() == ComponentEnum.Domain) {
                proteinElement.addContent(domainElement(component));
            } else if (component.getType() == ComponentEnum.Site) {
                proteinElement.addContent(siteElement(component));
            } else if (component.getType() == ComponentEnum.Note) {
                proteinElement.addContent(noteElement(component));
            } else if (component.getType() == ComponentEnum.Line) {
                proteinElement.addContent(lineElement(component));
            } else if (component.getType() == ComponentEnum.Mark) {
                proteinElement.addContent(markElement(component));
            } else if (component.getType() == ComponentEnum.Polygon) {
                proteinElement.addContent(polygonElement(component));
            } else if (component.getType() == ComponentEnum.Bracket) {
                proteinElement.addContent(bracketElement(component));
            }
        }
        return proteinElement;
    }

    private Element bracketElement(NComponent component) {

        String parameter = component.getParameter();
        StringTokenizer st = new StringTokenizer(parameter, DELIMIT);

        String strStrokeSize = st.nextToken();

        String sCenterx = String.valueOf((int) component.getCenterx());
        String sCentery = String.valueOf((int) component.getCentery());
        String sWide = String.valueOf((int) component.getWide());

        String sRotationAngle = String.valueOf((int) component.getRotationAngle());
        String strShapeType = component.getShapeType().name();

        Color color = component.getColor();

        Element bracketElement = new Element("component");
        bracketElement.setAttribute("type", "bracket");
        Element bracketStrokeSizeElement = new Element("strokesize");

        Element bracketCenterxElement = new Element("centerx");
        Element bracketCenteryElement = new Element("centery");
        Element bracketWideElement = new Element("wide");

        Element bracketRotationAngleElement = new Element("rotationAngle");

        Element bracketShapeTypeElement = new Element("shapetype");

        Element weightElement = new Element("weight");

        weightElement.setAttribute("color", color.getRed() + "," + color.getGreen() + "," + color.getBlue());

        bracketShapeTypeElement.addContent(strShapeType);
        bracketStrokeSizeElement.addContent(strStrokeSize);

        bracketCenterxElement.addContent(sCenterx);
        bracketCenteryElement.addContent(sCentery);
        bracketWideElement.addContent(sWide);
        bracketRotationAngleElement.addContent(sRotationAngle);

        bracketElement.addContent(weightElement);
        bracketElement.addContent(bracketStrokeSizeElement);
        bracketElement.addContent(bracketCenterxElement);
        bracketElement.addContent(bracketCenteryElement);
        bracketElement.addContent(bracketWideElement);
        bracketElement.addContent(bracketRotationAngleElement);
        bracketElement.addContent(bracketShapeTypeElement);
        return bracketElement;

    }

    private Element domainElement(NComponent component) {

        String parameter = component.getParameter();

        StringTokenizer st = new StringTokenizer(parameter, DELIMIT);

        String start = String.valueOf((int) component.getX());
        String startDrawType = st.nextToken();//0
        String end = String.valueOf((int) component.getX2());
        String endDrawType = st.nextToken();//1
        String name = st.nextToken();//2
        String nameDrawType = st.nextToken();//3
        String size = st.nextToken();//4

        String strHigh = String.valueOf((int) component.getiHigh());
        String strShapeType = component.getShapeType().name();
        String strStrokeSize = st.nextToken();//5
        String gradient = st.nextToken();//6
        String texture = st.nextToken();//7

        String sRotationAngle = String.valueOf((int) component.getRotationAngle());

        Element domainElement = new Element("component");
        Element domainStartElement = new Element("start");
        Element domainEndElement = new Element("end");
        Element domainNameElement = new Element("name");

        Element domainHighElement = new Element("high");
        Element domainShapeTypeElement = new Element("shapetype");

        Element domainStrokeSizeElement = new Element("strokesize");

        Element domainGradientElement = new Element("gradient");
        Element domainTextureElement = new Element("texture");

        Element polygonRotationAngleElement = new Element("rotationAngle");
        Element polygonDashElement = new Element("dash");
        Element polygonLeftDashElement = new Element("leftdash");
        Element polygonRightDashElement = new Element("rightdash");
        polygonLeftDashElement.addContent(String.valueOf(component.bProteinLDash));
        polygonRightDashElement.addContent(String.valueOf(component.bProteinRDash));
        domainElement.setAttribute("type", "domain");
        Color color = component.getColor();
        Color colortext = component.getColorText();
        Color colorline = component.getColorline();
        domainElement.addContent(domainStartElement);
        domainStartElement.setAttribute("direction", startDrawType);
        domainStartElement.addContent(start);

        domainElement.addContent(domainEndElement);
        domainEndElement.setAttribute("direction", endDrawType);
        domainEndElement.addContent(end);

        domainElement.addContent(domainNameElement);
        domainNameElement.setAttribute("direction", nameDrawType);
        domainNameElement.setAttribute("size", size);
        domainNameElement.setAttribute("color", color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        domainNameElement.setAttribute("colortext", colortext.getRed() + "," + colortext.getGreen() + "," + colortext.getBlue());
        domainNameElement.setAttribute("colorline", colorline.getRed() + "," + colorline.getGreen() + "," + colorline.getBlue());

        domainNameElement.addContent(name);


        domainHighElement.addContent(strHigh);
        domainShapeTypeElement.addContent(strShapeType);

        domainStrokeSizeElement.addContent(strStrokeSize);

        domainGradientElement.addContent(gradient);
        domainTextureElement.addContent(texture);


        polygonRotationAngleElement.addContent(sRotationAngle);
        polygonDashElement.addContent(String.valueOf(component.isbDash()));
        domainElement.addContent(domainHighElement);
        domainElement.addContent(domainShapeTypeElement);


        domainElement.addContent(domainStrokeSizeElement);

        domainElement.addContent(domainGradientElement);

        domainElement.addContent(domainTextureElement);
        domainElement.addContent(polygonRotationAngleElement);
        domainElement.addContent(polygonDashElement);
        domainElement.addContent(polygonLeftDashElement);
        domainElement.addContent(polygonRightDashElement);
        Element domainFontElement = new Element("textfont");
        domainFontElement.addContent("");
        domainElement.addContent(domainFontElement);
        return domainElement;
    }

    private Element siteElement(NComponent component) {
        String[] arr = component.getParameter().split(DELIMIT);
        String positions = arr[0];
        String text = arr[1];
        String size = arr[2];
        String isShow = arr[3];

        String deltaX = String.valueOf((int) component.getX());
        String deltaY = String.valueOf((int) component.getY());
        Color color = component.getColor();
        Color colortext = component.getColorText();
        Color colorline = component.getColorline();

        String strStrokeSize = arr[4];
        String gradient = arr[5];
        String texture = arr[6];

        String sCenterx = String.valueOf((int) component.getCenterx());
        String sCentery = String.valueOf((int) component.getCentery());
        String sWide = String.valueOf((int) component.getWide());
        String sHigh = String.valueOf((int) component.getHigh());
        String sRotationAngle = String.valueOf((int) component.getRotationAngle());
        String strShapeType = component.getShapeType().name();

        Element siteElement = new Element("component");
        Element sitePositionElement = new Element("position");
        Element siteTextElement = new Element("text");
        Element siteDashElement = new Element("dash");
        siteDashElement.addContent(String.valueOf(component.isbDash()));

        siteElement.setAttribute("type", "site");

        siteElement.addContent(sitePositionElement);
        sitePositionElement.addContent(positions);
        sitePositionElement.setAttribute("show", isShow);

        siteElement.addContent(siteTextElement);
        siteTextElement.setAttribute("deltax", deltaX);
        siteTextElement.setAttribute("deltay", deltaY);
        siteTextElement.setAttribute("size", size);
        siteTextElement.setAttribute("color", color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        siteTextElement.setAttribute("colortext", colortext.getRed() + "," + colortext.getGreen() + "," + colortext.getBlue());
        siteTextElement.setAttribute("colorline", colorline.getRed() + "," + colorline.getGreen() + "," + colorline.getBlue());
        siteTextElement.addContent(text);

        Element siteStrokeSizeElement = new Element("strokesize");
        Element siteGradientElement = new Element("gradient");
        Element siteTextureElement = new Element("texture");
        Element siteCenterxElement = new Element("centerx");
        Element siteCenteryElement = new Element("centery");
        Element siteWideElement = new Element("wide");
        Element siteHighElement = new Element("high");
        Element siteRotationAngleElement = new Element("rotationAngle");
        Element siteShapeTypeElement = new Element("shapetype");

        siteShapeTypeElement.addContent(strShapeType);
        siteStrokeSizeElement.addContent(strStrokeSize);
        siteGradientElement.addContent(gradient);
        siteTextureElement.addContent(texture);
//
        siteCenterxElement.addContent(sCenterx);
        siteCenteryElement.addContent(sCentery);
        siteWideElement.addContent(sWide);
        siteHighElement.addContent(sHigh);
        siteRotationAngleElement.addContent(sRotationAngle);
//
        siteElement.addContent(siteStrokeSizeElement);
        siteElement.addContent(siteGradientElement);
        siteElement.addContent(siteTextureElement);

        siteElement.addContent(siteCenterxElement);
        siteElement.addContent(siteCenteryElement);
        siteElement.addContent(siteWideElement);
        siteElement.addContent(siteHighElement);
        siteElement.addContent(siteRotationAngleElement);

        siteElement.addContent(siteShapeTypeElement);
//   
        siteElement.addContent(siteDashElement);
        Element siteFontElement = new Element("textfont");
        siteFontElement.addContent("");
        siteElement.addContent(siteFontElement);
        return siteElement;
    }

    private Element noteElement(NComponent component) {
        Color color = component.getColor();
        Color linecolor = component.getColorline();
        boolean bdash = component.bDash;
        String[] arr = component.getParameter().split(DELIMIT);
        String text = arr[0];
//        System.out.println("v:"+text+":v");
        String size = arr[1];
        String style = arr[2];
        String angle =String.valueOf((int) component.getRotationAngle());//arr[3];
        String linesize = arr[4];
        String deltaX = String.valueOf((int) component.getX());
        String deltaY = String.valueOf((int) component.getY());

        Element noteElement = new Element("component");
        Element noteTextElement = new Element("text");
        noteElement.setAttribute("type", "note");

        noteElement.addContent(noteTextElement);
        noteTextElement.setAttribute("deltax", deltaX);
        noteTextElement.setAttribute("deltay", deltaY);
        noteTextElement.setAttribute("size", size);
        noteTextElement.setAttribute("style", style);
        noteTextElement.setAttribute("angle", angle);
        noteTextElement.setAttribute("linesize", linesize);
        noteTextElement.setAttribute("color", color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        noteTextElement.setAttribute("linecolor", linecolor.getRed() + "," + linecolor.getGreen() + "," + linecolor.getBlue());
        noteTextElement.setAttribute("linedash", String.valueOf(bdash));

        noteTextElement.addContent(text);
        Element noteTextFontElement = new Element("textfont");
        noteTextFontElement.addContent("");
        noteTextElement.addContent(noteTextFontElement);
        return noteElement;
    }

    private Element polygonElement(NComponent component) {
//       name  + DELIMIT + nameComboBox.getSelectedItem() + DELIMIT + fontTextField.getText().trim() + DELIMIT + strokesize
//                + DELIMIT + jComboBoxGradientPaint.getSelectedItem() + DELIMIT +  texturechoose.SelectTextureFile     
        String parameter = component.getParameter();
        StringTokenizer st = new StringTokenizer(parameter, DELIMIT);

        String name = st.nextToken();
        String nameDrawType = st.nextToken();
        String size = st.nextToken();
        String strStrokeSize = st.nextToken();
        String gradient = st.nextToken();
        String texture = st.nextToken();

        String sCenterx = String.valueOf((int) component.getCenterx());
        String sCentery = String.valueOf((int) component.getCentery());
        String sWide = String.valueOf((int) component.getWide());
        String sHigh = String.valueOf((int) component.getHigh());
        String sRotationAngle = String.valueOf((int) component.getRotationAngle());
        String strShapeType = component.getShapeType().name();

        Color color = component.getColor();
        Color colortext = component.getColorText();
        Color colorline = component.getColorline();


        Element polygonElement = new Element("component");
        polygonElement.setAttribute("type", "polygon");
        Element polygonNameElement = new Element("name");
        Element polygonStrokeSizeElement = new Element("strokesize");
        Element polygonGradientElement = new Element("gradient");
        Element polygonTextureElement = new Element("texture");

        Element polygonCenterxElement = new Element("centerx");
        Element polygonCenteryElement = new Element("centery");
        Element polygonWideElement = new Element("wide");
        Element polygonHighElement = new Element("high");
        Element polygonRotationAngleElement = new Element("rotationAngle");

        Element polygonShapeTypeElement = new Element("shapetype");
        Element polygonDashElement = new Element("dash");
        polygonDashElement.addContent(String.valueOf(component.isbDash()));

        polygonElement.addContent(polygonNameElement);
        polygonNameElement.setAttribute("direction", nameDrawType);
        polygonNameElement.setAttribute("size", size);
        polygonNameElement.setAttribute("color", color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        polygonNameElement.setAttribute("colortext", colortext.getRed() + "," + colortext.getGreen() + "," + colortext.getBlue());
        polygonNameElement.setAttribute("colorline", colorline.getRed() + "," + colorline.getGreen() + "," + colorline.getBlue());

        polygonNameElement.addContent(name);

        polygonShapeTypeElement.addContent(strShapeType);
        polygonStrokeSizeElement.addContent(strStrokeSize);
        polygonGradientElement.addContent(gradient);
        polygonTextureElement.addContent(texture);

        polygonCenterxElement.addContent(sCenterx);
        polygonCenteryElement.addContent(sCentery);
        polygonWideElement.addContent(sWide);
        polygonHighElement.addContent(sHigh);
        polygonRotationAngleElement.addContent(sRotationAngle);

        polygonElement.addContent(polygonStrokeSizeElement);
        polygonElement.addContent(polygonGradientElement);
        polygonElement.addContent(polygonTextureElement);


        polygonElement.addContent(polygonCenterxElement);
        polygonElement.addContent(polygonCenteryElement);
        polygonElement.addContent(polygonWideElement);
        polygonElement.addContent(polygonHighElement);
        polygonElement.addContent(polygonRotationAngleElement);

        polygonElement.addContent(polygonShapeTypeElement);
        polygonElement.addContent(polygonDashElement);
        Element polygonFontElement = new Element("textfont");
        polygonFontElement.addContent("");
        polygonElement.addContent(polygonFontElement);
        return polygonElement;
    }

    private Element lineElement(NComponent component) {
        Color color = component.getColor();
        String[] arr = component.getParameter().split(DELIMIT);
        String weight = arr[0];
        String style = arr[1];
        String fname = "";
        NDrawTypeEnum nameDrawType;
        int size = 10;
        if (arr.length > 2) {
            fname = arr[2];
            nameDrawType = NDrawTypeEnum.valueOf(arr[3]);
            size = Integer.parseInt(arr[4]);
            fname.trim();
        } else {
            fname = "";
            nameDrawType = NDrawTypeEnum.Center;
            size = 10;
        }

        String x1 = String.valueOf((int) component.getX());
        String y1 = String.valueOf(component.getY());
        String x2 = String.valueOf((int) component.getX2());
        String y2 = String.valueOf(component.getY2());
        String conx1 = String.valueOf(component.getControlx1());
        String cony1 = String.valueOf(component.getControly1());
        String conx2 = String.valueOf(component.getControlx2());
        String cony2 = String.valueOf(component.getControly2());

        String strShapeType = component.getShapeType().name();

        Element lineElement = new Element("component");
        lineElement.setAttribute("type", "line");

        Element weightElement = new Element("weight");
        weightElement.setAttribute("style", style);
        weightElement.setAttribute("color", color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        weightElement.addContent(weight);

        Element textElement = new Element("text");
        textElement.setAttribute("size", String.valueOf(size));
        textElement.setAttribute("color", component.getColorText().getRed() + "," + component.getColorText().getGreen() + "," + component.getColorText().getBlue());
        textElement.setAttribute("direction", nameDrawType.name().toString());

        textElement.addContent(fname);


        Element x1Element = new Element("x1");
        x1Element.addContent(x1);
        Element y1Element = new Element("y1");
        y1Element.addContent(y1);
        Element x2Element = new Element("x2");
        x2Element.addContent(x2);
        Element y2Element = new Element("y2");
        y2Element.addContent(y2);

        Element lineShapeTypeElement = new Element("shapetype");
        lineShapeTypeElement.addContent(strShapeType);



        Element conx1Element = new Element("controlx1");
        conx1Element.addContent(conx1);
        Element cony1Element = new Element("controly1");
        cony1Element.addContent(cony1);
        Element conx2Element = new Element("controlx2");
        conx2Element.addContent(conx2);
        Element cony2Element = new Element("controly2");
        cony2Element.addContent(cony2);

        Element dashElement = new Element("dash");
        dashElement.addContent(String.valueOf(component.isbDash()));

        lineElement.addContent(weightElement);
        lineElement.addContent(textElement);
        lineElement.addContent(x1Element);
        lineElement.addContent(y1Element);
        lineElement.addContent(x2Element);
        lineElement.addContent(y2Element);
        lineElement.addContent(conx1Element);
        lineElement.addContent(cony1Element);
        lineElement.addContent(conx2Element);
        lineElement.addContent(cony2Element);
        lineElement.addContent(lineShapeTypeElement);
        lineElement.addContent(dashElement);

        return lineElement;
    }

    private Element markElement(NComponent component) {
        Color color = component.getColor();
        String[] arr = component.getParameter().split(DELIMIT);
        String weight = arr[0];
        String style = arr[1];
        String position = arr[2];
        String x1 = String.valueOf((int) component.getX());
        String y1 = String.valueOf(component.getY());
        String x2 = String.valueOf((int) component.getX2());
        String y2 = String.valueOf(component.getY2());
        String x3 = String.valueOf(component.getX3());
        String y3 = String.valueOf(component.getY3());
        Element lineElement = new Element("component");
        lineElement.setAttribute("type", "mark");

        Element weightElement = new Element("weight");
        weightElement.setAttribute("style", style);
        weightElement.setAttribute("color", color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        weightElement.addContent(weight);
        Element positionElement = new Element("position");
        positionElement.addContent(position);
        Element x1Element = new Element("x1");
        x1Element.addContent(x1);
        Element y1Element = new Element("y1");
        y1Element.addContent(y1);
        Element x2Element = new Element("x2");
        x2Element.addContent(x2);
        Element y2Element = new Element("y2");
        y2Element.addContent(y2);
        Element x3Element = new Element("x3");
        x3Element.addContent(x3);
        Element y3Element = new Element("y3");
        y3Element.addContent(y3);
        lineElement.addContent(weightElement);
        lineElement.addContent(x1Element);
        lineElement.addContent(y1Element);
        lineElement.addContent(x2Element);
        lineElement.addContent(y2Element);
        lineElement.addContent(x3Element);
        lineElement.addContent(y3Element);
        lineElement.addContent(positionElement);

        return lineElement;
    }

    public void save(NProject project) {
        try {
            Document doc = buildDocument(project);
            XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
            xmlOut.output(doc, new FileOutputStream(new File(this.filePath)));
        } catch (IOException ex) {
            Logger.getLogger(NProject2XML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public NProject openDemo() {
//        NProject project = null;
//        InputStream is2 = getClass().getResourceAsStream(
//                "/gui/dog3/002.xml");
//
//
//        try {
//            SAXBuilder builder = new SAXBuilder(false);
//            Document doc = builder.build(is2);//.build(filePath);
//            project = analysisXML(doc);
//            return project;
//        } catch (JDOMException ex) {
//            Logger.getLogger(NProject2XML.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(NProject2XML.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return project;
//    }

//    NProject open() {
//        NProject project = null;
//        try {
//            SAXBuilder builder = new SAXBuilder(false);
//            Document doc = builder.build(filePath);
//            project = analysisXML(doc);
//
//            return project;
//        } catch (JDOMException ex) {
//            Logger.getLogger(NProject2XML.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(NProject2XML.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return project;
//    }

//    private NProject analysisXML(Document doc) {
//        try {
//            LinkedList<NProtein> proteinList = new LinkedList<NProtein>();
//            Element root = doc.getRootElement();
//
//            /***********2.0������mode����********************/
//            String fmode;
//            List allroot = root.getAttributes();
//            boolean bmode = false;
//            for (int p = 0; p < allroot.size(); p++) {
//                String sl = allroot.get(p).toString();
//                if (sl.contains("[Attribute: mode=")) {
//                    bmode = true;
//                }
//            }
//            if (bmode = true) {
//                fmode = root.getAttributeValue("mode");
//                if (fmode == null) {
//                    fmode = "protein";
//                }
//            } else {
//                fmode = "protein";
//            }
//
//            if (!fmode.equals("nucleotide")) {
//                JOptionPane.showConfirmDialog(null, "Only open a XML file with the nucleotide mode!");
//                return null;
//            }
//            String projectVersion = root.getAttributeValue("version");
//
//            int width = Integer.parseInt(root.getAttributeValue("width"));
//            int height = Integer.parseInt(root.getAttributeValue("height"));
//            float rate = 1.0f;
//            try {
//                String rStr = root.getAttributeValue("rate");
//                if (rStr.contains(",")) {
//                    rate = Float.parseFloat(rStr.replaceAll(",", "."));
//                } else {
//                    rate = Float.parseFloat(rStr);
//                }
//            } catch (NumberFormatException numberFormatException) {
//            }
//            if (projectVersion.equals(VERSION)) {
//                VERSION = projectVersion;
//                List projectNodeList = root.getChildren("protein");
//                for (Iterator it = projectNodeList.iterator(); it.hasNext();) {
//                    Element protein = (Element) it.next();
//                    int vertical = Integer.parseInt(protein.getAttributeValue("vertical"));
//
//                    int horizontal = Integer.parseInt(protein.getAttributeValue("horizontal"));
//                    boolean isLocked = Boolean.parseBoolean(protein.getAttributeValue("locked"));
//                    Element proteinStartElement = protein.getChild("start");
//                    String proteinStart = proteinStartElement.getText();
//                    String proteinStartType = proteinStartElement.getAttributeValue("direction");
//
//                    Element proteinEndElement = protein.getChild("end");
//                    String proteinEnd = proteinEndElement.getText();
//                    String proteinEndType = proteinEndElement.getAttributeValue("direction");
//
//                    Element proteinHeightElement = protein.getChild("height");
//                    String proteinHeight = proteinHeightElement.getText();
//
//                    String proteinColorStr = proteinHeightElement.getAttributeValue("color");
//                    String proteinColorlineStr = proteinHeightElement.getAttributeValue("colorline");
//
//                    StringTokenizer ster = new StringTokenizer(proteinColorStr, ",");
//                    Color proteinColor = new Color(Integer.parseInt(ster.nextToken()), Integer.parseInt(ster.nextToken()),
//                            Integer.parseInt(ster.nextToken()));
//                    StringTokenizer sterline = new StringTokenizer(proteinColorlineStr, ",");
//                    Color proteinlineColor = new Color(Integer.parseInt(sterline.nextToken()), Integer.parseInt(sterline.nextToken()),
//                            Integer.parseInt(sterline.nextToken()));
//
//
//                    String proteinSize = proteinHeightElement.getAttributeValue("size");
//                    Element proteingradient = protein.getChild("gradient");
//                    String gradient1 = proteingradient.getText();
//                    Element proteintexture = protein.getChild("texture");
//                    String texture1 = proteintexture.getText();
//                    Element proteindash = protein.getChild("dash");
//                    boolean bProteinDash = Boolean.valueOf(proteindash.getText());
//                    Element proteinStrokeSize = protein.getChild("linesize");
//                    String stproteinStrokeSize = proteinStrokeSize.getText();
//
//                    String proteinParameter = proteinStart + DELIMIT + proteinStartType + DELIMIT
//                            + proteinEnd + DELIMIT + proteinEndType + DELIMIT + proteinHeight + DELIMIT + proteinSize
//                            + DELIMIT + gradient1 + DELIMIT + texture1 + DELIMIT + bProteinDash + DELIMIT + stproteinStrokeSize + DELIMIT + proteinColorlineStr;
//
//                    LinkedList<NComponent> componentList = new LinkedList<NComponent>();
//                    List componentNodeList = protein.getChildren("component");
//                    for (Iterator it1 = componentNodeList.iterator(); it1.hasNext();) {
//                        Element component = (Element) it1.next();
//                        String componentType = component.getAttributeValue("type");
//                        if (componentType.equals("domain")) {
//                            Element domainStart = component.getChild("start");
//                            int start = Integer.parseInt(domainStart.getText());
//                            String startDirect = domainStart.getAttributeValue("direction");
//
//                            Element domainEnd = component.getChild("end");
//                            int end = Integer.parseInt(domainEnd.getText());
//                            String endDirect = domainEnd.getAttributeValue("direction");
//
//                            Element domainName = component.getChild("name");
//                            String name = domainName.getText();
//                            if (name.equals("")) {
//                                name = " ";
//                            }
//                            String nameDirect = domainName.getAttributeValue("direction");
//                            String nameSize = domainName.getAttributeValue("size");
//                            String colorStr = domainName.getAttributeValue("color");
//                            StringTokenizer st = new StringTokenizer(colorStr, ",");
//                            Color color = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//                                    Integer.parseInt(st.nextToken()));
//
//                            String colorlineStr = domainName.getAttributeValue("colorline");
//                            StringTokenizer stline = new StringTokenizer(colorlineStr, ",");
//                            Color colorline = new Color(Integer.parseInt(stline.nextToken()), Integer.parseInt(stline.nextToken()),
//                                    Integer.parseInt(stline.nextToken()));
//
//                            String colortextStr = domainName.getAttributeValue("colortext");
//                            StringTokenizer sttext = new StringTokenizer(colortextStr, ",");
//                            Color colortext = new Color(Integer.parseInt(sttext.nextToken()), Integer.parseInt(sttext.nextToken()),
//                                    Integer.parseInt(sttext.nextToken()));
//
//                            Element domainstrokesize = component.getChild("strokesize");
//
//                            String strokesize = domainstrokesize.getText();
//                            Element domaingradient = component.getChild("gradient");
//
//                            String gradient = domaingradient.getText();
//
//
//                            Element domaintexture = component.getChild("texture");
//
//                            String texture = domaintexture.getText();
//
//                            Element domainRotationAngle = component.getChild("rotationAngle");
//                            String sRotationAngle = domainRotationAngle.getText();
//
//                            int iRotationAngle;
//
//                            iRotationAngle = Integer.valueOf(sRotationAngle);
//
//                            String parameter = startDirect + DELIMIT + endDirect + DELIMIT
//                                    + name + DELIMIT + nameDirect + DELIMIT + nameSize + DELIMIT
//                                    + strokesize + DELIMIT + gradient + DELIMIT + texture;
////
////                              
//                            int y1 = 0;
//                            int y2 = 0;
//                            Element domainHigh = component.getChild("high");
//                            String strhigh = domainHigh.getText();
//                            int iHigh = Integer.valueOf(strhigh);
////                                 
//
//                            Element domainshapetype = component.getChild("shapetype");
//                            String strshapetype = domainshapetype.getText();
//
//                            ShapeEnum shapetype = ShapeEnum.valueOf(strshapetype);
////
//                            Element domainborderdash = component.getChild("dash");
//                            String strborderdash = domainborderdash.getText();
//                            Element domainbleftdash = component.getChild("leftdash");
//                            String strbleftdash = domainbleftdash.getText();
//                            Element domainbrightdash = component.getChild("rightdash");
//                            String strbrightdash = domainbrightdash.getText();
//                            componentList.add(new NComponent(ComponentEnum.Domain, start, end, parameter,
//                                    color, colortext, colorline,
//                                    shapetype, iHigh, iRotationAngle,
//                                    Boolean.valueOf(strbleftdash), Boolean.valueOf(strbrightdash), Boolean.valueOf(strborderdash)));
//
//                        } else if (componentType.equals("site")) {
//                            Element sitePosition = component.getChild("position");
//                            String position = sitePosition.getText();
//                            String show = sitePosition.getAttributeValue("show");
//
//                            Element siteText = component.getChild("text");
//                            String text = siteText.getText();
//                            if (text.equals("")) {
//                                text = "     ";
//                            }
//                            int deltaX = Integer.parseInt(siteText.getAttributeValue("deltax"));
//                            int deltaY = Integer.parseInt(siteText.getAttributeValue("deltay"));
//                            String size = siteText.getAttributeValue("size");
//                            String colorStr = siteText.getAttributeValue("color");
//                            String colortextStr = siteText.getAttributeValue("colortext");
//                            String colorlineStr = siteText.getAttributeValue("colorline");
//
//                            StringTokenizer st = new StringTokenizer(colorStr, ",");
//                            Color color = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//                                    Integer.parseInt(st.nextToken()));
//                            StringTokenizer sttext = new StringTokenizer(colortextStr, ",");
//                            Color colortext = new Color(Integer.parseInt(sttext.nextToken()), Integer.parseInt(sttext.nextToken()),
//                                    Integer.parseInt(sttext.nextToken()));// 
//                            StringTokenizer stline = new StringTokenizer(colorlineStr, ",");
//                            Color colorline = new Color(Integer.parseInt(stline.nextToken()), Integer.parseInt(stline.nextToken()),
//                                    Integer.parseInt(stline.nextToken()));// 
//
//                            Element sitestrokesize = component.getChild("strokesize");
//                            String strokesize = sitestrokesize.getText();
//                            Element sitegradient = component.getChild("gradient");
//                            String gradient = sitegradient.getText();
//                            Element sitetexture = component.getChild("texture");
//                            String texture = sitetexture.getText();
//                            Element siteCenterx = component.getChild("centerx");
//                            String sCenterx = siteCenterx.getText();
//                            Element siteCentery = component.getChild("centery");
//                            String sCentery = siteCentery.getText();
//                            Element siteWide = component.getChild("wide");
//                            String sWide = siteWide.getText();
//                            Element siteHigh = component.getChild("high");
//                            String sHigh = siteHigh.getText();
//                            Element siteRotationAngle = component.getChild("rotationAngle");
//                            String sRotationAngle = siteRotationAngle.getText();
//                            Element sitedash = component.getChild("dash");
//                            boolean bsitedash = Boolean.valueOf(sitedash.getText());
//                            int iCenterx, iCentery, iWide, iHigh, iRotationAngle;
//                            iCenterx = Integer.valueOf(sCenterx);
//                            iCentery = Integer.valueOf(sCentery);
//                            iWide = Integer.valueOf(sWide);
//                            iHigh = Integer.valueOf(sHigh);
//                            iRotationAngle = Integer.valueOf(sRotationAngle);
//
//
//                            Element siteshapetype = component.getChild("shapetype");
//                            String strshapetype = siteshapetype.getText();
//                            ShapeEnum shapetype = ShapeEnum.valueOf(strshapetype);
//
//                            String parameter = position + DELIMIT + text + DELIMIT + size + DELIMIT + show + DELIMIT + strokesize + DELIMIT + gradient + DELIMIT + texture;
//
//                            componentList.add(new NComponent(ComponentEnum.Site, iCenterx, iCentery, parameter, colortext, shapetype, "site",
//                                    iWide, iHigh, iRotationAngle, color, colorline, bsitedash));
//                        } else if (componentType.equals("note")) {
//                            Element noteText = component.getChild("text");
//                            String text = noteText.getText();
//                            int deltaX = Integer.parseInt(noteText.getAttributeValue("deltax"));
//                            int deltaY = Integer.parseInt(noteText.getAttributeValue("deltay"));
//                            String size = noteText.getAttributeValue("size");
//                            String style = noteText.getAttributeValue("style");
//                            String angle = noteText.getAttributeValue("angle");
//                            String linesize = noteText.getAttributeValue("linesize");
//                            String linedash = noteText.getAttributeValue("linedash");
//                            boolean blinedash = Boolean.valueOf(linedash);
//                            String colorStr = noteText.getAttributeValue("color");
//                            StringTokenizer st = new StringTokenizer(colorStr, ",");
//                            Color color = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//                                    Integer.parseInt(st.nextToken()));
//
//                            String linecolorStr = noteText.getAttributeValue("linecolor");
//                            StringTokenizer stline = new StringTokenizer(linecolorStr, ",");
//                            Color linecolor = new Color(Integer.parseInt(stline.nextToken()), Integer.parseInt(stline.nextToken()),
//                                    Integer.parseInt(stline.nextToken()));
//
//                            String parameter = text + DELIMIT + size + DELIMIT + style + DELIMIT + angle + DELIMIT + linesize;
//                            componentList.add(new NComponent(ComponentEnum.Note, deltaX, deltaY, parameter, color, linecolor, blinedash ,
//                                    Integer.valueOf( angle)));
//                        } else if (componentType.equals("line")) {
//                            Element weightElement = component.getChild("weight");
//                            String weight = weightElement.getText();
//                            String style = weightElement.getAttributeValue("style");
//                            String colorStr = weightElement.getAttributeValue("color");
//                            StringTokenizer st = new StringTokenizer(colorStr, ",");
//                            Color color = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//                                    Integer.parseInt(st.nextToken()));
//
//                            int x1 = Integer.parseInt(component.getChildText("x1"));
//                            int y1 = Integer.parseInt(component.getChildText("y1"));
//                            int x2 = Integer.parseInt(component.getChildText("x2"));
//                            int y2 = Integer.parseInt(component.getChildText("y2"));
//                            int conx1 = 0;
//                            int cony1 = 0;
//                            int conx2 = 0;
//                            int cony2 = 0;
//                            Element domainshapetype;
//                            String strshapetype;
//
//                            ShapeEnum shapetype;
//                            boolean bDash = false;
//                            String fname = "";
//                            NDrawTypeEnum nameDrawType = NDrawTypeEnum.Center;
//                            int size = 10;
//                            Color colortext = Color.BLACK;
//
//                            if (projectVersion.equals("DOG 2.0")) {
//                                conx1 = Integer.parseInt(component.getChildText("controlx1"));
//                                cony1 = Integer.parseInt(component.getChildText("controly1"));
//                                conx2 = Integer.parseInt(component.getChildText("controlx2"));
//                                cony2 = Integer.parseInt(component.getChildText("controly2"));
//                                domainshapetype = component.getChild("shapetype");
//                                strshapetype = domainshapetype.getText();
//                                shapetype = ShapeEnum.valueOf(strshapetype);
//
//                                bDash = Boolean.parseBoolean(component.getChildText("dash"));
//                                Element textElement = component.getChild("text");
//                                fname = textElement.getText();
//                                nameDrawType = NDrawTypeEnum.valueOf(textElement.getAttributeValue("direction"));
//                                size = Integer.parseInt(textElement.getAttributeValue("size"));
//                                fname.trim();
//                                String colortextStr = textElement.getAttributeValue("color");
//
//
//                                StringTokenizer st1 = new StringTokenizer(colortextStr, ",");
//                                colortext = new Color(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()),
//                                        Integer.parseInt(st1.nextToken()));// 
//                            } else {
//                                shapetype = ShapeEnum.Straight;
//                            }
//
//                            String parameter = weight + DELIMIT + style + DELIMIT + fname + DELIMIT
//                                    + nameDrawType.name().toString() + DELIMIT + String.valueOf(size);
//
//
//
//                            componentList.add(new NComponent(ComponentEnum.Line, x1, y1, x2, y2, conx1, cony1, conx2, cony2, parameter, color, colortext, shapetype, bDash));
//
//
//                        } else if (componentType.equals("polygon")) {
//                            Element polygonName = component.getChild("name");
//                            String name = polygonName.getText();
//                            if (name.equals("")) {
//                                name = " ";
//                            }
//                            String nameDirect = polygonName.getAttributeValue("direction");
//                            String nameSize = polygonName.getAttributeValue("size");
//                            String colorStr = polygonName.getAttributeValue("color");
//                            String colortextStr = polygonName.getAttributeValue("colortext");
//                            String colorlineStr = polygonName.getAttributeValue("colorline");
//
//                            StringTokenizer st = new StringTokenizer(colorStr, ",");
//                            Color color = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//                                    Integer.parseInt(st.nextToken()));//     
//                            StringTokenizer sttext = new StringTokenizer(colortextStr, ",");
//                            Color colortext = new Color(Integer.parseInt(sttext.nextToken()), Integer.parseInt(sttext.nextToken()),
//                                    Integer.parseInt(sttext.nextToken()));// 
//                            StringTokenizer stline = new StringTokenizer(colorlineStr, ",");
//                            Color colorline = new Color(Integer.parseInt(stline.nextToken()), Integer.parseInt(stline.nextToken()),
//                                    Integer.parseInt(stline.nextToken()));// 
//
//
//                            Element polygonstrokesize = component.getChild("strokesize");
//                            String strokesize = polygonstrokesize.getText();
//                            Element polygongradient = component.getChild("gradient");
//                            String gradient = polygongradient.getText();
//                            Element polygontexture = component.getChild("texture");
//                            String texture = polygontexture.getText();
//                            Element polygonCenterx = component.getChild("centerx");
//                            String sCenterx = polygonCenterx.getText();
//                            Element polygonCentery = component.getChild("centery");
//                            String sCentery = polygonCentery.getText();
//                            Element polygonWide = component.getChild("wide");
//                            String sWide = polygonWide.getText();
//                            Element polygonHigh = component.getChild("high");
//                            String sHigh = polygonHigh.getText();
//                            Element polygonRotationAngle = component.getChild("rotationAngle");
//                            String sRotationAngle = polygonRotationAngle.getText();
//                            Element polygondash = component.getChild("dash");
//                            boolean bpolygonDash = Boolean.valueOf(polygondash.getText());
//                            int iCenterx, iCentery, iWide, iHigh, iRotationAngle;
//                            iCenterx = Integer.valueOf(sCenterx);
//                            iCentery = Integer.valueOf(sCentery);
//                            iWide = Integer.valueOf(sWide);
//                            iHigh = Integer.valueOf(sHigh);
//                            iRotationAngle = Integer.valueOf(sRotationAngle);
//                            String parameter =
//                                    name + DELIMIT + nameDirect + DELIMIT + nameSize + DELIMIT + strokesize + DELIMIT + gradient + DELIMIT + texture;
//                            Element polygonshapetype = component.getChild("shapetype");
//                            String strshapetype = polygonshapetype.getText();
//                            ShapeEnum shapetype = ShapeEnum.valueOf(strshapetype);
//                            componentList.add(new NComponent(ComponentEnum.Polygon, iCenterx, iCentery, iWide, iHigh,
//                                    iRotationAngle, parameter, color, colortext, colorline, shapetype, bpolygonDash));
//                        } else if (componentType.equals("bracket")) {
//                            Element weightElement = component.getChild("weight");
//                            String colorStr = weightElement.getAttributeValue("color");
//                            StringTokenizer st = new StringTokenizer(colorStr, ",");
//                            Color color = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//                                    Integer.parseInt(st.nextToken()));//                         
//                            Element domainstrokesize = component.getChild("strokesize");
//                            String strokesize = domainstrokesize.getText();
//                            Element domainCenterx = component.getChild("centerx");
//                            String sCenterx = domainCenterx.getText();
//                            Element domainCentery = component.getChild("centery");
//                            String sCentery = domainCentery.getText();
//                            Element domainWide = component.getChild("wide");
//                            String sWide = domainWide.getText();
//                            Element domainRotationAngle = component.getChild("rotationAngle");
//                            String sRotationAngle = domainRotationAngle.getText();
//                            int iCenterx, iCentery, iWide, iHigh, iRotationAngle;
//                            iCenterx = Integer.valueOf(sCenterx);
//                            iCentery = Integer.valueOf(sCentery);
//                            iWide = Integer.valueOf(sWide);
//                            iRotationAngle = Integer.valueOf(sRotationAngle);
//                            String parameter = strokesize;
//                            Element domainshapetype = component.getChild("shapetype");
//                            String strshapetype = domainshapetype.getText();
//                            ShapeEnum shapetype = ShapeEnum.valueOf(strshapetype);
//                            componentList.add(new NComponent(ComponentEnum.Bracket, color, iCenterx, iCentery, iWide,
//                                    iRotationAngle, parameter, shapetype));
//                        } else if (componentType.equals("mark")) {
//                            Element weightElement = component.getChild("weight");
//                            String weight = weightElement.getText();
//                            String style = weightElement.getAttributeValue("style");
//                            String colorStr = weightElement.getAttributeValue("color");
//                            StringTokenizer st = new StringTokenizer(colorStr, ",");
//                            Color color = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//                                    Integer.parseInt(st.nextToken()));
//                            int x1 = Integer.parseInt(component.getChildText("x1"));
//                            int y1 = Integer.parseInt(component.getChildText("y1"));
//                            int x2 = Integer.parseInt(component.getChildText("x2"));
//                            int y2 = Integer.parseInt(component.getChildText("y2"));
//                            int x3 = Integer.parseInt(component.getChildText("x3"));
//                            int y3 = Integer.parseInt(component.getChildText("y3"));
//                            String strPosition = component.getChildText("position");
//                            String parameter = weight + DELIMIT + style + DELIMIT + strPosition;
//                            componentList.add(new NComponent(ComponentEnum.Mark, x1, y1, x2, y2, x3, y3, parameter, color));
//                        }
//                    }
//                    proteinList.add(new NProtein(vertical, horizontal, isLocked, Integer.parseInt(proteinEnd), proteinParameter, proteinColor, proteinlineColor, componentList));//, bProteinDash));
//                }
//
//            } else {
//                JOptionPane.showMessageDialog(null, filePath + " not a " + VERSION + " project or has been corrupted.",
//                        "Error : cannot open file", JOptionPane.ERROR_MESSAGE, null);
//            }
//            return new NProject(width, height, rate, proteinList);
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
