# Kepplinger -- Übung Bean Validation

### Was ist Bean Validation
Bean Validation wird benutzt um die Werte von Feldern in Klassen zu überprüfen.  
Dies wird mithilfe von setzen bestimmter Annotationen über den Feldern erzielt.  

**Beispiel:**
```
public class Name {
    @NotNull
    @Size(min=1, max=16)
    private String firstname;

    @NotNull
    @Size(min=1, max=16)
    private String lastname;
    ...
}
```
### Brewery-Validation

- Zuerst wurden die **Validation-Annotationen** hinzugefügt:
![](./images/bean-val.png)

- Danach wird eine **REST-Schnittstelle** defniniert:
![](./images/bean-rest.png)

- Anschließend werden 2 **Tests** erstellt:
![](./images/tests.png)

- Wenn diese erfolgreich sind hat alles funktioniert:
![](./images/success.png)
