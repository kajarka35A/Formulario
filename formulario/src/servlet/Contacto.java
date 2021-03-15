package servlet;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Contacto
 */
@WebServlet(name = "contactoController", urlPatterns = { "/contacto" })
public class Contacto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Contacto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Entrooooo");

		String nombre = request.getParameter("nombre");
		System.out.println(nombre);
		String asunto = request.getParameter("asunto");
		System.out.println(asunto);
		String email = request.getParameter("email");
		System.out.println(email);
		String mensaje = request.getParameter("mensaje");
		System.out.println(mensaje);

		
		
		Properties props = System.getProperties();

		props.put("mail.smtp.host", "smtp.gmail.com"); //se esta conectanodo al host
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "false");//imprimir por consola 
		props.put("mail.smtp.port", "465");//misma
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //utiliza el socke ssl
		props.put("mail.smtp.socketFactory.port", "465"); // puerto por el cual lo envia
		
		Session mailSession = Session.getDefaultInstance(props, null); // creamos la sesion con lo de arriba
		mailSession.setDebug(true);

		Message mailMessage = new MimeMessage(mailSession);// declaramos el mensaje
		try {
			mailMessage.setFrom(new InternetAddress("sofiamueblesycolchones@gmail.com"));// de quien
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));// para quien
			mailMessage.setContent(mensaje, "text/html; charset=utf-8");//contenido
			mailMessage.setSubject(asunto);//cabecera
			Transport transport = mailSession.getTransport("smtp");// tipo a una sola persona
			transport.connect("smtp.gmail.com", "sofiamueblesycolchones@gmail.com", "3118938189SE");// autnticando
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());// enviar toodo

			System.out.println("Entrooooo");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

}
