/*
 * JSP generated by Resin-4.0.47 (built Thu, 03 Dec 2015 10:34:34 PST)
 */

package _jsp._user._jsp._include_22dpage;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class _checkbox_22dprocess__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  private boolean _caucho_isNotModified;
  private com.caucho.jsp.PageManager _jsp_pageManager;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    com.caucho.jsp.PageContextImpl pageContext = _jsp_pageManager.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);

    TagState _jsp_state = null;

    try {
      _jspService(request, response, pageContext, _jsp_application, session, _jsp_state);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_pageManager.freePageContext(pageContext);
    }
  }
  
  private void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response,
              com.caucho.jsp.PageContextImpl pageContext,
              javax.servlet.ServletContext application,
              javax.servlet.http.HttpSession session,
              TagState _jsp_state)
    throws Throwable
  {
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    javax.servlet.jsp.tagext.JspTag _jsp_parent_tag = null;
    com.caucho.jsp.PageContextImpl _jsp_parentContext = pageContext;
    response.setContentType("text/html");

     
response.setDateHeader("Expires", 0 );
response.setHeader("Cache-Control", "no-cache, post-check=0, pre-check=0"); 
response.setHeader("Pragma", "no-cache"); 

String TableName = request.getParameter("TableName")  ;
String IDField  = request.getParameter("IDField")  ;
String BeanName = request.getParameter("BeanName")  ;
String SQLEngine = request.getParameter("SQLEngine") ;
String WebApp =  request.getParameter("WebApp");

    out.write(_jsp_string0, 0, _jsp_string0.length);
    out.print((WebApp ));
    out.write(_jsp_string1, 0, _jsp_string1.length);
    out.print((WebApp ));
    out.write(_jsp_string2, 0, _jsp_string2.length);
    out.print((SQLEngine ));
    out.write(_jsp_string3, 0, _jsp_string3.length);
    out.print((BeanName  ));
    out.write(_jsp_string4, 0, _jsp_string4.length);
    out.print((IDField ));
    out.write(_jsp_string5, 0, _jsp_string5.length);
    out.print((IDField ));
    out.write(_jsp_string6, 0, _jsp_string6.length);
    out.print((TableName ));
    out.write(_jsp_string7, 0, _jsp_string7.length);
    out.print((TableName ));
    out.write(_jsp_string8, 0, _jsp_string8.length);
    out.print((IDField ));
    out.write(_jsp_string9, 0, _jsp_string9.length);
    out.print((BeanName ));
    out.write(_jsp_string10, 0, _jsp_string10.length);
    out.print((IDField ));
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((BeanName ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print((BeanName ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    out.print((BeanName ));
    out.write(_jsp_string14, 0, _jsp_string14.length);
    out.print((TableName ));
    out.write(_jsp_string15, 0, _jsp_string15.length);
    out.print((TableName ));
    out.write(_jsp_string16, 0, _jsp_string16.length);
  }

  private com.caucho.make.DependencyContainer _caucho_depends
    = new com.caucho.make.DependencyContainer();

  public java.util.ArrayList<com.caucho.vfs.Dependency> _caucho_getDependList()
  {
    return _caucho_depends.getDependencies();
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    _caucho_depends.add(depend);
  }

  protected void _caucho_setNeverModified(boolean isNotModified)
  {
    _caucho_isNotModified = true;
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;

    if (_caucho_isNotModified)
      return false;

    if (com.caucho.server.util.CauchoSystem.getVersionId() != 8324212715306645294L)
      return true;

    return _caucho_depends.isModified();
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
    TagState tagState;
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("user/jsp/include-page/checkbox-process.jsp"), 6041692679725149300L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
  }

  final static class TagState {

    void release()
    {
    }
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void caucho_init(ServletConfig config)
  {
    try {
      com.caucho.server.webapp.WebApp webApp
        = (com.caucho.server.webapp.WebApp) config.getServletContext();
      init(config);
      if (com.caucho.jsp.JspManager.getCheckInterval() >= 0)
        _caucho_depends.setCheckInterval(com.caucho.jsp.JspManager.getCheckInterval());
      _jsp_pageManager = webApp.getJspApplicationContext().getPageManager();
      com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
      com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.InitPageContextImpl(webApp, this);
    } catch (Exception e) {
      throw com.caucho.config.ConfigException.create(e);
    }
  }

  private final static char []_jsp_string12;
  private final static char []_jsp_string2;
  private final static char []_jsp_string3;
  private final static char []_jsp_string8;
  private final static char []_jsp_string15;
  private final static char []_jsp_string14;
  private final static char []_jsp_string0;
  private final static char []_jsp_string1;
  private final static char []_jsp_string5;
  private final static char []_jsp_string13;
  private final static char []_jsp_string6;
  private final static char []_jsp_string10;
  private final static char []_jsp_string7;
  private final static char []_jsp_string9;
  private final static char []_jsp_string11;
  private final static char []_jsp_string4;
  private final static char []_jsp_string16;
  static {
    _jsp_string12 = ".nextRow())\r\n           {\r\n					   \r\n           }\r\n					 ".toCharArray();
    _jsp_string2 = ".apputil.MakeQueryString( request, \"".toCharArray();
    _jsp_string3 = "\" );\r\n	  qStr.addMultiSelectParam(".toCharArray();
    _jsp_string8 = "`.`".toCharArray();
    _jsp_string15 = "` \"+WhrCls+\" \") );\r\n				 MessageText = \" ( \"+del_cnt+\" ) records are deleted.\" ;\r\n				}\r\n				else\r\n				{\r\n				  MessageText = \"Record deletion not permitted.\" ;\r\n				}\r\n		}\r\n		\r\n		if(\"Activity\".equalsIgnoreCase(CheckedAction) && chk_count > 0 )\r\n		{\r\n		      String WhereParam  = RequestHelper.encodeBase64( WhrCls.getBytes()) ;\r\n          String ReturnPath = new String( com.webapp.base64.UrlBase64.encode( ( thisFile+\"?Action=Default\" ).getBytes() ) );\r\n          String redirect  = \"".toCharArray();
    _jsp_string14 = ".executeUpdate( qStr.SQL( \"DELETE FROM `".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n if(nAction == PROCESS_CHECKED)\r\n {\r\n    qStr  = new ".toCharArray();
    _jsp_string1 = ".apputil.appMakeQueryString( request, application );\r\n		//  qStr  = new ".toCharArray();
    _jsp_string5 = "\", false  );\r\n	  String CheckedAction = request.getParameter(\"CheckedAction\");\r\n		\r\n		String chk_items[] = request.getParameterValues(\"".toCharArray();
    _jsp_string13 = ".closeTable();\r\n				*/\r\n				if(bAllowDelete == true)\r\n				{\r\n				 int del_cnt = ".toCharArray();
    _jsp_string6 = "\");\r\n	  int chk_count = 0;\r\n	  chk_count  = (chk_items != null ) ? chk_items.length : 0 ;\r\n	  String WhrCls =qStr.getWhereClause() ;\r\n	  String ChkQuery = qStr.SQL( \" SELECT * FROM  `".toCharArray();
    _jsp_string10 = ".openTable(WhrCls, \" ORDER BY `".toCharArray();
    _jsp_string7 = "` \"+ WhrCls + \" ORDER BY `".toCharArray();
    _jsp_string9 = "` \" );\r\n	  \r\n		if(\"Delete\".equalsIgnoreCase(CheckedAction) && chk_count > 0  )\r\n		{\r\n		    /* Poll through records to be deleted in case values are there in dependent table \r\n				\r\n				   ".toCharArray();
    _jsp_string11 = "` \");\r\n           while(".toCharArray();
    _jsp_string4 = "._tablename, \"".toCharArray();
    _jsp_string16 = "-selection-activity.jsp?Count=\"+chk_count+\"&WhereClause=\"+WhereParam+\"&ReturnPath=\"+ReturnPath; \r\n          response.sendRedirect(response.encodeRedirectURL(redirect)); \r\n		}\r\n\r\n		/* \r\n		  // Forwared Base64 encoded sql to other page if relevent\r\n		   String SQLPARAM = new String( com.webapp.base64.UrlBase64.encode(ChkQuery.getBytes() ) );\r\n       String ReturnPath = new String( com.webapp.base64.UrlBase64.encode( ( thisFile ).getBytes() ) );\r\n       String redirect  = appPath+\"/$PATH.jsp?Count=\"+chk_count+\"&SQL=\"+SQLPARAM+\"&ReturnPath=\"+ReturnPath; //  &Target=Target&MobileField=Mobile&EmailField=Email  \r\n       response.sendRedirect(response.encodeRedirectURL(redirect)); \r\n		\r\n		*/\r\n     nAction=default_cmd ;\r\n }\r\n\r\n".toCharArray();
  }
}
