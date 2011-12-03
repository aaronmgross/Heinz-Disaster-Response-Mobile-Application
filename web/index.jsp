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

    </script>
    <!-- END CUSTOM JAVASCRIPT DEFINITIONS -->

    <body>
        <div data-role="page">

            <div data-role='header' data-theme='b'>
                <h2>Log in</h2>
            </div>

            <div data-role='content'>
                <b>Welcome to DReporter!</b> Please log in below to access the application.<br>
                <%ArrayList errors = (ArrayList<String>) request.getAttribute("errors");
                            if (errors != null) {
                                for (int i = 0; i < errors.size(); i++) {
                                    out.println("<strong>" + errors.get(i) + "</strong>");
                                   }
                                }   
                 String getMessage = (String)request.getAttribute("RegisterMessage");
                 if(getMessage!=null)
                    out.println("<strong>" + getMessage + "</strong>");


                %>

                <div class='sub_form'>
                    <form name='login_form' action='LogIn' method='post'>
                        <input type='hidden' name='lat' >
                        <b>Username:</b><br />
                        <input type='text' name='username'/>

                        <b>Password:</b><br />
                        <input type='password' name='password'/>

                        <b>Choose Disaster Incident:</b><br />

                        <select name="Disaster">
                            <option value='none'>choose...</option>
                            <option value="TEST">User Testing 11/27/11 </option>
                        </select>
                    </form>
                </div>

                <a href='javascript:;' onClick="submitForm('login_form')" data-role="button" data-icon="arrow-r" data-iconpos="right" data-theme="b"><div class='mainlink_big_head'>Log in</div></a>

                <a href='forgot_password.html' data-role="button" data-icon="arrow-r" data-iconpos="right" rel="external">Forgot password?</a>

                <a href='register.jsp' data-role="button" data-icon="arrow-r" data-iconpos="right" rel="external">Register for an account
                </a>

            </div>
        </div>
    </body>
</html>
