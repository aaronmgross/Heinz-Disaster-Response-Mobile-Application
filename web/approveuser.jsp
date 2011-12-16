<%-- 
    Document   : approveuser
    Created on : Dec 1, 2011, 1:58:49 PM
    Author     : Yao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.util.List;"%>
<%@ page language="java" import="DataDAO.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">	<!-- sets document with to device width -->
<meta name="apple-mobile-web-app-capable" content="yes"> <!-- tags as web app for iOS -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"> <!-- status bar translucent -->
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js"></script>
<link rel="stylesheet" media="all" href="style.css" />
<title>Disaster Response App</title>
</head>

<!-- script immediately scrolls the page one pixel to hide the address bar -->
<script>
window.addEventListener('load', function(e) {
    setTimeout(function() { window.scrollTo(0, 1); }, 1);
  }, false);
</script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <body>

        <div data-role="page" id="approve_form">

            <div data-role="header" data-theme="b   ">
                <a href="welcome.jsp" data-rel="back">Back</a>
		<h2>Approve Users</h2>
            </div>

            <div data-role="content">
                <form name="admin" method="post" action="ApproveUser">

                    <div class="sub_form">
                        <div class="instruction_text">Please select the users you wish to approve. Upon approval, users will be able to access the disaster application and submit disaster assessments.</div>

                 <div data-role="fieldcontain" class="iOS-fc-fix" style='margin:auto;text-align:center'>
                        <fieldset data-role="controlgroup">

                        <%
                                    String msg = (String) request.getAttribute("message");
                                    if (msg == null) {
                                        msg = "";
                                    }

                                    Connection con;
                                    List<User> l = null;
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver");
                                    } catch (ClassNotFoundException e) {
                                        throw new AssertionError(e);
                                    }
                                    String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
                                    String user = "root";
                                    String pw = "hello";
                                    try {
                                        //database,For MySQL it would be "jdbc:mysql:///<dbname>",Optionally you can pass in a user id, & password
                                        con = DriverManager.getConnection(connectionStr, user, pw);
                                        l = User.getUnapproved(con);
                                        User temp;
                                        if (l.isEmpty()) {
                                        }
                                        for (int i = 0; i < l.size(); i++) {
                                            temp = l.get(i);
                        %>
                        
				<input type='checkbox' id="<%=temp.getUserId()%>" name="chbox" value ="<%=temp.getUserId()%>" />
				<label for='<%=temp.getUserId()%>'><%=temp.getfName()%> <%=temp.getlName()%>, <%=temp.getEmail()%></label>
				
			

                        <%
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                        %>
                    </fieldset>
                 </div>

            </div>
	<a href='javascript:;' onClick="document.forms['admin'].submit()" data-role="button" data-icon="arrow-r" data-iconpos="right">
			<div class='mainlink_big_head'>Approve</div>
			<div class='mainlink_subtitle'>selected users</div>
	</a>
        
                     <div data-role="content">
        <p><a href="deleteuser.jsp" data-role="button" data-icon="arrow-r" data-iconpos="right">Delete Users</a></p>
        <p><a href="export_xml_and_excel.html" data-role="button" data-icon="arrow-r" data-iconpos="right">Click here to download XML and Excel files</a></p>

        <div class='mainlink_big_head'></div>
    </div>


                    

            </div>
        </div>
        
    </body>
</html>