TP4: Java EE
=====================
Pierre FALEZ  
Ga�tan DEFLANDRE  
- - - - - - - - - - - 

## Introduction
Cette application est un exemple de site d'e-commerce destin�e � la vente de livre.
Il utilise le framework Java Entreprise et notament la *Java Persistence API*

Cette application propose diverse fonctionnalit�es : 
* Inscription d'utilisateur
* Gestion de panier
* Recherche de livre
* Tri par auteur

## Architecture

Nous avons s�par� notre application en diff�rentes couches :
* La premiere, contenu dans le package *entity*, contient tous les �l�ments destin�s � �tre persistant. Ceux-ci sont repr�sent� par des *Entity Bean* de la *Java Persistence API*
* La seconde contient tout les services dans le package *service*. Chaque service est une *Session Bean* (*Stateful* ou *Stateless*) de la *Java Persistence API*
* La troisieme couche, contenu dans le package *servlet*, regroupe l'ensembles des servlets http. Ces derniers fonctionne avec des fichiers JSP en guise de template HTML.

Il y a �galement un package *exeception* qui regroupe les exceptions sp�cifique � l'application.

*Liste des throw*

service.LoginService:39, UnableToLoginException
Exception lev� lorsque le nom de l'utilisateur est introuvable.

service.LoginService:43, UnableToLoginException
Exception lev� lorsque le mot de passe entr� ne correspond pas au mot de passe associ� � l'utilisateur.

service.OrderService:39, UnableToOrderException
Exception lev� lorsque l'utilisateur � traiter est introuvable.

entity.User:50, UnableToCreateUserException
Exception lev� lorsque qu'il y a un probl�me d'encodage des caracteres

entity.User:52, UnableToCreateUserException
Exception lev� lorsque qu'il y a un probl�me avec l'encryptage du mot de passe

*Liste des try/catch*

servlet.AbstractSessionServlet:38, NamingException
V�rifie la r�cup�ration de l'EJB via lookup

servlet.Login:73, UnableToLoginException
V�rifie que le login � bien fonctionn�, et affiche un message � l'utilisateur dans le cas contraire

servlet.Order:52, UnableToOrderException
V�rifie que le traitement de la commande � bien fonctionn�, et affiche un message � l'utilisateur dans le cas contraire

servlet.SignUp:68, UnableToCreateUserException
V�rifie que l'inscription � bien fonctionn�, et affiche un message � l'utilisateur dans le cas contraire

entity.User:45, UnsupportedEncodingException, NoSuchAlgorithmException
R�cup�rations des erreurs li� � l'encryptage du mot de passe.

entity.User:109, UnsupportedEncodingException, NoSuchAlgorithmException
R�cup�rations des erreurs li� � l'encryptage du mot de passe.

service.LoginService:35, NoResultException
V�rifie que l'utilisateur existe dans la base de donn�e

## Code samples

service.loginService:30 Verification du compte utilisateur
```
public void login(String username, String password) throws UnableToLoginException {
	Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
	query.setParameter("username", username);
	User user = null;
	try {
		user = (User)query.getSingleResult();
	}
	catch(NoResultException e) {
		throw new UnableToLoginException("User not found");
	}
        
	if(!user.checkPassword(password))
		throw new UnableToLoginException("Inccorect password");
}
```

servlet.AbstractSessionServlet:34 Gestion de la session du panier
```
CartService cartService;
cartService = (CartService)getServletContext().getAttribute(CART_SESSION_KEY); // get cart session
        
if(cartService == null) {
	try {
        InitialContext ic = new InitialContext();
        cartService = (CartService)ic.lookup("java:global/BookSell/CartService"); // get service

        getServletContext().setAttribute(CART_SESSION_KEY, cartService);

    } catch (NamingException e) {
        return null;
    }
 }
```

entity.User:92 Hashage du mot de passe (SHA1)
```
private String hash(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	crypt.reset();
	crypt.update(str.getBytes("UTF-8"));
	
	Formatter formatter = new Formatter();
	for (byte b : crypt.digest()) { // Transform each byte in hexa format string
		formatter.format("%02x", b);
	}
    String result = formatter.toString();
    formatter.close();
        
    return result;
}
```

servlet.ListBook:35 Gestion de la liste des livre � donner au template
```
String search = request.getParameter("search");
        
if(search != null && !search.equals("")) {
	request.setAttribute("search", search);
	request.setAttribute("listBook", listBookService.search(search)); // get list of book, which title contains search pattern
}
else {
	request.setAttribute("search", "");
	request.setAttribute("listBook", listBookService.getByTitle()); // get all books
}
        
this.getServletContext().getRequestDispatcher("/ListBook.jsp").forward(request, response);
```

servlet.SignUp:68 Inscription d'un utilisateur
```
try {
	signUpService.create(new User(username, password, User.Rank.USER));
	request.setAttribute("message", "Sign Up success ! please login now");
	response.sendRedirect("Login");
}
catch(UnableToCreateUserException e) {
	request.setAttribute("message", e.getMessage());
	processRequest(request, response);
}
```