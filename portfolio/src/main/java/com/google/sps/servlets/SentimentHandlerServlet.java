package com.google.sps.servlets;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet("/sentiment")
public class SentimentHandlerServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Analyze the message to get its sentiment score:
        String message = request.getParameter("message");
        Document doc = Document.newBuilder().setContent(message).setType(Document.Type.PLAIN_TEXT).build();
        LanguageServiceClient languageService = LanguageServiceClient.create();
        Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
        float score = sentiment.getScore();
        String scoreString = Float.toString(score);
        languageService.close();


        //TODO: This is probably very inefficient (it's writing every line of the .html file "manually")
        response.setContentType("text/html;");

        //Base HTML:
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("  <head>");
        response.getWriter().println("    <meta charset=\"UTF-8\">");
        response.getWriter().println("    <title>Message received!</title>");
        response.getWriter().println("    <link id=\"bootstrap-stylesheet\" rel=\"stylesheet\" href=\"style/bootstrap.min-light.css\">");
        response.getWriter().println("    <link rel=\"stylesheet\" href=\"style/style.css\">");
        response.getWriter().println("    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Montserrat\">");
        response.getWriter().println("    <script src=\"script.js\"></script>");
        response.getWriter().println("  </head>");
        response.getWriter().println("  <body>");
        response.getWriter().println("    <div id=\"content\">");

        //Actual stuff:
        response.getWriter().println("<h2>Your message:</h2>");
        response.getWriter().println("<p>" + message + "</p>");
        response.getWriter().println("<h3>Got the following score:</h3>");
        response.getWriter().println("<p>" + scoreString + "</p>");

        //Button to return home:
        response.getWriter().println("<form action=\"/\">");
        response.getWriter().println("<input type=\"submit\" value=\"Go home\" />");
        response.getWriter().println("</form>");

        //End HTML:
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
