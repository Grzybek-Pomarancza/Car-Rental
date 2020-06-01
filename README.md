### Validators
*Validator dostaje obiekt * klasy, np. `UserValidator` dostaje w argumencie w metodzie *validate()* `User`. 
W przpadku `UserValidator` wykonują się też metody *validate()* z klas `EmailValidator`, `NameValidator` itp. 

W metodzie *validate()* sprawdzane są wartości w obiekcie otrzymanym w argumencie (długość, odpowiednie znaki itp.). Jeśli obiekt nie spełnia wymagań rzucany jest jeden z wyjątków z package excepiots.

### Exceptions
Nazwy klas wyjątków odpowiadają problemom jakie można znaleźć w obiektach w metodach *validate()*. 

### Controllers

###### Dodawanie do bazy
W wersji Filipa metoda *addNewObject()* jest w bloku try, w tej metodzie wywoływane są odpowiednie metody *validate()*, a co za tym idzie rzucane odpowiednie wyjątki przy błednych danych.
W wersji Julki w klasie *Controller w bloku try jest tylko metoda *validate()*. Jeśli ta metoda **nie** rzuci wyjątku, obiekt jest dodawany do bazy metodą *save()* z klasy *Service.

### Logowanie
Postman -> POST localhost:8080/login

w **BODY** :

```
{
	"email": "your_email",
	"password": "your_password"
}
```
 
