package org.demo;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.SystemUtils;
import org.apache.html.dom.HTMLDocumentImpl;
import org.apache.html.dom.HTMLStyleElementImpl;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLStyleElement;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;

import com.itextpdf.text.pdf.BaseFont;

public class App {

	public static void main(String[] args) throws Exception {
		List<User> all = User.all();
		System.out.println(all);
		// test();
	}
	
	public static void test() throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("<html>");

		// put in some style
		buf.append("<head><link rel='stylesheet' type='text/css' href='http://127.0.0.1/PDFservlet.css' /></head>");

		// generate the rest of the HTML...
		buf.append("<body>");
		buf.append("    <div id='container'>");
		buf.append("        <div id='check_num'>1000</div>");
		buf.append("        <div id='address'><b>Estate Of JAMES SMITH</b><br />35 Addison Avenue<br />New York, NY 00000<br />(123)456-7890</div>");
		buf.append("        <div id='date'><i>Date</i>&#160;<u>02/08/2012</u></div>");
		buf.append("        <div id='void_message'><b>VOID 180 DAYS FROM CHECK DATE</b></div>");
		buf.append("        <div id='pay_line_container'>");
		buf.append("            <div id='pay_line_message'><i>Pay To The Order Of:</i></div>");
		buf.append("            <div id='pay_line'></div>");
		buf.append("            <div id='pay_line_pay_to'>Richard Richards XXIII</div>");
		buf.append("            <div id='pay_line_amount'>$&#160;5.00</div>");
		buf.append("        </div>");
		buf.append("        <div id='pay_line2_container'>");
		buf.append("            <div id='pay_line2'></div>");
		buf.append("            <div id='pay_line2_amount_description'>Five and 00/100</div>");
		buf.append("            <div id='pay_line2_dollars'>DOLLARS</div>");
		buf.append("        </div>");
		buf.append("        <div id='void_stamp'><b>VOID</b></div>");
		buf.append("        <div id='for_line'><i>For:</i>&#160;<u>test</u></div>");
		buf.append("        <div id='bank_info'><b>TD BANKNORTH</b><br />MAINE</div>");
		buf.append("        <div id='signature_line'></div>");
		buf.append("        <div id='bank_numbers'><b>c1000c a123456789a 987654321c</b></div>");
		buf.append("    </div>");
		buf.append("</body>");
		buf.append("</html>");

		System.out.println(buf.toString());

		// parse our markup into an xml Document
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = builder.parse(buf.toString());
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(doc, null);
		renderer.layout();
		OutputStream os = new FileOutputStream("c:/2.pdf");
		renderer.createPDF(os);
		os.close();
	}

	public static void main1() throws Exception {

		OutputStream os = new FileOutputStream("c:/1.pdf");

		DOMParser parser = new DOMParser();

		parser.setProperty(
				"http://cyberneko.org/html/properties/default-encoding",
				"utf-8");

		String url = "http://v2.bootcss.com/examples/hero.html";

		BufferedReader in = new BufferedReader(new InputStreamReader(new URL(
				url).openStream()));
		parser.parse(new InputSource(in));
		in.close();

		Document doc = parser.getDocument();
		NodeList nodeList = doc.getElementsByTagName("head");

		HTMLStyleElement n = new HTMLStyleElementImpl((HTMLDocumentImpl) doc,
				"style");

		nodeList.item(0).appendChild(n);

		ITextRenderer renderer = new ITextRenderer();

		renderer.getSharedContext().setBaseURL(
				"http://v2.bootcss.com/examples/");
		// 指定模板地址
		renderer.setDocument(doc, "http://v2.bootcss.com/examples/");

		ITextFontResolver fontResolver = renderer.getFontResolver();

		if (SystemUtils.IS_OS_WINDOWS)
			fontResolver.addFont("C:/Windows/Fonts/ARIALUNI.TTF",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		else
			fontResolver.addFont("/usr/share/fonts/TTF/ARIALUNI.TTF",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		renderer.layout();
		renderer.createPDF(os);
		os.close();
	}
}
