package main 

class Board {
    int size
    boolean[] cells
    Rule[] rules

    Board(boolean[][] grid, Rule[] rules){
        this.size  = grid.size()
        
        this.cells = grid.flatten()

        this.rules = rules
    }

    Board(int size = 3, Rule[] rules) {
        if (size < 3) {
            throw new RuntimeException("size of $size is too small. At least 3.")
        }

        this.size = size
        this.cells = new boolean[size * size]
        this.rules = rules
    }

    boolean equals(other) {
        return this.cells == other.cells
    }

    String toString(){
        return this.cells.toString()
    }

    void initialize() {
        for(int i; i < this.cells.length; i++){
            if(Math.random() < 0.5){
                this.cells[i] = true;
            }
        }
    }

    boolean getAt(int row, int col){
        if(row >= this.size || col >= this.size) {
            throw new IndexOutOfBoundsException("Row Index: $row, Row Size: ${this.size}; Col Index: $col, Col size: ${this.size}")
        }
        return this.cells[row * this.size + col]
    }

    void setAt(int row, int col, boolean val){
        if(row >= this.size || col >= this.size) {
            throw new IndexOutOfBoundsException("Row Index: $row, Row Size: ${this.size}; Col Index: $col, Col size: ${this.size}")
        }

        this.cells[row * this.size + col] = val       
    }

    boolean[] neighbours(int row, int col) {
        
        List<Boolean> result = []

        for(int rowN = row - 1; rowN <= row + 1; rowN++) {

            if(rowN < this.size && rowN >= 0) {
                
                for(int colN = col -1; colN < col + 2; colN++) {
                    
                    if(colN == col && rowN == row) {
                        continue;
                    }

                    if(colN >= 0 && colN < this.size) {
                        result.add(this.getAt(rowN, colN))
                    }

                }
            }
        }

        return result
    }

    int aliveNeighbours(int row, int col) {
        def n = this.neighbours(row,col)
        return n.inject(0) {r, i -> r + (i ? 1 : 0)}
    }

    void tick() {
        def board = new Board(this.size) // Point to optimize!

        for(int r = 0; r < this.size; r++) {
            for(int c = 0; c < this.size; c++) {
                def newValue = this.getAt(r, c);
                for(rule in this.rules) {
                    newValue = rule.apply(this.getAt(r,c), this.aliveNeighbours(r,c))
                    board.setAt(r, c, newValue)
                }
            }
        }
        this.cells = board.cells
    }

}