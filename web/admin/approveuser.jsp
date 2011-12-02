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
        <title>Approve User</title>
        <script language="javascript">

            function validation()
            {
                var count=0;
                for(vari=0;i<admin.chbox.length;i++)
                {
                    if(admin.chbox[i].checked)
                    {
                        count++;
                    }
                }

                if(count==0)
                {
                    alert("check at least one");
                    return false;
                }
                else
                {
                    return true;
                }

            }
        </script>
    </head>
    <body>        
        <form name="admin" method="post" action="ApproveUser" onClick="return validation()">
            <p align="center"><b>Please Select Users To Be Approved</b><br>&nbsp;</p>            
            <div align="center" width="85%">
                <center>
                    <table border="1" borderColor="#ffe9bf" cellPadding="0" cellSpacing="0" width="658" height="63">
                        <tbody>
                        <td bgColor="#008080" width="50" align="center" height="19"></td>
                        <td bgColor="#008080" width="120" align="center" height="19"><font color="#ffffff"><b>First Name</b></font></td>
                        <td bgColor="#008080" width="120" align="center" height="19"><font color="#ffffff"><b>Last Name</b></font></td>
                        <td bgColor="#008080" width="270" align="center" height="19"><font color="#ffffff"><b>Email</b></font></td>
                        <td bgColor="#008080" width="224" align="center" height="19"><font color="#ffffff"><b>Telephone</b></font></td>
                        <td bgColor="#008080" width="270" align="center" height="19"><font color="#ffffff"><b>Agency</b></font></td>
                        </tbody>

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
                        <tr>                           
                            <td width="50"><input type="checkbox" name="chbox" value="<%=temp.getUserId()%>"/></td>
                            <td width="120"><%=temp.getfName()%></td>
                            <td width="120"><%=temp.getlName()%></td>
                            <td width="270"><%=temp.getEmail()%></td>
                            <td width="224"><%=temp.getTelephone()%></td>
                            <td width="270"><%=temp.getAgency()%></td>
                        </tr>

                        <%
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                        %>
                    </table>
                    <p><font color="#FF0000"><b><%=msg%></b></font></p>
                </center>
            </div>
            <input type="submit" value="Approve"/>
        </form>
    </body>
</html>