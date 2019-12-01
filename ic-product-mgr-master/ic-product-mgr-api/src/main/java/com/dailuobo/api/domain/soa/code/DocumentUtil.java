package com.dailuobo.api.domain.soa.code;

import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public final class DocumentUtil {

	
	   public static Document parseText(String text) throws DocumentException {
	        Document result = null;

	        SAXReader reader = new SAXReader();
	        try {
		        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		        reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
		        reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
	        } catch (Exception e) {
	         }
	        String encoding = getEncoding(text);

	        InputSource source = new InputSource(new StringReader(text));
	        source.setEncoding(encoding);

	        result = reader.read(source);

	        // if the XML parser doesn't provide a way to retrieve the encoding,
	        // specify it manually
	        if (result.getXMLEncoding() == null) {
	            result.setXMLEncoding(encoding);
	        }

	        return result;
	    }
	   
	   private static String getEncoding(String text) {
	        String result = null;

	        String xml = text.trim();

	        if (xml.startsWith("<?xml")) {
	            int end = xml.indexOf("?>");
	            String sub = xml.substring(0, end);
	            StringTokenizer tokens = new StringTokenizer(sub, " =\"\'");

	            while (tokens.hasMoreTokens()) {
	                String token = tokens.nextToken();

	                if ("encoding".equals(token)) {
	                    if (tokens.hasMoreTokens()) {
	                        result = tokens.nextToken();
	                    }

	                    break;
	                }
	            }
	        }

	        return result;
	    }
}
