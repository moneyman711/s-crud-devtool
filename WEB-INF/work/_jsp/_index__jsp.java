/*
 * JSP generated by Resin-4.0.47 (built Thu, 03 Dec 2015 10:34:34 PST)
 */

package _jsp;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import com.beanwiz.*;

public class _index__jsp extends com.caucho.jsp.JavaPage
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
    com.caucho.jsp.PageContextImpl pageContext = _jsp_pageManager.allocatePageContext(this, _jsp_application, request, response, "/errorpage.jsp", session, 8192, true, false);

    TagState _jsp_state = new TagState();

    try {
      _jspService(request, response, pageContext, _jsp_application, session, _jsp_state);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_state.release();
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
    com.validation.ValidateTag _jsp_ValidateTag_0 = null;

    out.write(_jsp_string0, 0, _jsp_string0.length);
    _jsp_ValidateTag_0 = _jsp_state.get_jsp_ValidateTag_0(pageContext, _jsp_parent_tag);
    _jsp_ValidateTag_0.doStartTag();
    out.write(_jsp_string1, 0, _jsp_string1.length);
     
String appPath = request.getContextPath() ;
String thisFile = appPath+request.getServletPath() ;

com.beanwiz.LoggedUser LogUsr  = (com.beanwiz.LoggedUser)session.getAttribute("theWizardUser");
if(LogUsr == null ) LogUsr =  LoginHelper.autoLoginCheck(application,session,request );

    out.write(_jsp_string2, 0, _jsp_string2.length);
    pageContext.include("/assets/include-page/common/meta-tag.jsp", true);out.write(_jsp_string3, 0, _jsp_string3.length);
    pageContext.include("/assets/include-page/css/main-css.jsp", true);out.write(_jsp_string4, 0, _jsp_string4.length);
    pageContext.include("/assets/include-page/common/main-head-js.jsp", true);out.write(_jsp_string5, 0, _jsp_string5.length);
    pageContext.include("/header.jsp", true);out.write(_jsp_string6, 0, _jsp_string6.length);
    out.print((appPath ));
    out.write(_jsp_string7, 0, _jsp_string7.length);
    out.print((appPath ));
    out.write(_jsp_string8, 0, _jsp_string8.length);
    pageContext.include("/assets/include-page/common/footer.jsp", true);out.write(_jsp_string1, 0, _jsp_string1.length);
    pageContext.include("/assets/include-page/js/main-js.jsp", true);out.write(_jsp_string1, 0, _jsp_string1.length);
    pageContext.include("/assets/include-page/common/Google-Analytics.jsp", true);out.write(_jsp_string9, 0, _jsp_string9.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("index.jsp"), -4676885180482633355L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
    depend = new com.caucho.vfs.Depend(mergePath.lookup("file:/c:/ResinApps/common/tld/validation.tld"), -7790462880171604110L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
    _caucho_depends.add(new com.caucho.make.ClassDependency("com.validation.ValidateTag", -8980947769165573932L));
  }

  static {
    try {
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  final static class TagState {
    private com.validation.ValidateTag _jsp_ValidateTag_0;

    final com.validation.ValidateTag get_jsp_ValidateTag_0(PageContext pageContext, javax.servlet.jsp.tagext.JspTag _jsp_parent_tag) throws Throwable
    {
      if (_jsp_ValidateTag_0 == null) {
        _jsp_ValidateTag_0 = new com.validation.ValidateTag();
        _jsp_ValidateTag_0.setPageContext(pageContext);
        if (_jsp_parent_tag instanceof javax.servlet.jsp.tagext.Tag)
          _jsp_ValidateTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_parent_tag);
        else if (_jsp_parent_tag instanceof javax.servlet.jsp.tagext.SimpleTag)
          _jsp_ValidateTag_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jsp_parent_tag));
        else
          _jsp_ValidateTag_0.setParent((javax.servlet.jsp.tagext.Tag) null);
      }

      return _jsp_ValidateTag_0;
    }

    void release()
    {
      if (_jsp_ValidateTag_0 != null) {
        _jsp_ValidateTag_0.release();
        _jsp_ValidateTag_0 = null;
      }
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
      manager.addTaglibFunctions(_jsp_functionMap, "chk", "validation");
      com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.InitPageContextImpl(webApp, this);
    } catch (Exception e) {
      throw com.caucho.config.ConfigException.create(e);
    }
  }

  private final static char []_jsp_string4;
  private final static char []_jsp_string2;
  private final static char []_jsp_string9;
  private final static char []_jsp_string0;
  private final static char []_jsp_string8;
  private final static char []_jsp_string5;
  private final static char []_jsp_string6;
  private final static char []_jsp_string1;
  private final static char []_jsp_string3;
  private final static char []_jsp_string7;
  static {
    _jsp_string4 = "\r\n  ".toCharArray();
    _jsp_string2 = "\r\n\r\n<!DOCTYPE HTML>\r\n<html class=\"no-js\" lang=\"en\">\r\n<head>\r\n  ".toCharArray();
    _jsp_string9 = "\r\n</body>\r\n</html>".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n".toCharArray();
    _jsp_string8 = "/user/other/index.jsp\" role=\"button\"><i aria-hidden=\"true\" class=\"icon glyphicon glyphicon-wrench\"></i>&nbsp;&nbsp;Other Dev. Tools</a> -->\r\n			</div>			\r\n    </div>	\r\n  </div>\r\n  </div>\r\n</div>	\r\n\r\n<div class=\"row\">\r\n  <div class=\"col-sm-4 animated bounceInUp\">\r\n	<div class=\"panel panel-default\">\r\n        <div class=\"panel-heading text-center\">\r\n          <h4 class=\"text-primary text-left\" ><span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>&nbsp;&nbsp;What is It ?</h4>\r\n				</div>	\r\n        <div class=\"panel-body\">\r\n				  <p class=\"text-muted lead\">\r\n	           This Simplifies Tedious job of \r\n						 <ul class=\"list-group\">\r\n                <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-menu-right\" aria-hidden=\"true\"></span>&nbsp;&nbsp;Writing Boilerplate</li>\r\n                <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-menu-right\" aria-hidden=\"true\"></span>&nbsp;&nbsp;Repetitive Code</li>\r\n								<li class=\"list-group-item\"><span class=\"glyphicon glyphicon-menu-right\" aria-hidden=\"true\"></span>&nbsp;&nbsp;Access Database</li>\r\n             </ul>\r\n	        </p>\r\n				</div>\r\n	</div>			\r\n	</div>\r\n	\r\n  <div class=\"col-sm-4 animated bounceIn\">\r\n	<div class=\"panel panel-default\">\r\n        <div class=\"panel-heading text-center\">\r\n          <h4 class=\"text-primary text-left\" ><span class=\"glyphicon glyphicon-tasks\" aria-hidden=\"true\"></span>&nbsp;&nbsp;CRUD Operation</h4>\r\n				</div>	\r\n        <div class=\"panel-body\">\r\n          <ul class=\"list-group\">\r\n            <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>&nbsp;&nbsp;CREATE</li>\r\n            <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-eye-open\" aria-hidden=\"true\"></span>&nbsp;&nbsp;READ</li>\r\n            <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span>&nbsp;&nbsp;UPDATE</li>\r\n            <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>&nbsp;&nbsp;DELETE</li>\r\n          	<li class=\"list-group-item list-group-item-info\">& Include</li>\r\n          	<li class=\"list-group-item\"><span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>&nbsp;&nbsp;SEARCH</li>\r\n          </ul>					\r\n				</div>\r\n	</div>			\r\n	</div>\r\n	\r\n  <div class=\"col-sm-4 animated bounceInDown\">\r\n	<div class=\"panel panel-default\">\r\n        <div class=\"panel-heading text-center\">\r\n          <h4 class=\"text-primary text-left\" ><span class=\"glyphicon glyphicon-oil\" aria-hidden=\"true\"></span>&nbsp;&nbsp;Database</h4>\r\n				</div>	\r\n        <div class=\"panel-body\">\r\n          <ul class=\"list-group\">\r\n            <li class=\"list-group-item\"><code>MySQL</code></li>\r\n            <li class=\"list-group-item\"><code>PostgreSQL</code></li>\r\n            <li class=\"list-group-item\"><code>Microsoft SQL</code></li>\r\n            <li class=\"list-group-item\"><code>Oracle</code></li>\r\n            <li class=\"list-group-item\"><code>IBM DB2</code></li>\r\n          	<li class=\"list-group-item list-group-item-info\">Embedded Database</li>\r\n          	<li class=\"list-group-item\"><code>SQlite</code></li>\r\n          	<li class=\"list-group-item\"><code>H2</code></li>\r\n          </ul>			\r\n	      </div>\r\n	</div>				\r\n	</div>	\r\n</div>\r\n		\r\n</div>\r\n<!-- /container -->\r\n\r\n".toCharArray();
    _jsp_string5 = " \r\n</head>\r\n<body>\r\n".toCharArray();
    _jsp_string6 = "\r\n\r\n<div class=\"container\">\r\n\r\n<div class=\"row \">\r\n  <div class=\"col-sm-12\">\r\n  <div class=\"jumbotron\">\r\n	  <div class=\"row \">\r\n		  <div class=\"col-sm-8 animated slideInRight\">\r\n  			  <h2>Development Tool</h2>\r\n  		    <h4><code>JSP / SERVLET</code></h4>\r\n					<h4><code>IWGLVJ-623</code> | <code>QSZMNZ-1045</code></h4>\r\n			</div>\r\n		  <div class=\"col-sm-3 animated slideInLeft\">\r\n             <a class=\"btn btn-lg btn-primary btn-block\" href=\"".toCharArray();
    _jsp_string1 = "\r\n".toCharArray();
    _jsp_string3 = "\r\n  <title>Development Tool</title>\r\n  ".toCharArray();
    _jsp_string7 = "/user/index.jsp\" role=\"button\"><i aria-hidden=\"true\" class=\"icon glyphicon glyphicon-check\"></i>&nbsp;&nbsp;Let's Do It !</a>\r\n             <!-- <a class=\"btn btn-lg btn-primary btn-block\" href=\"".toCharArray();
  }
}