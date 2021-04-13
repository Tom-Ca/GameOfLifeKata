package gameoflife;

public class Cell {
    private boolean isAlive;

    Cell() {
        this.isAlive = false;
    }

    Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public String toString() {
        return isAlive ? "X" : ".";
    }

    boolean isAlive() {
        return isAlive;
    }

    void setIsAlive(boolean newState) {
        this.isAlive = newState;
    }

    static boolean processState(boolean isAlive, int nbNeighbourCellsAlive) {
       if(isAlive == true && (nbNeighbourCellsAlive > 3 || nbNeighbourCellsAlive < 2)){
            isAlive = false;
        }
        if(isAlive == false && nbNeighbourCellsAlive == 3 ){
            isAlive = true;
        }
        return isAlive;
    }
}
