# TP sur la Sécurité des systèmes distribués Oauth2 OIDC Keycloak
## Application web de gestion de facture avec authentification Oauth2 OIDC Keycloak

## Stack technique
- Frontend: Angular 
- Backend: Spring Boot 
- Keycloak: 
- Base de données: H2

## Architecture de l'application
![Architecture de l'application](img/architecture.png)

## Discovery Service
Le service de découverte est un service qui permet de découvrir les services disponibles dans l'application. Il est basé sur le protocole Eureka de Netflix. 
Il permet de centraliser les informations des services disponibles dans l'application.

![Discovery de l'application](img/discovery-service.png)

## Keycloak
Keycloak est un serveur d'authentification open source qui permet de gérer les identités et les accès des utilisateurs. Il implémente les protocoles Oauth2 et OpenID Connect.

## Configuration de Keycloak

### Starting Keycloak using Docker
![Keycloak Docker](img/keycloak-docker.png)

#### Création d'un realm
![Keycloak Realm](img/glsid-realm.png)

#### Création d'un client
![Angular client](img/ang-client.png)

### L'ajout de la configuration dans chaque service ( inventory & Order )
![Keycloak Configuration](img/config.png)

### Redirection vers Keycloak pour l'authentification
![Keycloak Login](img/login.png)

# Des apercus de l'application

### Page d'accueil
![Page d'accueil](img/homepage.png)

### Les commandes
![Les commandes](img/commandes.png)

### Detail d'une commande
![Gestion des commandes](img/detail-command.png)

### Gestion des commandes
![Gestion des produits](img/products.png)

