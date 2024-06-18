<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="header">
        <div class="left">
            <a href="/" class="logo">System logowania i rejestracji</a>
        </div>
        <div class="right">
            <#if userId??>
                <a href="/logout" class="headerButtons" >Wyloguj się</a>
            <#else>
                <a href="/register" class="headerButtons" >Zarejestruj się</a>
                <a href="/login" class="headerButtons" >Zaloguj się</a>
            </#if>
        </div>
    </div>
    <div class="content">
        <#if username??>
            <h1>jesteś zalogowny jako <span style="color: red">${username}</span></h1>
        <#else>
            <h1>Nie jesteś zalogowny</h1>
        </#if>
    </div>
</body>
</html>
