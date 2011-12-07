<%-- 
    Document   : index
    Created on : Nov 28, 2011, 1:36:38 PM
    Author     : Michelle
--%>
<%@page import="java.lang.String" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html manifest="mobileapp.manifest">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
        <meta name="apple-mobile-web-app-capable" content="yes">

        <!-- Stylesheets -->
        <link rel="stylesheet" media="all" href="style.css" />
        <!--<link rel="stylesheet" href="jquery.mobile-1.0.css" />-->
        <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css" />

        <!-- JavaScript Includes -->
        <script type="text/javascript" src="jquery-1.6.4.min.js"></script>
        <script type="text/javascript" src="jquery.mobile-1.0.js"></script>


        <title>Disaster Response Mobile Assessment</title>
    </head>
    <!-- END PAGE HEADER -->

    <!-- BEGIN CUSTOM JAVASCRIPT DEFINITIONS -->
    <script>
        window.addEventListener('load', function(e) {
            setTimeout(function() { window.scrollTo(0, 1); }, 1);
        }, false);

        function submitForm(form_name) {
            document.forms[form_name].submit();
        }

        function setErrorMessages()
        {
            if (sessionStorage.getItem("errorValue") != "")
            {
                document.getElementById("error_container").innerHTML = sessionStorage.getItem("errorValue");
                document.getElementById("error_container").style.display = "block";

                // Reset sessionStorage value
                sessionStorage.setItem("errorValue", "");
            }
            
        }

        function setSuccessMessages()
        {
            if (sessionStorage.getItem("successValue") != "")
            {
                document.getElementById("success_container").innerHTML = sessionStorage.getItem("successValue");
                document.getElementById("success_container").style.display = "block";

                // Reset sessionStorage value
                sessionStorage.setItem("successValue", "");
            }

        }
    </script>
    <!-- END CUSTOM JAVASCRIPT DEFINITIONS -->

    <body>
        <div data-role="page">

            <div data-role='header' data-theme='b'>
                <h2>Log in</h2>
            </div>

            <div data-role='content'>
                <b>Welcome to the Disaster Response Mobile Application!</b> <Br /><br />Please log in below to access the application.<br>
                <%ArrayList errors = (ArrayList<String>) request.getAttribute("errors");
                            if (errors != null) {
                                String errorString = "";
                                for (int i = 0; i < errors.size(); i++) {

                                    errorString = errorString + errors.get(i) + "<br />";
                                   }
                                out.println("<script> sessionStorage.setItem('errorValue', '" + errorString + "') </script>");

                                }   

                String getMessage = (String)request.getAttribute("RegisterMessage");

                 if(getMessage!=null)
                    out.println("<script> sessionStorage.setItem('successValue', '" + getMessage + "') </script>");


                %>

                <div class='sub_form'>

                    
                    <div id="error_container" class='failure_message' style='display:none'>
				<div class='instruction_text'></div>
				<div id="inperror"></div>
		</div>


                <div id="success_container" class='success_message' style='display:none'>
				<div class='instruction_text'></div>
		</div>

                 <script>setErrorMessages();</script>
                 <script>setSuccessMessages();</script>


                    <form name='login_form' action='LogIn' method='post'>
                        <input type='hidden' name='lat' >
                        <b>E-mail Address:</b><br />
                        <input type='text' name='username' autocorrect="off" autocapitalization="on"/>
<div class="spacer"></div>
                        <b>Password:</b><br />
                        <input type='password' name='password'/>

                        <!--
                        <b>Choose Disaster Incident:</b><br />

                        <select name="Disaster">
                            <option value='none'>choose...</option>
                            <option value="TEST">User Testing 11/27/11 </option>
                        </select>
                        -->
                    </form>
                </div>

                <a href='javascript:;' onClick="submitForm('login_form')" data-role="button" data-icon="arrow-r" data-iconpos="right" data-theme="b"><div class='mainlink_big_head'>Log in</div></a>

                <a href='register.jsp' data-role="button" data-icon="arrow-r" data-iconpos="right" rel="external">Register for an account
                </a>

                <div class="sub_form">
                    <div style="font-size:9pt">This application was developed by students at <a href="http://cmu.edu" rel="external">Carnegie Mellon University</a> and is licensed to the United Way of Allegheny County and VisionLink, Inc. For more information, please contact <a href="mailto:joemertz@cmu.edu">Dr. Joe Mertz</a>.</div></div>
            </div>
        </div>
    </body>
</html>
