package org.apache.hadoop.hdfs.server.namenode;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nimbusds.jose.shaded.json.JSONObject;

import static org.apache.hadoop.hdfs.server.namenode.NameNodeHttpServer.getNameNodeFromContext;

public class SafeModeServlet extends DfsServlet {

  public static final String SERVLET_NAME = "safemode";
  public static final String PATH_SPEC = "/safemode";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ServletContext context = getServletContext();
    NameNode nn = getNameNodeFromContext(context);
    FSNamesystem namesystem = nn.namesystem;
    resp.setContentType("application/json");
    boolean inSafeMode = namesystem.isInSafeMode();

    String safeModeTip = namesystem.getSafeModeTip();
    JSONObject result = new JSONObject();
    result.put("inSafeMode", inSafeMode);
    result.put("safeTip", safeModeTip);
    resp.getWriter().write(result.toString());
  }
}
