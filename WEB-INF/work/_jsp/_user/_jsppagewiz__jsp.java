/*
 * JSP generated by Resin-4.0.47 (built Thu, 03 Dec 2015 10:34:34 PST)
 */

package _jsp._user;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import com.beanwiz.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;
import javax.sql.*;
import com.webapp.utils.*;

public class _jsppagewiz__jsp extends com.caucho.jsp.JavaPage
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

    out.write(_jsp_string0, 0, _jsp_string0.length);
     
String appPath = request.getContextPath() ;
String thisFile = appPath+request.getServletPath() ;

com.beanwiz.LoggedUser LogUsr  = (com.beanwiz.LoggedUser)session.getAttribute("theWizardUser");
if(LogUsr == null ) LogUsr =  LoginHelper.autoLoginCheck(application,session,request );

response.setDateHeader("Expires", 0 );
response.setHeader("Cache-Control", "no-cache, post-check=0, pre-check=0"); 
response.setHeader("Pragma", "no-cache"); 

String JNDIDSN= request.getParameter("JNDIDSN");
String WebAppName = request.getParameter("WebAppName");
String TableName = request.getParameter("TableName");
String DriverName= request.getParameter("DriverName");

char First  = TableName.charAt(0);
if(First > 96) First-=32 ;

char TFirst  = TableName.charAt(0);
if(TFirst > 96) TFirst-=32 ;
String CTableName = TFirst+TableName.substring(1) ;

String BeanName = First+TableName.substring(1,2)+"Bn" ;
String BeanClass = First+TableName.substring(1)+"Bean" ;
String BeanPackage  = "com.db."+JNDIDSN.substring(JNDIDSN.indexOf("/",0)+1 );

boolean bAuto =( request.getParameter("IsAuto")!=null)? true:false ;
String IDField = request.getParameter("IDField");
String IDFieldType = request.getParameter("IDFieldType") ;

String Database=null;
Context env = (Context) new InitialContext().lookup("java:comp/env");
DataSource source = (DataSource) env.lookup(JNDIDSN);
Connection conn = source.getConnection();
Database=conn.getCatalog();

String PK="?";
boolean bDateSupport = false;
boolean bTimeSupport = false;
boolean bTimestampSupport = false;
String WebApp = "com."+WebAppName ;

