<%-- 
    Document   : Register
    Created on : Dec 1, 2011, 1:07:59 PM
    Author     : Michelle
--%>
<%@page import="java.lang.String" %>
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


        <!-- END PAGE HEADER -->

        <!-- BEGIN CUSTOM JAVASCRIPT DEFINITIONS -->
        <script type>

            window.addEventListener('load', function(e) {
                setTimeout(function() { window.scrollTo(0, 1); }, 1);
            }, false);
			
			
			function validateEmailID()
			{
			var x=document.forms["registerForm"]["volunteer_email"].value;
			var atpos=x.indexOf("@");
			var dotpos=x.lastIndexOf(".");
			if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
			  {
			  		document.getElementById("error_container1").style.display = "block";
					document.getElementById('err3').innerHTML="Please check the email IDs you entered, they seem to be invalid";
                  scroll(0,0);
					return false;
			  }
			
			return true;
			}
			function validateFields(){
				
				var v3=validateEmailID();
				var v1=validateEmail();
				var v2=validatePassword();
				
				if(v1==true && v2==true&&v3==true){
					
							submitForm("registerForm");
							
						}
			}
            function validatePassword()
            {

                var pwd=document.forms["registerForm"]["volunteer_password"].value;
                var pwd1=document.forms["registerForm"]["volunteer_password1"].value;

                if(pwd1 != pwd || pwd == "" || pwd == null)
                {
                    document.getElementById("error_container1").style.display = "block";
					document.getElementById('err2').innerHTML="Please check the passwords that you entered - they need to match!";
                    scroll(0,0);
					return false;
                }
                
					return true;
            }


			 function validateEmail()
	            {

	                var email=document.forms["registerForm"]["volunteer_email"].value;
	                var email1=document.forms["registerForm"]["volunteer_email1"].value;

	                if(email1 != email || email == "" || email == null)
	                {
	                    document.getElementById("error_container1").style.display = "block";
		document.getElementById('err1').innerHTML="Please check the email IDs that you entered - they need to match!";
	                    scroll(0,0);
						return false;
	                }
						return true;
	                
	            }

            function submitForm(form_name) {
                document.forms[form_name].submit();
            }

        </script>
    </head>


    <!-- END CUSTOM JAVASCRIPT DEFINITIONS -->

    <body>

        <div data-role="page">
            <div data-role='header' data-theme='b'>
                <a href='index1.html' data-transition="reverse">Back</a>
                <h2>Register</h2>
            </div>

            <div data-role='content'>
                <div id="error_container1" class='failure_message' style='display:none'>
                    <div class='instruction_text' id='err1'></div>
                
                    <div class='instruction_text' id='err2'></div>
                
	                    <div class='instruction_text' id='err3'></div>
	                </div>


                <div class='sub_form'>

                    <div class='instruction_text'>Please enter your information below in order to sign up for a user account.</div>
                    <% String getMessage = (String) request.getAttribute("RegisterMessage");
                                if (getMessage != null) {
                                    out.println("<strong>" + getMessage + "</strong>");
                                }
                    %>
                    <form name="registerForm" method="post" action="Register">

                        <b>First Name:</b><br />
                        <input type="text" name="volunteer_firstname"/>

                        <b>Last Name:</b><br />
                        <input type='text' name='volunteer_lastname'/>

                        <b>E-mail Address:</b><br />
                        <input type='text' name='volunteer_email'/>

						<b>Confirm E-mail Address:</b><br />
                        <input type='text' name='volunteer_email1'/>

                        <b>Password:</b><br />
                        <input type="password" name="volunteer_password"/>

                        <b>Re-type password:</b><br />
                        <input type="password" name="volunteer_password1"/>

                        <a href='javascript:;' onClick="validateFields()" data-role="button" data-icon="arrow-r" data-iconpos="right" data-theme="b"><div class='mainlink_big_head'>Register</div></a>


                    </form>
                </div>
            </div>
        </div>
    </body>

</html>
