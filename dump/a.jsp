<%@ page errorPage = "/errorpage.jsp" %>
<%@ page import="java.io.*, com.beanwiz.* "%>
<% 
String appPath = request.getContextPath() ;
String thisFile = appPath+request.getServletPath() ;

LoggedUser LogUsr = (com.beanwiz.LoggedUser)session.getAttribute("theWizardUser") ;
boolean bAutoLogin = false;
if(LogUsr == null )
{
   LogUsr =  LoginHelper.autoLoginCheck(application,session,request );
	 if(LogUsr!=null) bAutoLogin=true;
}
%>
<!DOCTYPE HTML>
<html lang="en-us">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../favicon.ico">

    <title>Repid Development Tool</title>
		
<jsp:include page="/assets/include-page/main-css.jsp" flush="true" />		
</head>
<body>   

<% 
if(LogUsr !=null)
{
%>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Repid Development Tool</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
           <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i aria-hidden="true" class="icon glyphicon glyphicon-link"></i>&nbsp;&nbsp;User Host&nbsp;<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#"><samp>Host : <%= request.getRemoteHost() %></samp></a></li>
                <li><a href="#"><samp>IP Addr : <%=  request.getRemoteAddr() %></samp></a></li>
              </ul>
            </li>
            						
          </ul>
          <ul class="nav navbar-nav navbar-right">
					  <li><a href="#contact"><i aria-hidden="true" class="icon glyphicon glyphicon-phone-alt"></i>&nbsp;&nbsp;Contact</a></li>
            <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i aria-hidden="true" class="icon glyphicon glyphicon-user"></i>&nbsp;&nbsp;<%=LogUsr.getUserName() %> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="<%=appPath %>/userlogout.jsp"><i aria-hidden="true" class="icon glyphicon glyphicon-off"></i>&nbsp;&nbsp;Logout</a></li>
              </ul>						
						</li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

     <div class="container-fluid">
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Repid Development Tool</h1>

<p class="lead">This wizard  simplifies the tedious job of writing boilerplate,  repetitive code to access databases tables from Java Servlets or Java Server Pages ( JSP ).</p>
<p class="lead">This wizard  generated Java / JSP code allows you to work with any of <code>MySQL</code>, <code>PostgreSQL</code>, <code>Microsoft SQL</code>, <code>Oracle</code> & <code>IBM DB2</code> SQL servers as well as <code>SQlite</code> & <code>H2</code> embedded database libraries. </p>
 
<%
if(bAutoLogin)
{ 
%> 
<p class="lead">Automatic login permitted for IP Addr : <samp><%=request.getRemoteAddr() %></samp></p>
<%  
}
%>
<br />

        <p>
          <a class="btn btn-lg btn-primary" href="<%=appPath %>/user/index.jsp" role="button"><i aria-hidden="true" class="icon glyphicon glyphicon-check"></i>&nbsp;&nbsp;Let's Do It ! &raquo;</a>
					&nbsp;&nbsp;&nbsp;
					<a class="btn btn-lg btn-primary" href="<%=appPath %>/user/other/index.jsp" role="button"><i aria-hidden="true" class="icon glyphicon glyphicon-wrench"></i>&nbsp;&nbsp;Other Dev. Tools &raquo;</a>
        </p>
      </div>
</div> <!-- /container -->
			
<%
}
else
{ 
%>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Repid Development Tool</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
           <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i aria-hidden="true" class="icon glyphicon glyphicon glyphicon-link"></i>&nbsp;&nbsp;User Host&nbsp;<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#"><samp>Host : <%= request.getRemoteHost() %></samp></a></li>
                <li><a href="#"><samp>IP Addr : <%=  request.getRemoteAddr() %></samp></a></li>
              </ul>
            </li>
            						
          </ul>
          <ul class="nav navbar-nav navbar-right">
					  <li><a href="#contact"><i aria-hidden="true" class="icon glyphicon glyphicon-phone-alt"></i>&nbsp;&nbsp;Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

<div class="container">

<div class="logincontent">
<form action="<%=appPath %>/user_login_check/" method="post">
<!--  -->
<h3 align="center"><i aria-hidden="true" class="icon glyphicon glyphicon-log-in"></i>&nbsp;&nbsp;Log In</h3>
<br />
                    <div class="input-group input-group-lg sepH_a">
                        <span class="input-group-addon"><i aria-hidden="true" class="icon glyphicon glyphicon-user"></i></span>
                        <input type="text" name="LoginID" placeholder="Username" class="form-control">
                    </div>
                    <div class="input-group input-group-lg sepH_a">
                        <span class="input-group-addon"><i aria-hidden="true" class="icon glyphicon glyphicon-lock"></i></span>
                        <input type="password" name="Password" placeholder="Password" class="form-control">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
                    </div>
                </form>
</div>								
</div> <!-- /container -->

<% 
} 
%>

<jsp:include page="/assets/include-page/footer.jsp" flush="true" />
<jsp:include page="/assets/include-page/main-js.jsp" flush="true" />
</body>
</html>
