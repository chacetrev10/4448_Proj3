# 4448_Proj3
## Roll Store Simulator
Team: Chace Trevino, Joseph Wilson

### Special Instructions  
Uncomment lines 26-27 if you want the output of 30 days of the simulation to be recorded in an output file.

### Language and Environment
Language: Java 8  
Environment: Eclipse IDE  

### Program Design  
For this simulator we implemented an observer pattern, decorator pattern, and factory pattern. The RollStore is observed by a StoreObserver object to keep track of when inventory of certain rolls runs out. We decorate rolls with random values of certain extra toppings, fillings, or sauces. This allows us to easily add extra things to a roll, regardless of what subclass of Roll it falls into. We also used a factory to create customers, so that we didn't have to use specific Customer subclass constructors when adding new customers to the customer list in the simulation.  

We determined that all unit tests could be done before the main loops of the simulation began running. This helped us determine that the basic functions of roll, customer, and store classes were working. We assumed that each day, the simulation was required to print out an entire customer's order including extra add-ons to each roll. 