package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/favourite-bands")
public class FavouriteBandsServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");
    Gson gson = new Gson();
    ArrayList<String> bands = new ArrayList<String>();
    bands.add("Muse");
    bands.add("Queen");
    bands.add("The Beatles");
    bands.add("Franz Ferdinand");
    String json = gson.toJson(bands);
    response.getWriter().println(json);
  }
}
