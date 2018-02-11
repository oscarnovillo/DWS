<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <title>Welcome</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script >
            function logout()
            {

                $.get("https://mail.google.com/mail/u/0/?logout&hl=en", function (data) {

                    alert("Log out");
                    location = "index.jsp";
                });

            }

        </script>
    </head>
    <body>


        <!--  forward to index page for login if user info is not in session  -->
        <% if (session.getAttribute("userName") == null) {%>
        <jsp:forward page="/index.jsp"/>
        <% }%>

        <h3>Welcome  ${userName}</h3>
        <a href="#" onclick="revokeAccess();">RevokeAccess</a>
        <a href="#" onclick="signOut();">Sign out</a>
         <a href="#" onclick="logout();">logout</a>
        <script>
            function signOut() {
                gapi.load('auth2', function () {
                    auth2 = gapi.auth2.init({
                        client_id: '1027953223368-th3hh3onqnevoft8sgliam4kvntmtrvj.apps.googleusercontent.com',
                        fetch_basic_profile: true,
                        scope: 'profile'
                    });
                    // Sign the user in, and then retrieve their ID.
                    auth2.signIn().then(function () {
                        console.log(auth2.currentUser.get().getId());
                        auth2.signOut();
                        location = "logout.jsp";
                    });
                    //GoogleAuth = gapi.auth2.getAuthInstance();
                    //GoogleAuth.signOut();
                    //auth2.signOut();
                    //   location="index.jsp";
                    // location="https://mail.google.com/mail/u/0/?logout&hl=en";






                });
            }

            function revokeAccess() {
                // Google's OAuth 2.0 endpoint for revoking access tokens.
                var revokeTokenEndpoint = 'https://accounts.google.com/o/oauth2/revoke';

                // Create <form> element to use to POST data to the OAuth 2.0 endpoint.
                var form = document.createElement('form');
                form.setAttribute('method', 'post');
                form.setAttribute('action', revokeTokenEndpoint);

                // Add access token to the form so it is set as value of 'token' parameter.
                // This corresponds to the sample curl request, where the URL is:
                //      https://accounts.google.com/o/oauth2/revoke?token={token}
                var tokenField = document.createElement('input');
                tokenField.setAttribute('type', 'hidden');
                tokenField.setAttribute('name', 'token');
                tokenField.setAttribute('value', '${userToken}');
                form.appendChild(tokenField);

                // Add form to page and submit it to actually revoke the token.
                document.body.appendChild(form);
                form.submit();
            }


        </script>

    </body>
</html>