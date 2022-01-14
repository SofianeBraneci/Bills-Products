### API pour gerer des factures et des produits
Pour le développement de l'API, Spring boot a été utilisé, ainsi que MySql pour gérer la persistance des données via Hibernet

Les différents composants sont:

##### Les modèles de données

![model-ea](Capture%20d’écran%202022-01-14%20à%2018.44.06.png)

#### Representation en spring

- L'entité facture correspond à la classe Bill
- L'entité produit correpond à la classe de Product

La modélisation de la relation entre les deux entités se fait facilement grâce aux annotations Spring.
Dans notre cas on fait face à une relation many-to-many, grâce à Spring on ajoute une collection dans chacune des classes
et de les annoter par **@ManyToMany**.

##### API 

###### ProductController
Gere toutes requêtes lié aux produits, les urls sont

>localhost:8080/products/all

Renvoie tous les produits en base de données

>localhost:8080/products/all?page=

Renvoie tous les produits dans la n-ième page (les produits sont organisés en page)

>localhost:8080/products/find?id=

Renvoie le produit avec l'identifiant *id*

>localhost:8080/products/add?product_name=&price=&tva=

La création d'un nouveau produit avec les paramètres contenue dans l'url

>localhost:8080/products/delete?id=

Supprime le produit dont l'identifiant est *id* et renvoie *id*

>localhost:8080/products/upload 

Permets de traiter un fichier cvs contenants différents produits, avec les colonnes product_name, price, tva

###### BillController

Gere les opérations liées aux factures

>localhost:8080/bills/all

Renvoie toutes les factures déjà établies

>localhost:8080/bills/find?billId=

Retourne la facture dont l'id est *billId*

>localhost:8080/bills/find-products?billId=

Renvoie la liste des produits de la facture dont l'id est *billId*

>localhost:8080/bills/add?emitter=&client=

Création d'une nouvelle facture destinée au client *client* facturé par *emitter*, et renvoie l'instance crée

>localhost:8080/bills/add-product?billId=&productId

Ajoute le produit dont l'id est *productId* à la facture avec l'id *billId*

>localhost:8080/bills/delete?billId=

Supprime la facture avec l'id *billId*

>localhost:8080/bills/delete-product?billId=&productId=

Supprime de la facture le produit identifié par *productId*