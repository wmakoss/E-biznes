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
        <div class="formBox">
            <form action="" method="post">
                <h1>Zaloguj się</h1>
                <span>Login:</span><br />
                <input name="username" type="text" /><br />
                <span>Hasło:</span><br />
                <input name="password" type="password" /><br />
                <input type="submit" value="Zaloguj się" />
                <#if error??>
                    <br /><span class="error">${error}</span>
                </#if>
            </form>
        </div>
    </div>
</body>
</html>
