/*
 * Napisz serwet, kt�ry odczytuje dwa parametry prefix i suffix a nast�pnie generuje obrazek,
 * kt�ry zawiera tekst w postaci prefix@suffix. W przypadku, gdy nie ma kt�rego� z parametr�w,
 * wypisywany jest tekst nonami@noname.com.
 * Pomocne b�d� funkcja w Graphics2D drawString(String tekst, int x, int y);
 */

package zad1;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/zad1")
public class zad1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		BufferedImage image = new BufferedImage(320, 200, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 320, 200);
		g.setColor(Color.BLACK);
		if (request.getParameter("param1") == null || request.getParameter("param1").trim().equals("")) {
			if (request.getParameter("param2") == null || request.getParameter("param2").trim().equals("")) {
				g.drawString("nonami" + "@" + "noname" + ".com", 15, 15);
			} else {
				g.drawString("nonami" + "@" + request.getParameter("param2"), 15, 15);
			}
		}
		else {
			if (request.getParameter("param2") == null || request.getParameter("param2").trim().equals("")) {
				g.drawString(request.getParameter("param1") + "@" + "noname" + ".com", 15, 15);
			} else {
				g.drawString(request.getParameter("param1") + "@" + request.getParameter("param2"), 15, 15);
			}
		}
		response.setContentType("image/png");
		OutputStream outO = response.getOutputStream();
		try {
			ImageIO.write(image, "png", outO);
		} catch(IOException ioe) {
			System.err.println("Error writing JPEG file: " + ioe);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
