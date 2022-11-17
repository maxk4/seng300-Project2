# SENG300P1-7

## Running
To run the project run the Simulation class with the integer number of DIY Stations passed as an argument

## Notes
Running the simulation with n DIY stations will open:
    1 Attendant UI window
    n Customer UI windows; and
    n Customer Simulation windows

## Operation
1) To start scanner click the start scanning button in the Customer UI (The window with a dark background)
2) Then in the Customer Simulation window click on any items you want to add. (Note: clicking an item multiple times will not scan the item multiple times)
3) The Customer will then be prompted to either place the scanned item in the bag or make a no bagging request. (Note: In this implementation the simulation will execute synchronously so the simulation will not have an oportunity to place the item in the bagging area so a no bagging request will have to be made)
4) To pay click the Pay with Card button in the Customer UI then select the card you wish to use from the Customer Simulation window
5) The simulation has to be terminated remotely ie via command line or terminate in ide. This is to prevent customers from being able to turn off the DIY stations. However the Attendant UI can be closed at any time by clicking the Shutdown button therein.