package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ContadorCookie", urlPatterns = {"/ContadorCookie"})
public class ContadorCookie extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
    Cookie contador = buscaCookie("contador", cookies);
     
    if (contador == null)
    {
       // Creamos la cookie con el contador
       Cookie cookie = new Cookie ("contador", "1");
       cookie.setMaxAge(180);
       response.addCookie(cookie);
 
       // Mostramos el mensaje de primera visita
       PrintWriter out = response.getWriter();
       out.println ("<HTML>");            
       out.println ("<BODY>");
       out.println ("<h1>GRACIAS POR VISITAR LA PAGINA</h1>");
       out.println ("Primera visita"); 
       out.println ("<BR>");
       out.println ("</BODY>");
       out.println ("</HTML>");
    } else {
     
       // Obtenemos el valor actual del contador   
       int cont = Integer.parseInt(contador.getValue());
       cont++;
         
       // Modificamos el valor de la cookie 
       // incrementando el contador
       Cookie cookie = new Cookie ("contador", "" + cont);
       cookie.setMaxAge(180);
       response.addCookie(cookie);
 
       // Mostramos el numero de visitas
       PrintWriter out = response.getWriter();
       out.println ("<HTML>");
       out.println ("<BODY>");
       out.println ("<h1>GRACIAS POR VISITARNOS NUEVAMENTE</h1>");
       out.println ("Visita numero " + cont);
       out.println ("<br>");
       out.println("<a href='index.jsp'>Ir al inicio</a>");
       out.println ("<BR>");
       out.println ("</BODY>");
       out.println ("</HTML>");
    }       
   }    
 
   // Busca la cookie 'nombre' 
   // en el array de cookies indicado. 
   // Devuelve null si no esta
   private Cookie buscaCookie(String nombre, 
                              Cookie[] cookies)
   {
    if (cookies == null)
       return null;
     
    for (int i = 0; i < cookies.length; i++)
       if (cookies[i].getName().equals(nombre))
        return cookies[i];
     
    return null;
   }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
