package main

interface Rule {
    boolean apply(boolean cell, int numberOfAlive)
}

class ConwaysFirstRule implements Rule {
    boolean apply(boolean cell, int numberOfAlive){
        if(cell) {
            if(numberOfAlive < 2) {
                return false
            }
            return true
        }
        return false;
    }
}

class ConwaysSecondRule implements Rule {
    boolean apply(boolean cell, int numberOfAlive) {
        return cell && (numberOfAlive == 2 || numberOfAlive == 3)
        }
}

class ConwaysThirdRule implements Rule {
    boolean apply(boolean cell, int numberOfAlive) {
        if(cell) {
            if(numberOfAlive > 3){
                return false;
            }
            return true;
        }
        return false;

        return !(cell && numberOfAlive > 3)
    }
}

class ConwaysFourthRule implements Rule {
    boolean apply(boolean cell, int numberOfAlive) {
        if(!cell && numberOfAlive == 3){
            return true
        } else {
            false
        }
    }
}

class ConwaySimplified implements Rule {
    boolean apply(boolean cell, int numberOfAlive) {
       if(cell && (numberOfAlive == 2 || numberOfAlive == 3) || !cell && numberOfAlive == 3) {
        return true;
       }
       return false;
    }
}