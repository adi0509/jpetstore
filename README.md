# Jpetstore

## Prerequisite
- [Install Docker](https://docs.docker.com/engine/install/#server)
- [Install Docker-compose](https://docs.docker.com/compose/install/#install-compose)

## Setup Jpetstore
### Start server
    docker-compose build
    docker-compose up -d
NOTE: ``docker-compose build`` will take 15-20 minutes for the first time. It downloads all the required dependency.
### Stop server
    docker-compose down

## Architecture
![architecture](/images/architect.png)
### Account
![architecture](/images/account.png)
### Catalog
![architecture](/images/catalog.png)
### Order
![architecture](/images/order.png)
### Cart
![architecture](/images/cart.png)