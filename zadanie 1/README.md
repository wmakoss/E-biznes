
Aplikacja działa na porcie **8080** więc można ją uruchomić np. w następujący sposób:

```
docker build . -t nazwa/nazwa:latest
docker run -p 127.0.0.1:8080:8080 -it nazwa/nazwa:latest
```

Aplikacja będzie działała pod adresem:

```
http://127.0.0.1:8080/
```
