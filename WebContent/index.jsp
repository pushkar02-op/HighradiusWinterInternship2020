<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/index.css">
    <script src="index.js"></script>

</head>
<body>


    <div class="container mirror">
        <div class="logo mirror">
            <img src="images/hrc-logo.svg" alt="logo" width="200">
        </div>
       
        <div class="mirror" >
            <H3>Sign In</H3>
            <form action="login" method="POST" >
                <div class="input">
                <label for="name">User name</label><br>
                <input type="text" id="fname" name="name"><br>
                <label for="pwd">Password</label><br>
                <input type="password" id="pwd" name="pwd" ><br><br>
                </div >
                <div class="button">
                    <input type="submit" value="Sign in">
                </div>
                
            </form>

        </div>
    
    </div>

</body>
</html>