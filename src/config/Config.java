/*
 * @project		MyEBook
 * @author		Oliver.xyy
 * @file_name	Config.java
 * @pub_time	2015-6-8 14:38:41
 * @copyright	Oliver.xyy All Rights Reserved.
 */
package config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class Config {
	private Map<String,String> map = new HashMap<String,String>();
	public Config() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath();
			File file = new File(path);
			Document doc = db.parse(file.getParent()+"\\config.xml");
			NodeList items = doc.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++) {
				Node item = items.item(i);
				//Element e = (Element) item;
				for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						if(node.getNodeName().equals("pageItemsNum")){
							this.map.put("pageItemsNum", node.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Map<String, String> getMap() {
		return this.map;
	}
}