package ca.bcit.comp2522.setB.project.marcuslages.numbergame;

/**
 * Functional Interface used to propagate the click
 * of a user on a Button in the ButtonGrid.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
@FunctionalInterface
public interface GridListener {

    /**
     * Method that is called once the user clicks on
     * a Button in the ButtonGrid.
     */
    void onNumberPlaced();
}
