<%@ page errorPage = "/errorpage.jsp" %>
<%@ page import="com.webapp.security.*, com.$WEBAPP.*, com.$WEBAPP.apputil.*, com.db.$DATABASE.* " %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="SiMngrBn" scope="page" class="com.db.$DATABASE.SitemanagerBean" />
<% 
String appPath = request.getContextPath() ;
String thisFile = appPath+request.getServletPath() ;
com.$WEBAPP.LoggedSitemanager LogUsr =  (com.$WEBAPP.LoggedSitemanager)session.getAttribute("theSitemanager") ;

java.sql.Date today = DateTimeHelper.today();
int cYear = today.getYear()+1900;

String Action = request.getParameter("Action");
if(Action==null) Action="Form" ; // other action is Update

String menuType = "topbar";
%>

<!DOCTYPE HTML>
<html class="no-js css-menubar" lang="en">
<head>
<jsp:include page ="/include-page/common/meta-tag.jsp" flush="true" />
<title>Change System Password</title>
  <jsp:include page ="/include-page/css/main-css.jsp">
	   <jsp:param  name="menuType" value="<%=menuType %>"/>
	</jsp:include>  
<style type="text/css">
<!--
.fa-stack{display:none;}
@media (max-width:480px){
h3 {font-size: 18px;}
} 
.page-login-v3:before{position:fixed;top:0;left:0;content:'';width:100%;height:100%;background-position:center top;-webkit-background-size:cover;background-size:cover;z-index:-1;background:#62a8ea;background-image:url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzYyYThlYSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiMxNTcxYjEiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);background-image:-webkit-linear-gradient(top,#62a8ea 0,#3583ca 100%);background-image:-o-linear-gradient(top,#62a8ea 0,#3583ca 100%);background-image:-webkit-gradient(linear,left top,left bottom,from(#62a8ea),to(#3583ca));background-image:linear-gradient(to bottom,#62a8ea 0,#3583ca 100%);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff62a8ea', endColorstr='#ff3583ca', GradientType=0)}.page-login-v3 .panel{width:400px;margin-bottom:45px;background:#fff;border-radius:4px}.page-login-v3 .panel .panel-body{padding:50px 40px 40px}.page-login-v3 .panel .brand-text{margin-top:8px}.page-login-v3 form{margin:45px 0 30px}.page-login-v3 form a{margin-left:20px}.page-login-v3 form .form-material.floating+.page-login-v3 form .form-material.floating{margin-top:30px}.page-login-v3 form .form-material label{color:#a3afb7;font-weight:300}@media (max-width:480px){.page-login-v3 .page-content{padding:20px 10px}.page-login-v3 .panel{width:auto;padding:50px}.page-login-v3 .panel .panel-body{padding:10px 0px 10px}}
-->
</style>	
<jsp:include page="/include-page/css/formValidation-css.jsp" flush="true" />
  <jsp:include page ="/include-page/common/main-head-js.jsp">
	   <jsp:param  name="menuType" value="<%=menuType %>"/>
	</jsp:include>  
	
</head>
<body class="page-login-v3 layout-full">
  <div class="page animsition vertical-align text-center" data-animsition-in="fade-in" data-animsition-out="fade-out">
    <div class="page-content vertical-align-middle">
     
		  <div class="panel">
        <div class="panel-body">
				
          <div class="brand">
            <a href="index.jsp"><img class="brand-img" src="<%=appPath %><%=ApplicationResource.ProductLogo %>" alt="..."></a>
            <h2 class="brand-text font-size-18 text-uppercase"><%=ApplicationResource.ProductName %></h2>
          </div>

		
<% 
if("Form".equalsIgnoreCase(Action))
{ 
%>
<h3>Change System Password</h3>

<div class="well well-sm well-success">
<ul class="list-icons">
<li><i class="wb-check" aria-hidden="true"></i>&nbsp;<span class="pull-left">logged in with temp password</span></li>
<li><i class="wb-check" aria-hidden="true"></i>&nbsp;<span class="pull-left">change system password</span></li>
</ul>
</div>

                  <form action="<%=thisFile %>" id="PASSWORD_FORM" method="post" >
									<input type="hidden" name="Action" value="Update" />
									  <div class="form-group">
                      
                      <input type="password" name="NewPassword1" id="NewPassword1" class="form-control" placeholder="Enter New Password">
                    </div>
                    <div class="form-group">
                      
                      <input type="password" name="NewPassword2" id="NewPassword2" class="form-control" placeholder="Confirm Password">
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-raised"><span><i class="icon fa fa-check" aria-hidden="true"></i>Submit</span></button>
                        <button type="button" class="btn btn-default btn-outline" onclick="NavigateTo('<%=appPath %>/index.jsp')"><i class="fa fa-remove" aria-hidden="true"></i>&nbsp;Cancel</button>
                    </div>
                  </form>

<% 
}
else if ("Update".equalsIgnoreCase(Action))
{ 
	 SiMngrBn.locateRecord(LogUsr.AdminID);
	 SiMngrBn.Password= request.getParameter("NewPassword1");
   SiMngrBn.PasswordType = PasswordType.USER ;
   SiMngrBn.updateRecord(LogUsr.AdminID);
%>
<div class="well well-sm well-success" ><i class="icon wb-check" aria-hidden="true"></i>New password set successfully.</div>
<br />
<button type="button" class="btn btn-raised btn-primary btn-lg center-block" onclick="NavigateTo('<%=appPath %>/AdminLoginForm.jsp')" ><i class="fa fa-sign-in" aria-hidden="true"></i>&nbsp;Login with New Password</button>
<% 
}
else
{
%>
<div class="alert alert-success" role="alert">
Request Parameter Error
</div>
<%  
}
%>

            </div>
          </div>
      <footer class="page-copyright page-copyright-inverse">
        <p>DEVELOP BY <span class="text-uppercase"><%=ApplicationResource.CompanyName %></span></p>
        <p>&copy; <%=cYear %>. All RIGHT RESERVED.</p>
        <div class="social">
          <a class="btn btn-icon btn-pure" href="javascript:void(0)">
            <i class="icon bd-twitter" aria-hidden="true"></i>
          </a>
          <a class="btn btn-icon btn-pure" href="javascript:void(0)">
            <i class="icon bd-facebook" aria-hidden="true"></i>
          </a>
          <a class="btn btn-icon btn-pure" href="javascript:void(0)">
            <i class="icon bd-google-plus" aria-hidden="true"></i>
          </a>
        </div>
      </footer>
					
    </div>
  </div>
  <!-- End Page -->
	
  <jsp:include page="/include-page/js/main-js.jsp">
	   <jsp:param  name="menuType" value="<%=menuType %>"/>
	</jsp:include>

<jsp:include page="/include-page/js/formValidation-js.jsp" flush="true" />
<script type="text/javascript">
<!--
   function NavigateTo(url)
   {
        document.location.href=url ;
   }
	 
  $(document).ready(function(){
  // {{ Begin Init jQuery 


    $('#PASSWORD_FORM').formValidation({
        framework: 'bootstrap',
        fields: {
            NewPassword1: {
                validators: {
                    notEmpty: {
                        message: 'The Password is required'
                    }
                }
            },
            NewPassword2: {
                validators: {
                    identical: {
                        field: 'NewPassword1',
                        message: 'The password is not same as above'
                    }
                }
            }
        }
    });
 
  // }} End Init jQuery 
  });
// -->
</script>
	
	<jsp:include page="/include-page/common/Google-Analytics.jsp" flush="true" />


</body>
</html>