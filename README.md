# Jpetstore

## Prerequisite
- [Install Docker](https://docs.docker.com/engine/install/#server)
- [Install Docker-compose](https://docs.docker.com/compose/install/#install-compose)

## Setup Jpetstore
### Start server
    docker-compose build
    docker-compose up -d
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