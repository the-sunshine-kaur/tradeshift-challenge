# tradeshift-challenge
Company Tree 

We in Amazing Co need to model how our company is structured so we can do awesome stuff.  
We have a root node (only one) and several children nodes, each one with its own children as well. It's a tree-based structure. 

We need two HTTP APIs that will serve the two basic operations: 
Get all children nodes of a given node (the given node can be anyone in the tree structure). 
Change the parent node of a given node (the given node can be anyone in the tree structure). 

They need to answer quickly, even with tons of nodes. Also, we can't afford to lose this information, so some sort of persistence is
required.  

Each node should have the following info:  
node identification who is the parent node who is the root node the height of the node.
In the above example, height(root) = 0 and height(a) == 1. 

Our boss is evil and we can only have docker and docker-compose on our machines. So your server needs to be ran using them.  



Build the project with Maven

Setup a MYSQL database

Run the complete stack with docker compose: docker-compose up

Stop all the services at once using docker compose using the command: docker-compose down