String query = BeanwizHelper.openTableSQL(conn, TableName);
try 
{
java.sql.Statement stmt = conn.createStatement();
java.sql.ResultSet rslt = stmt.executeQuery(query);
java.sql.ResultSetMetaData rsmd = rslt.getMetaData();
int count  = rsmd.getColumnCount();

for(int n = 1 ; n <= count ; n++ )
{	
	if( rsmd.isAutoIncrement(n)) PK=rsmd.getColumnName(n) ;
}// end for() 


    out.write(_jsp_string1, 0, _jsp_string1.length);
    pageContext.include("/assets/include-page/common/meta-tag.jsp", true);out.write(_jsp_string2, 0, _jsp_string2.length);
    pageContext.include("/assets/include-page/css/main-css.jsp", true);out.write(_jsp_string3, 0, _jsp_string3.length);
    pageContext.include("/assets/include-page/common/main-head-js.jsp", true);out.write(_jsp_string4, 0, _jsp_string4.length);
    pageContext.include("/user/assets-user/include-page/header-user.jsp", true);out.write(_jsp_string5, 0, _jsp_string5.length);
    out.print((appPath ));
    out.write(_jsp_string6, 0, _jsp_string6.length);
    out.print((JNDIDSN ));
    out.write(_jsp_string7, 0, _jsp_string7.length);
    out.print((WebAppName ));
    out.write(_jsp_string8, 0, _jsp_string8.length);
    out.print((Database ));
    out.write(_jsp_string9, 0, _jsp_string9.length);
    out.print((Database ));
    out.write('.');
    out.print((TableName ));
    out.write(_jsp_string10, 0, _jsp_string10.length);
    out.print((JNDIDSN ));
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((appPath ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print((DriverName ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    out.print((JNDIDSN ));
    out.write(_jsp_string14, 0, _jsp_string14.length);
    out.print((TableName ));
    out.write(_jsp_string15, 0, _jsp_string15.length);
    out.print((BeanName ));
    out.write(_jsp_string16, 0, _jsp_string16.length);
    out.print((BeanClass ));
    out.write(_jsp_string17, 0, _jsp_string17.length);
    out.print((BeanPackage ));
    out.write(_jsp_string18, 0, _jsp_string18.length);
    out.print((WebApp ));
    out.write(_jsp_string19, 0, _jsp_string19.length);
    out.print((CTableName ));
    out.write(_jsp_string20, 0, _jsp_string20.length);
    out.print((TableName ));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    
for(int n = 1 ; n <= count ; n++ )
{	
	int ColSQLType = rsmd.getColumnType(n);
	if(ColSQLType==java.sql.Types.TIMESTAMP)bTimestampSupport=true;
}

    out.write(_jsp_string22, 0, _jsp_string22.length);
     if(bTimestampSupport){ 
    out.write(_jsp_string23, 0, _jsp_string23.length);
     } 
    out.write(_jsp_string24, 0, _jsp_string24.length);
    out.print((CTableName ));
    out.write(_jsp_string25, 0, _jsp_string25.length);
    
      for(int n = 1 ; n <= count ; n++ )
      {	
      	 String ColName = rsmd.getColumnName(n) ;
      	 String ColType = rsmd.getColumnTypeName(n);
      	 int ColSQLType = rsmd.getColumnType(n);
      	 if( rsmd.isAutoIncrement(n))PK=ColName; ;
      	 if(ColSQLType==java.sql.Types.DATE) bDateSupport=true;
				 if(ColSQLType==java.sql.Types.TIME) bTimeSupport=true;
      	 if(ColSQLType==java.sql.Types.TIMESTAMP)bTimestampSupport=true;
      
    out.write(_jsp_string26, 0, _jsp_string26.length);
    out.print((n ));
    out.write(_jsp_string27, 0, _jsp_string27.length);
    out.print((ColName ));
     if( ColName.equals(PK)) { 
    out.write(_jsp_string28, 0, _jsp_string28.length);
     } 
    out.write(_jsp_string29, 0, _jsp_string29.length);
    out.print((ColName ));
    out.write(_jsp_string30, 0, _jsp_string30.length);
    out.print((n ));
    out.write(_jsp_string31, 0, _jsp_string31.length);
     if( rsmd.isAutoIncrement(n)){ 
    out.write(_jsp_string32, 0, _jsp_string32.length);
     }else{ 
    out.write(_jsp_string33, 0, _jsp_string33.length);
     } 
    out.write(_jsp_string34, 0, _jsp_string34.length);
    out.print((n ));
    out.write(_jsp_string35, 0, _jsp_string35.length);
    out.print((ColName ));
    out.write(_jsp_string36, 0, _jsp_string36.length);
    out.print((n ));
    out.write(_jsp_string31, 0, _jsp_string31.length);
     if( rsmd.isAutoIncrement(n)){ 
    out.write(_jsp_string37, 0, _jsp_string37.length);
     }else{ 
    out.write(_jsp_string38, 0, _jsp_string38.length);
     } 
    out.write(_jsp_string39, 0, _jsp_string39.length);
    out.print((n ));
    out.write(_jsp_string40, 0, _jsp_string40.length);
    out.print((ColName ));
    out.write(_jsp_string41, 0, _jsp_string41.length);
    out.print((n ));
    out.write(_jsp_string31, 0, _jsp_string31.length);
     if( rsmd.isAutoIncrement(n)){ 
    out.write(_jsp_string42, 0, _jsp_string42.length);
     }else{ 
    out.write(_jsp_string43, 0, _jsp_string43.length);
     } 
    out.write(_jsp_string44, 0, _jsp_string44.length);
    out.print((n ));
    out.write(_jsp_string45, 0, _jsp_string45.length);
    out.print((n ));
    out.write(_jsp_string46, 0, _jsp_string46.length);
    out.print((n ));
    out.write(_jsp_string31, 0, _jsp_string31.length);
     if( rsmd.isAutoIncrement(n)){ 
    out.write(_jsp_string47, 0, _jsp_string47.length);
     }else{ 
    out.write(_jsp_string48, 0, _jsp_string48.length);
     } 
    out.write(_jsp_string49, 0, _jsp_string49.length);
    out.print((n ));
    out.write(_jsp_string50, 0, _jsp_string50.length);
     if(n%1 ==0 ) out.print("</tr><tr>");  
    out.write(_jsp_string51, 0, _jsp_string51.length);
    	  
      }// end for(int n = 1 ; n <= count ; n++ ) 
      
    out.write(_jsp_string52, 0, _jsp_string52.length);
    out.print((appPath ));
    out.write(_jsp_string53, 0, _jsp_string53.length);
    out.print((JNDIDSN ));
    out.write(_jsp_string54, 0, _jsp_string54.length);
    out.print((TableName ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((appPath ));
    out.write(_jsp_string56, 0, _jsp_string56.length);
    out.print((JNDIDSN ));
    out.write(_jsp_string54, 0, _jsp_string54.length);
    out.print((TableName ));
    out.write(_jsp_string57, 0, _jsp_string57.length);
    out.print((appPath ));
    out.write(_jsp_string58, 0, _jsp_string58.length);
    out.print((JNDIDSN ));
    out.write(_jsp_string54, 0, _jsp_string54.length);
    out.print((TableName ));
    out.write(_jsp_string59, 0, _jsp_string59.length);
    pageContext.include("/assets/include-page/js/main-js.jsp", true);out.write(_jsp_string60, 0, _jsp_string60.length);
    out.print((PK ));
    out.write(_jsp_string61, 0, _jsp_string61.length);
     if(!"?".equals(PK)){ 
    out.write(_jsp_string62, 0, _jsp_string62.length);
     } 
    out.write(_jsp_string63, 0, _jsp_string63.length);
    out.print((Database ));
    out.write(_jsp_string64, 0, _jsp_string64.length);
     if(bDateSupport)
 { 
    out.write(_jsp_string65, 0, _jsp_string65.length);
      } 
if(bTimeSupport)
 { 
    out.write(_jsp_string66, 0, _jsp_string66.length);
      }
if(bTimestampSupport)
 { 
    out.write(_jsp_string67, 0, _jsp_string67.length);
      } 
    out.write(_jsp_string68, 0, _jsp_string68.length);
     
rslt.close();
stmt.close();
}	 
finally
{
conn.close();
} 


    out.write(_jsp_string69, 0, _jsp_string69.length);
    pageContext.include("/assets/include-page/common/footer.jsp", true);out.write(_jsp_string70, 0, _jsp_string70.length);
    pageContext.include("/assets/include-page/js/tabdrop-js.jsp", true);out.write(_jsp_string71, 0, _jsp_string71.length);
    pageContext.include("/assets/include-page/js/iframe-resizer/iframeResizer-js.jsp", true);out.write(_jsp_string69, 0, _jsp_string69.length);
    pageContext.include("/assets/include-page/common/Google-Analytics.jsp", true);out.write(_jsp_string72, 0, _jsp_string72.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("user/jsppagewiz.jsp"), -4060782723419928628L, false);
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

  private final static char []_jsp_string33;
  private final static char []_jsp_string50;
  private final static char []_jsp_string56;
  private final static char []_jsp_string9;
  private final static char []_jsp_string34;
  private final static char []_jsp_string28;
  private final static char []_jsp_string62;
  private final static char []_jsp_string15;
  private final static char []_jsp_string43;
  private final static char []_jsp_string32;
  private final static char []_jsp_string12;
  private final static char []_jsp_string31;
  private final static char []_jsp_string26;
  private final static char []_jsp_string1;
  private final static char []_jsp_string57;
  private final static char []_jsp_string58;
  private final static char []_jsp_string52;
  private final static char []_jsp_string72;
  private final static char []_jsp_string17;
  private final static char []_jsp_string68;
  private final static char []_jsp_string64;
  private final static char []_jsp_string60;
  private final static char []_jsp_string67;
  private final static char []_jsp_string24;
  private final static char []_jsp_string54;
  private final static char []_jsp_string48;
  private final static char []_jsp_string47;
  private final static char []_jsp_string3;
  private final static char []_jsp_string18;
  private final static char []_jsp_string42;
  private final static char []_jsp_string13;
  private final static char []_jsp_string21;
  private final static char []_jsp_string49;
  private final static char []_jsp_string35;
  private final static char []_jsp_string30;
  private final static char []_jsp_string7;
  private final static char []_jsp_string36;
  private final static char []_jsp_string45;
  private final static char []_jsp_string59;
  private final static char []_jsp_string14;
  private final static char []_jsp_string23;
  private final static char []_jsp_string16;
  private final static char []_jsp_string11;
  private final static char []_jsp_string27;
  private final static char []_jsp_string46;
  private final static char []_jsp_string8;
  private final static char []_jsp_string10;
  private final static char []_jsp_string41;
  private final static char []_jsp_string40;
  private final static char []_jsp_string65;
  private final static char []_jsp_string55;
  private final static char []_jsp_string39;
  private final static char []_jsp_string22;
  private final static char []_jsp_string63;
  private final static char []_jsp_string25;
  private final static char []_jsp_string6;
  private final static char []_jsp_string71;
  private final static char []_jsp_string2;
  private final static char []_jsp_string38;
  private final static char []_jsp_string70;
  private final static char []_jsp_string44;
  private final static char []_jsp_string19;
  private final static char []_jsp_string61;
  private final static char []_jsp_string53;
  private final static char []_jsp_string37;
  private final static char []_jsp_string66;
  private final static char []_jsp_string0;
  private final static char []_jsp_string29;
  private final static char []_jsp_string5;
  private final static char []_jsp_string4;
  private final static char []_jsp_string20;
  private final static char []_jsp_string69;
  private final static char []_jsp_string51;
  static {
    _jsp_string33 = "class=\"SEARCH_chkbox\"".toCharArray();
    _jsp_string50 = "\">&nbsp;</label>\r\n      </span>\r\n      </td> \r\n      ".toCharArray();
    _jsp_string56 = "/user/forms/fieldlabels.jsp?JNDIDSN=".toCharArray();
    _jsp_string9 = "</strong></a></li>\r\n      <li class=\"active\">JSP Page</li>\r\n    </ol>\r\n  </div>\r\n</div>\r\n<hr class=\"pageheaderHR\" />\r\n\r\n<div class=\"well well-sm row-fluid-text\">\r\n\r\n  <div class=\"row\">\r\n    <div class=\"col-md-6\">\r\n      <big><i class=\"fa fa-table fa-lg text-primary\"></i>&nbsp;&nbsp;<span class=\"text-info\">Table : </span><span class=\"text-muted\">".toCharArray();
    _jsp_string34 = ">\r\n          <label for=\"SEARCH_FIELD_COL_".toCharArray();
    _jsp_string28 = "&nbsp;&nbsp;<span class=\"label label-default\">A</span>".toCharArray();
    _jsp_string62 = " \r\nvar obIsAuto = document.getElementById(\"IsAuto\");\r\nobIsAuto.checked=true;\r\n".toCharArray();
    _jsp_string15 = "\" />\r\n\r\n  <div class=\"form-group\">\r\n    <label for=\"BeanName\" class=\"col-sm-4 control-label\">Bean Name</label>\r\n    <div class=\"col-sm-8\">\r\n      <input type=\"text\" name=\"BeanName\" id=\"BeanName\" value=\"".toCharArray();
    _jsp_string43 = "class=\"FOREIGN_chkbox\"".toCharArray();
    _jsp_string32 = "class=\"auto_SEARCH_chkbox\"".toCharArray();
    _jsp_string12 = "/getjsp\" method=\"post\"  id=\"beanform\" name=\"beanform\" target=\"_blank\" >\r\n<input type=\"hidden\" name=\"DriverName\" value=\"".toCharArray();
    _jsp_string31 = "\" ".toCharArray();
    _jsp_string26 = "\r\n			<td>".toCharArray();
    _jsp_string1 = "\r\n<!DOCTYPE HTML>\r\n<!--[if lt IE 7]>      <html class=\"no-js lt-ie9 lt-ie8 lt-ie7\" lang=\"en\"> <![endif]-->\r\n<!--[if IE 7]>         <html class=\"no-js lt-ie9 lt-ie8\" lang=\"en\"> <![endif]-->\r\n<!--[if IE 8]>         <html class=\"no-js lt-ie9\" lang=\"en\"> <![endif]-->\r\n<!--[if gt IE 8]><!--> <html class=\"no-js\" lang=\"en\"> <!--<![endif]-->\r\n<head>\r\n".toCharArray();
    _jsp_string57 = "\" frameborder=\"0\" width=\"100%\" scrolling=\"no\" class=\"iFrame_Resize\" ></iframe>		\r\n\r\n</div>		\r\n		\r\n\r\n<div id=\"FormValidation\" class=\"tab-pane\">\r\n\r\n     <iframe src=\"".toCharArray();
    _jsp_string58 = "/user/forms/fieldinputs.jsp?JNDIDSN=".toCharArray();
    _jsp_string52 = "\r\n					\r\n        </tr>\r\n        </tbody>\r\n        </table>\r\n        </div>\r\n\r\n			</div>\r\n\r\n</div>\r\n\r\n</form>	\r\n\r\n<div id=\"FormValidate\" class=\"tab-pane active\">\r\n\r\n     <iframe src=\"".toCharArray();
    _jsp_string72 = "\r\n</body>\r\n</html>\r\n".toCharArray();
    _jsp_string17 = "\" >\r\n    </div>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <label for=\"BeanPackage\" class=\"col-sm-4 control-label\">Bean Package</label>\r\n    <div class=\"col-sm-8\">\r\n			<input type=\"text\" name=\"BeanPackage\" class=\"form-control\" value=\"".toCharArray();
    _jsp_string68 = "\r\n\r\n$(\"#Result_checkall\").click(function () {\r\n   if ($(\"#Result_checkall\").is(':checked')) {\r\n        $(\".Result_chkbox\").each(function () {\r\n          $(this).prop(\"checked\", true);\r\n      });\r\n   } else {\r\n       $(\".Result_chkbox\").each(function () {\r\n        $(this).prop(\"checked\", false);\r\n      });\r\n   }\r\n});\r\n$(\"#Search_checkall\").click(function () {\r\n   if ($(\"#Search_checkall\").is(':checked')) {\r\n        $(\".SEARCH_chkbox\").each(function () {\r\n          $(this).prop(\"checked\", true);\r\n      });\r\n   } else {\r\n       $(\".SEARCH_chkbox\").each(function () {\r\n        $(this).prop(\"checked\", false);\r\n      });\r\n   }\r\n});\r\n$(\"#Foreign_checkall\").click(function () {\r\n   if ($(\"#Foreign_checkall\").is(':checked')) {\r\n        $(\".FOREIGN_chkbox\").each(function () {\r\n          $(this).prop(\"checked\", true);\r\n      });\r\n   } else {\r\n       $(\".FOREIGN_chkbox\").each(function () {\r\n        $(this).prop(\"checked\", false);\r\n      });\r\n   }\r\n});	 \r\n$(\"#Duplicate_checkall\").click(function () {\r\n   if ($(\"#Duplicate_checkall\").is(':checked')) {\r\n        $(\".DUPLICATE_chkbox\").each(function () {\r\n          $(this).prop(\"checked\", true);\r\n      });\r\n   } else {\r\n       $(\".DUPLICATE_chkbox\").each(function () {\r\n        $(this).prop(\"checked\", false);\r\n      });\r\n   }\r\n});\r\n\r\n\r\n</script>\r\n\r\n	\r\n	\r\n	</div>\r\n</div>				\r\n\r\n</div> <!-- /container -->\r\n\r\n".toCharArray();
    _jsp_string64 = "\" ;\r\n// -->\r\n".toCharArray();
    _jsp_string60 = "\r\n<script type=\"text/javascript\">\r\n<!--\r\n\r\nvar obIDField = document.getElementById(\"IDField\");\r\nobIDField.value=\"".toCharArray();
    _jsp_string67 = "\r\ndocument.forms['beanform'].elements['HasTimestamp'].value =\"YES\" ;\r\n".toCharArray();
    _jsp_string24 = "	\r\n  <div class=\"form-group\">\r\n    <label class=\"col-sm-4 control-label\">SMS ?</label>\r\n    <div class=\"col-sm-8\">\r\n			<span class=\"checkbox checkbox-primary\">\r\n      <input type=\"checkbox\" name=\"ConfigSMS\" id=\"ConfigSMS\" />\r\n      <label for=\"ConfigSMS\">SMS Patch ?</label>\r\n      </span>\r\n    </div>\r\n  </div>	\r\n\r\n  <div class=\"form-group\">\r\n    <label for=\"EntityName\" class=\"col-sm-4 control-label\">Entity Name</label>\r\n    <div class=\"col-sm-8\">\r\n			<input type=\"text\" class=\"form-control\" name=\"EntityName\"  id=\"EntityName\" value=\"".toCharArray();
    _jsp_string54 = "&TableName=".toCharArray();
    _jsp_string48 = "class=\"DUPLICATE_chkbox\"".toCharArray();
    _jsp_string47 = "class=\"auto_DUPLICATE_chkbox\"".toCharArray();
    _jsp_string3 = "		\r\n".toCharArray();
    _jsp_string18 = "\" id=\"BeanPackage\" >\r\n    </div>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <label for=\"WebApp\" class=\"col-sm-4 control-label\">WebApp Package</label>\r\n    <div class=\"col-sm-8\">\r\n			<input type=\"text\" class=\"form-control\" name=\"WebApp\"  id=\"WebApp\" value=\"".toCharArray();
    _jsp_string42 = "class=\"auto_FOREIGN_chkbox\"".toCharArray();
    _jsp_string13 = "\" />\r\n<input type=\"hidden\" name=\"HasDate\" value=\"NO\" />\r\n<input type=\"hidden\" name=\"HasTime\" value=\"NO\" />\r\n<input type=\"hidden\" name=\"HasTimestamp\" value=\"NO\" />\r\n<input type=\"hidden\" name=\"JNDIDSN\" value=\"".toCharArray();
    _jsp_string21 = ".jsp\" >\r\n    </div>\r\n  </div>	\r\n  <div class=\"form-group\">\r\n    <label class=\"col-sm-4 control-label\">Check Boxe ?</label>\r\n    <div class=\"col-sm-8\">\r\n			<span class=\"checkbox checkbox-primary\">\r\n      <input type=\"checkbox\" name=\"CheckBox\" id=\"CheckBox\" value=\"TRUE\" checked=\"checked\" />\r\n      <label for=\"CheckBox\">Check boxes in Table</label>\r\n      </span>\r\n    </div>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <label class=\"col-sm-4 control-label\">Datatables ?</label>\r\n    <div class=\"col-sm-8\">\r\n			<span class=\"checkbox checkbox-primary\">\r\n      <input type=\"checkbox\" name=\"DatatablesJSCSS\" id=\"DatatablesJSCSS\" />\r\n      <label for=\"DatatablesJSCSS\">Include Datatables</label>\r\n      </span>\r\n    </div>\r\n  </div>	\r\n".toCharArray();
    _jsp_string49 = ">\r\n          <label for=\"DUPLICATE_FIELD_COL_".toCharArray();
    _jsp_string35 = "\">&nbsp;</label>\r\n      </span>\r\n      </td> \r\n      <td>\r\n      <span class=\"checkbox checkbox-inline checkbox-primary\">\r\n          <input type=\"checkbox\" name=\"ShowFields\" value=\"".toCharArray();
    _jsp_string30 = "\" id=\"SEARCH_FIELD_COL_".toCharArray();
    _jsp_string7 = "&WebAppName=".toCharArray();
    _jsp_string36 = "\" id=\"SHOW_FIELD_COL_".toCharArray();
    _jsp_string45 = "\">&nbsp;</label>\r\n      </span>\r\n      </td> \r\n      <td>\r\n      <span class=\"checkbox checkbox-inline checkbox-primary\">\r\n          <input type=\"checkbox\" name=\"DuplicateFields\" value=\"".toCharArray();
    _jsp_string59 = "\" frameborder=\"0\" width=\"100%\" scrolling=\"no\" class=\"iFrame_Resize\" ></iframe>		\r\n\r\n</div>		\r\n		\r\n</div>\r\n	\r\n	 </div>\r\n	 \r\n</div> \r\n	\r\n\r\n\r\n".toCharArray();
    _jsp_string14 = "\" />\r\n<input type=\"hidden\" name=\"TableName\" value=\"".toCharArray();
    _jsp_string23 = "	\r\n  <div class=\"form-group\">\r\n    <label class=\"col-sm-4 control-label\">DateTime ?</label>\r\n    <div class=\"col-sm-8\">\r\n			<span class=\"checkbox checkbox-primary\">\r\n      <input type=\"checkbox\" name=\"DateTimeJSCSS\" id=\"DateTimeJSCSS\"/>\r\n      <label for=\"DateTimeJSCSS\">DateTime JS/CSS ?</label>\r\n      </span>\r\n    </div>\r\n  </div>\r\n".toCharArray();
    _jsp_string16 = "\" class=\"form-control\" >\r\n    </div>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <label for=\"BeanClass\" class=\"col-sm-4 control-label\">Bean Class</label>\r\n    <div class=\"col-sm-8\">\r\n		  <input type=\"text\" name=\"BeanClass\" class=\"form-control\" id=\"BeanClass\" value=\"".toCharArray();
    _jsp_string11 = "</span></big>\r\n    </div>\r\n  </div>\r\n\r\n</div>		\r\n			\r\n<div class=\"row\">\r\n  <div class=\"col-md-5\">\r\n\r\n<form class=\"form-horizontal\" action=\"".toCharArray();
    _jsp_string27 = "</td>\r\n			<td>".toCharArray();
    _jsp_string46 = "\" id=\"DUPLICATE_FIELD_COL_".toCharArray();
    _jsp_string8 = "\">Table List : <strong>".toCharArray();
    _jsp_string10 = "</span></big>\r\n    </div>\r\n    <div class=\"col-md-6\">\r\n      <big class=\"pull-right\"><i class=\"fa fa-send-o fa-lg text-primary\"></i>&nbsp;&nbsp;<span class=\"text-info\">JNDI: </span><span class=\"text-muted\">".toCharArray();
    _jsp_string41 = "\" id=\"FOREIGN_FIELD_COL_".toCharArray();
    _jsp_string40 = "\">&nbsp;</label>\r\n      </span>\r\n      </td> \r\n      <td>\r\n      <span class=\"checkbox checkbox-inline checkbox-primary\">\r\n          <input type=\"checkbox\" name=\"ForeignFields\" value=\"".toCharArray();
    _jsp_string65 = "\r\ndocument.forms['beanform'].elements['HasDate'].value =\"YES\" ;\r\n".toCharArray();
    _jsp_string55 = "\" frameborder=\"0\" width=\"100%\" scrolling=\"no\" class=\"iFrame_Resize\" ></iframe>		\r\n\r\n</div>\r\n\r\n<div id=\"FieldLabels\" class=\"tab-pane\">\r\n\r\n     <iframe src=\"".toCharArray();
    _jsp_string39 = ">\r\n          <label for=\"SHOW_FIELD_COL_".toCharArray();
    _jsp_string22 = "	\r\n".toCharArray();
    _jsp_string63 = "\r\n\r\n\r\n// OLD code document.forms['beanform'].elements['WebApp'].value =\"com.\"+\"".toCharArray();
    _jsp_string25 = "\" >\r\n    </div>\r\n  </div>\r\n\r\n	\r\n	</div>\r\n  <div class=\"col-md-7\">\r\n<div class=\"well well-sm\">	\r\n<div class=\"row\">\r\n   <div class=\"col-md-8\">\r\n	<div class=\"row\"> \r\n  <div class=\"form-group col-md-12\">\r\n    <label for=\"OuputPage\" class=\"col-sm-4 control-label\">Output Page Type</label>\r\n    <div class=\"col-sm-8\">\r\n<select class=\"form-control\" name=\"OuputPage\" id=\"OuputPage\" ><!--   -->\r\n    <option value=\"AllInOne\" selected=\"selected\">All In One-SMS Path Available</option>\r\n		<option value=\"Listpage\" >Listpage</option>\r\n		<option value=\"Listpage-P\" >Listpage-P</option>\r\n		<option value=\"S-R-D-P\" >S-R-D-P</option>\r\n		<option value=\"S-R-D\" >S-R-D</option>\r\n		<option value=\"S-R-D-QryObj\" >S-R-D-QryObj</option>\r\n		<option value=\"R-D\" >R-D</option>\r\n		<option value=\"R-D-P\" >R-D-P</option>\r\n		<option value=\"R-D-POPUP\" >R-D-POPUP</option>\r\n		<option value=\"DataMatrix\" >Data Matrix</option>\r\n<!-- \r\n    <option value=\"SearchMultipleOld\">Old Style - Search ( Multiple Paginated )</option>\r\n    <option value=\"SearchSingle\">Search Page (Single Page)</option>\r\n		<option value=\"SearchSingleOld\">Old  Style - Search  (Single Page )</option>\r\n    <option value=\"List\" >List Page</option>\r\n		<option value=\"ListOld\" >Old Style - List Page</option>\r\n    <option value=\"SearchPopup\" >Popup Search Page</option>  \r\n    <option value=\"OnlyTableData\" >Only Table Data Page</option>  \r\n    <option value=\"PaginatedTableData\" >Paginated Table Data Page</option>\r\n    <option value=\"SelectionActivity\" >Selection Activity Page</option>   \r\n    <option value=\"QuickFieldUpdate\" >Quick Field Update Page</option>  \r\n    <option value=\"SelectionDataUpdate\" >Selection Data Update Page</option> \r\n -->		\r\n       \r\n</select>\r\n    </div>\r\n  </div>\r\n	\r\n  <div class=\"form-group  col-md-12\">\r\n    <label for=\"LoginFolderName\" class=\"col-sm-4 control-label\">Login Folder</label>\r\n    <div class=\"col-sm-8\">\r\n			<span class=\"checkbox checkbox-primary\">\r\n      <input type=\"checkbox\" name=\"AddCMSSetting\" id=\"AddCMSSetting\" />\r\n      <label for=\"AddCMSSetting\"><input type=\"text\" class=\"form-control\" name=\"LoginFolderName\"  id=\"LoginFolderName\" value=\"admin\"></label>\r\n      </span>\r\n\r\n               \r\n    </div>\r\n  </div>\r\n</div>\r\n\r\n	 </div>\r\n   <div class=\"col-md-3\">\r\n  <div class=\"form-group\">\r\n    <div class=\"col-sm-offset-4 col-sm-10\">\r\n			<button type=\"submit\" class=\"btn btn-primary btn-lg\" name=\"jsp\" >Get JSP Page</button>\r\n    </div>\r\n  </div>\r\n\r\n	 </div>\r\n	 \r\n</div> \r\n</div>\r\n\r\n<div class=\"row\">\r\n   <div class=\"col-md-12\">\r\n	 \r\n<ul class=\"nav nav-tabs nav-tabs-drop\"><!--  nav-tabs-drop nav-justified  -->\r\n    <li class=\"\"><a href=\"#SelectFieldforAction\" data-toggle=\"tab\"><i class=\"fa fa-tasks fa-lg text-primary\"></i>&nbsp;&nbsp;Fields</a></li>\r\n		<li class=\"active\"><a href=\"#FormValidate\" data-toggle=\"tab\"><i class=\"fa fa-lock fa-lg text-primary\"></i>&nbsp;&nbsp;Form validation</a></li>\r\n		<li class=\"\"><a href=\"#FieldLabels\" data-toggle=\"tab\"><i class=\"fa fa-lock fa-lg text-primary\"></i>&nbsp;&nbsp;Labels</a></li>\r\n		<li class=\"\"><a href=\"#FormValidation\" data-toggle=\"tab\"><i class=\"fa fa-lock fa-lg text-primary\"></i>&nbsp;&nbsp;Input Control</a></li>\r\n</ul>\r\n\r\n<div class=\"tab-content tab-content-drop\">\r\n\r\n<div id=\"SelectFieldforAction\" class=\"tab-pane\">\r\n\r\n      <div class=\"panel panel-default\">\r\n        <!-- Default panel contents -->\r\n        <div class=\"panel-heading\"><i class=\"fa fa-tasks fa-lg text-primary\"></i>&nbsp;&nbsp;Fields for Action</div>\r\n        <div class=\"panel-body\">\r\n            <span class=\"checkbox checkbox-inline checkbox-primary\">\r\n                <input type=\"checkbox\" name=\"multipart_form_upload\" id=\"multipart_form_upload\" >\r\n                <label for=\"multipart_form_upload\">Multipart Form Upload ?</label>\r\n            </span>\r\n        </div>\r\n				\r\n        <div class=\"table-responsive\">\r\n        <table class=\"table table-bordered table-striped Rslt-Act-tbl\" id=\"empleave_type_Result_tbl\">\r\n        <thead>\r\n        <tr>\r\n				  <th></th>\r\n					<th>Field</th>\r\n          <th>\r\n  				<span class=\"checkbox checkbox-inline checkbox-primary\">\r\n              <input type=\"checkbox\" name=\"Search_checkall\" id=\"Search_checkall\" >\r\n              <label for=\"Search_checkall\"><b>Search</b></label>\r\n          </span></th>\r\n          <th>\r\n  				<span class=\"checkbox checkbox-inline checkbox-primary\">\r\n              <input type=\"checkbox\" name=\"Result_checkall\" id=\"Result_checkall\" >\r\n              <label for=\"Result_checkall\"><b>Result</b></label>\r\n          </span></th>\r\n					<th>\r\n  				<span class=\"checkbox checkbox-inline checkbox-primary\">\r\n              <input type=\"checkbox\" name=\"Foreign_checkall\" id=\"Foreign_checkall\" >\r\n              <label for=\"Foreign_checkall\"><b>Foreign Key</b></label>\r\n          </span></th>\r\n					<th>\r\n  				<span class=\"checkbox checkbox-inline checkbox-primary\">\r\n              <input type=\"checkbox\" name=\"Duplicate_checkall\" id=\"Duplicate_checkall\" >\r\n              <label for=\"Duplicate_checkall\"><b>Dupicate</b></label>\r\n          </span></th>\r\n        </tr>\r\n        </thead>\r\n        <tbody>\r\n        <tr>\r\n\r\n      ".toCharArray();
    _jsp_string6 = "/user/index.jsp\"><i class=\"fa fa-home fa-lg\"></i></a></li>\r\n      <li><a href=\"tablelist.jsp?JNDIDSN=".toCharArray();
    _jsp_string71 = "\r\n<!-- iframeResizer.min.js on host page -->\r\n".toCharArray();
    _jsp_string2 = "	\r\n<title>Generate JSP Page</title>	\r\n".toCharArray();
    _jsp_string38 = "class=\"Result_chkbox\"".toCharArray();
    _jsp_string70 = "\r\n".toCharArray();
    _jsp_string44 = ">\r\n          <label for=\"FOREIGN_FIELD_COL_".toCharArray();
    _jsp_string19 = "\" >\r\n    </div>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <label for=\"IDField\" class=\"col-sm-4 control-label\">PK - Auto Number ?</label>\r\n	    <div class=\"col-sm-8\">\r\n			<span class=\"checkbox checkbox-primary\">\r\n      <input type=\"checkbox\" name=\"IsAuto\" id=\"IsAuto\" />\r\n      <label for=\"IsAuto\"><input type=\"text\" name=\"IDField\" class=\"form-control\" value=\"?\" id=\"IDField\"></label>\r\n      </span>\r\n			\r\n    </div>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <label for=\"IDFieldType\" class=\"col-sm-4 control-label\">PK Field Type</label>\r\n    <div class=\"col-sm-8\">\r\n     \r\n			<select name=\"IDFieldType\" id=\"IDFieldType\" class=\"form-control selectpicker\">\r\n    		<option value=\"INT\" selected=\"selected\">Integer</option>\r\n    		<option value=\"STRING\" >Character Data</option> \r\n  		</select>\r\n    </div>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <label for=\"PageTitle\" class=\"col-sm-4 control-label\">Page Title</label>\r\n    <div class=\"col-sm-8\">\r\n			<input type=\"text\" class=\"form-control\" name=\"PageTitle\"  id=\"PageTitle\" value=\"Manage ".toCharArray();
    _jsp_string61 = "\";\r\n".toCharArray();
    _jsp_string53 = "/user/forms/formvalidate.jsp?JNDIDSN=".toCharArray();
    _jsp_string37 = "class=\"auto_Result_chkbox\"".toCharArray();
    _jsp_string66 = "\r\ndocument.forms['beanform'].elements['HasTime'].value =\"YES\" ;\r\n".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string29 = "</td>\r\n      <td>\r\n      <span class=\"checkbox checkbox-inline checkbox-primary\">\r\n          <input type=\"checkbox\" name=\"SearchFields\" value=\"".toCharArray();
    _jsp_string5 = "	\r\n\r\n<div class=\"container-fluid\">\r\n\r\n<div class=\"row page-header11\">\r\n  <div class=\"col-md-6 col-xs-12\">\r\n    <h4 class=\"page-title11\"><i class=\"fa fa-plug text-primary\"></i>&nbsp;&nbsp;<span class=\"text-muted\">Generate JSP Page</span></h4>\r\n  </div>\r\n  <div class=\"col-md-6 col-xs-12\">\r\n    <!--  page-header-actions  -->\r\n    <ol class=\"breadcrumb text-right\">\r\n      <li><a href=\"".toCharArray();
    _jsp_string4 = "\r\n<style type=\"text/css\">\r\n.table th {background-color: #f5f5f5}\r\n</style>\r\n</head>\r\n<body>   \r\n".toCharArray();
    _jsp_string20 = "\" >\r\n    </div>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <label for=\"OutputFileName\" class=\"col-sm-4 control-label\">Output File Name</label>\r\n    <div class=\"col-sm-8\">\r\n			<input type=\"text\" class=\"form-control\" name=\"OutputFileName\"  id=\"c\" value=\"manage".toCharArray();
    _jsp_string69 = "\r\n\r\n".toCharArray();
    _jsp_string51 = " \r\n      ".toCharArray();
  }
}
