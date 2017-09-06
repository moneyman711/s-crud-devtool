<% 
String appPath=request.getContextPath(); 
String menuType = request.getParameter("menuType");
String MenuTitle = request.getParameter("MenuTitle");
String MenuLink = request.getParameter("MenuLink");
%>

<%  
if(menuType.equals("topbar"))
{
%>
  <jsp:include page ="/admin/include-page/assets/topbar/nav-body-topbar.jsp">
		 <jsp:param  name="MenuTitle" value="<%=MenuTitle %>"/>
		 <jsp:param  name="MenuLink" value="<%=MenuLink %>"/>
	</jsp:include>	
<%  
}
else if(menuType.equals("center"))
{
%>
  <jsp:include page ="/admin/include-page/assets/center/nav-body-center.jsp">
		 <jsp:param  name="MenuTitle" value="<%=MenuTitle %>"/>
		 <jsp:param  name="MenuLink" value="<%=MenuLink %>"/>
	</jsp:include>
<%  
}
else
{
%>
  <jsp:include page ="/admin/include-page/assets/base/nav-body-base.jsp">
		 <jsp:param  name="MenuTitle" value="<%=MenuTitle %>"/>
		 <jsp:param  name="MenuLink" value="<%=MenuLink %>"/>
	</jsp:include>	
<%  
}
%>