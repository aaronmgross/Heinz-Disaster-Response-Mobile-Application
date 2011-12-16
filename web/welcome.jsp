<%-- 
    Document   : welcome
    Created on : Nov 28, 2011, 12:48:10 PM
    Author     : Michelle
--%>
<%@page import="java.lang.String" %>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<script>
function initialize_disaster_session()
{
	// TODO: Check to see if there are any elements in local storage already to deal with.
	//		 Present the user with a screen to address those.

	// Check to see if a disaster reporting key incrementor exists - if not, set it at zero.
	if(localStorage.getItem("disasterKey") == null)
	localStorage.setItem("disasterKey", "0");
}
</script>

<body onLoad="initialize_disaster_session()">

<div data-role="page">


	<div data-role="header" data-theme="b">
		<h2>Welcome!</h2>
            <a href="index.jsp" data-rel="back">Back</a>
	</div>

	<div data-role="content">
        <%HttpSession sessionUser = request.getSession();
        String username = (String)sessionUser.getAttribute("userName");
        if(username==null) username="Guest";
        String loginStatus = (String)sessionUser.getAttribute("loginStatus");
        String volunteerId = (String)sessionUser.getAttribute("volunteerId");
        String adminStatus = (String)sessionUser.getAttribute("adminStatus");
        out.println("<script>sessionStorage.setItem('userRole', '" + adminStatus + "'); sessionStorage.setItem('loginStatus', '" + loginStatus + "'); localStorage.setItem('volunteer_id', '" + volunteerId +"'); localStorage.setItem('volunteer_name', '" + username + "'); </script>");
        %>

       <%

       // Check to see if we've been directed here from a successful form fill.
       String status = (String)request.getAttribute("submitStatus");

       if (status == "OK")
           {
                // All records returned OK, we can delete them from local storage
                String idsInserted = (String)request.getAttribute("idsInserted");
                String delims = "[,]";
                String[] tokens = idsInserted.split(delims);

                for(int i = 0; i < tokens.length; i++)
                    {
                        // Iterate over the token array to get all IDs we should delete from local storage
                        out.println("<script>localStorage.removeItem('" + tokens[i] + "');</script>");
                    }
           }

        String successMessage = (String)request.getAttribute("formSuccessMessage");
        String failureMessage = (String)request.getAttribute("formFailureMessage");

        if(successMessage != null)
            out.println("<div class='success_message'><div class='instruction_text'>" + successMessage + "</div></div>");

        if(failureMessage != null)
            out.println("<div class='failure_message'><div class='instruction_text'>" + failureMessage + "</div></div>");
        %>

	Welcome, <strong><%=username%></strong>. Please choose what you would like to do:<br>


	<a href='find_location.html' data-role="button" data-icon="arrow-r" data-iconpos="right" rel="external">
			<div class='mainlink_big_head'>Start</div>
			<div class='mainlink_subtitle'>a disaster assessment</div>
	</a>

	<a href='system_sync_status.html' data-role="button" data-icon="arrow-r" data-iconpos="right" rel="external">
			<div class='mainlink_big_head'>Sync</div>
			<div class='mainlink_subtitle'>assessments saved<br />on local device</div>
	</a>

	<a href='sync.html' data-role="button" data-icon="arrow-r" data-iconpos="right">
			<div class='mainlink_big_head'>Get help</div>
			<div class='mainlink_subtitle'>on how to use this app</div>
	</a>

        <div id="admin_link" style="display:none">
             <a href='approveuser.jsp' data-role="button" data-icon="arrow-r" data-iconpos="right" rel="external">
			<div class='mainlink_big_head'>Approve</div>
			<div class='mainlink_subtitle'>new users</div>
            </a>
        </div>

        <div id="admin_link" style="display:none">
             <a href='deleteuser.jsp' data-role="button" data-icon="arrow-r" data-iconpos="right" rel="external">
			<div class='mainlink_big_head'>Delete Users</div>
			<div class='mainlink_subtitle'>new users</div>
            </a>
        </div>

        <script>
            if (sessionStorage.getItem("userRole") == "ADMIN")
                document.getElementById("admin_link").style.display = "block";
        </script>

	</div>
</div>
</body>
</html>
