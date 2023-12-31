/*
 * This Spock specification was generated by the Gradle 'init' task.
 */
package main

import spock.lang.Specification

class AppTest extends Specification {
    def "setter function sets correct value"() {
        setup:
        //Board is:
        // - - -
        // - + -
        // - - -

            def app = new Board(3, [] as Rule[])
        when:
            app.setAt(0,1, true)
        then:
            app.cells[1] == true
    }

    def "getter function gets correct value"(){
        setup:
        //Board is:
        // - - -
        // - + -
        // - - -
            def app = new Board(3, [] as Rule[])
        when:
            app.setAt(1,1, true)
        then:
            app.getAt(1,1) == true
    }

    def "First Rule applies"() {
        setup:
            def rule = new ConwaysFirstRule()
        when:
            def result = rule.apply(true, 1)
        then:
            result == false
    }

    def "First Rule does not apply"() {
        setup:
            def rule = new ConwaysFirstRule()
        when:
            def result = rule.apply(true, 2)
        then:
            result == true
    }

    def "Check neighbours"(){
        setup: 
            boolean[][] grid = [
                [false, false, false],
                [false, true , true],
                [false, false, true]]
            
            Rule[] rules = [new ConwaysFirstRule()];

            def app = new Board(grid, rules)

        when: 
            def n = app.neighbours(1,1)
        then:
            n == [false, false, false, false, true, false, false, true]
    }

    def "Calculate alive neighbours"(){
        setup: 
            boolean[][] grid = [
                [false, false, false],
                [false, true , true],
                [false, false, true]]
            
            Rule[] rules = [new ConwaysFirstRule()];

            def app = new Board(grid, rules)

        when: 
            def n = app.aliveNeighbours(1,1)
        then:
            n == 2
    }

    def "Tick does work correctly with first rule"(){
        setup:
            // Board:
            // - - +    - - -
            // - - + -> - - +
            // - - +    - - -
            boolean[][] grid = [
                [false, false, true],
                [false, false, true],
                [false, false, true]]
            
            Rule[] rules = [new ConwaysFirstRule()];

            def app = new Board(grid, rules)
            
        when:
            app.tick()
        then: 
            def boolean[][] result = [
                [false, false, false],
                [false, false, true],
                [false, false, false],
            ]
            
            app == new Board(result, rules)
    }

    def "Tick works correctly with second rule"(){
        setup:
            // Board:
            // - - +    - - -
            // - - + -> - - +
            // - - +    - - -
            boolean[][] grid = [
                [false, false, true],
                [false, false, true],
                [false, false, true]]
            
            Rule[] rules = [new ConwaysSecondRule()];

            def app = new Board(grid, rules)
            
        when:
            app.tick()
        then: 
            boolean[][]result = [
                [false, false, false],
                [false, false, true],
                [false, false, false]]
            
            app == new Board(result, rules)
    }

    def "Tick works correclty with third rule"(){
        setup:
            // Board:
            // - + +    - + +
            // - + + -> - - -
            // - - +    - - +
            boolean[][] grid = [
                [false, true, true],
                [false, true , true],
                [false, false, true]]
            
            
            Rule[] rules = [new ConwaysThirdRule()];

            def app = new Board(grid, rules)
            
        when:
            app.tick()
        then: 
            boolean[][] result = [
                [false, true, true],
                [false, false, false],
                [false, false, true]]
            app == new Board(result, rules)
    }

    def "Tick works correclty with fourth rule"(){
        setup:
            // Board:
            // - - +    - + -
            // - + + -> - - -
            // - - +    - + -
            boolean[][] grid = [
                [false, false, true],
                [false, true, true],
                [false, false, true]]
            
            
            Rule[] rules = [new ConwaysFourthRule()];

            def app = new Board(grid, rules)
            
        when:
            app.tick()
        then: 
            boolean[][] result = [
                [false, true, false],
                [false, false, false],
                [false, true, false]]
            app == new Board(result, rules)
    }

    def "Tick works with simplified rule"(){
        setup:
            //Board:
            // + - -    + + +
            // + - - -> - - -
            // + - -    - - - 
        def board = new Board([
            [0,1,0],
            [0,1,0],
            [0,1,0]
            ] as boolean[][], 
            [new ConwaySimplified()] as Rule[])
        
        when:
            board.tick()
        
        then:
            board == new Board([
            [0,0,0],
            [1,1,1],
            [0,0,0]
            ] as boolean[][], 
            [new ConwaySimplified()] as Rule[])
    }

}
