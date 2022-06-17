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

        response.setContentType("text/html;");
        response.getWriter().println("<h2>Your message:</h2>");
        response.getWriter().println("<p>" + message + "</p>");
        response.getWriter().println("<h3>Got the following score:</h3>");
        response.getWriter().println("<p>" + scoreString + "</p>");
    }
}
