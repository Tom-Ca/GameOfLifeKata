package gameoflife;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private Cell[][] nextCells;
    private int sizeGrid;
    private Random rd;
    private String rslt = "";
    private int rsltNei;
    private  int count;
    private int column;
    private int line;

    public Grid(int sizeGrid) {
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        generateRandomInitialState();
    }

    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
        this.nextCells = new Cell[sizeGrid][sizeGrid];

    }

    private void generateRandomInitialState() {
        // Pour chaque cases on va prendre random si il est pair on mes une cellule sinon non
        cells = new Cell[sizeGrid][sizeGrid];
        for (column = 0; column < sizeGrid; column++) {
            for (line = 0; line < sizeGrid; line++) {
                if((int)Math.random() % 2 == 0){
                    cells[column][line] = new Cell(true);
                }else{
                    cells[column][line] = new Cell(false);
                }
            }
        }
    }
    public int searchNeibour(boolean type) {
        //On parcourt le tableau de cells autour d'un cellule pour lui trouver un voisin
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                //On verifie qu'on sort pas en dehors de notre grille comme par exemple [-1][-1] ou au dessu de la taille de sizeGrid
                if (column + i >= 0 && line + j >= 0 && column + i < sizeGrid && line + j < sizeGrid) {
                    //Si il trouve un cellule voisine en vie count+=1
                    if (cells[column + i][line + j].isAlive() == true) {
                        count += 1;
                    }
                }
            }
        }
        //Si il est en vie lors de la recherche il va incrementer count pour lui même on soustrait donc count de 1
        if(type == true){
            count-=1;
        }
        return count;
    }

    public void generateNextState() {
        //On parcour tout le tableau
        for (column = 0; column < sizeGrid; column++) {
            for (line = 0; line < sizeGrid; line++) {
        //
                count = 0;
                //On initialise nextCell par rapport a la cellule de cells
                nextCells[column][line] = new Cell(cells[column][line].isAlive());
                //on fait la fonction pour chercher des voisins en vie en fonction de l'etat de la cellule & et appele setIsAlive pour
                //modifier la cellule en fonction des regles du jeu et on ecrit dans nextCells
                if (cells[column][line].isAlive() == true) {
                    rsltNei = searchNeibour(true);
                    nextCells[column][line].setIsAlive(Cell.processState(true, rsltNei));

                }
                if (cells[column][line].isAlive() == false) {
                    rsltNei = searchNeibour(false);
                    nextCells[column][line].setIsAlive(Cell.processState(false, rsltNei));
                }
            }

        }
    }
    public String toString() {
        String[] test= new String[sizeGrid];
        //On parcourt le le tableau de nextCell
        for (int i = 0; i <  sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {
                test[j] = nextCells[i][j].toString();
            }
            rslt += String.join(" ",test)+(i == sizeGrid -1 ? "" : "\n");
        }
        return rslt;
    }
}

