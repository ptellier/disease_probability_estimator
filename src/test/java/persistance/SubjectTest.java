package persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Subject;
import ui.deletecomponent.DeleteComponent;
import ui.reviewcomponent.ReviewComponent;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubjectTest {

    private Subject subject1;
    private DeleteComponent deleteComp;
    private ReviewComponent reviewComp;

    @BeforeEach
    void setup() {
        subject1 = new Subject();
        deleteComp = new DeleteComponent();
        reviewComp = new ReviewComponent();
    }

    @Test
    void constructorTest() {
        assertEquals(new ArrayList<>(), subject1.getObservers());
    }

    @Test
    void addObserverTest() {
        subject1.addObserver(deleteComp);
        assertEquals(Arrays.asList(deleteComp), subject1.getObservers());
        subject1.addObserver(reviewComp);
        assertEquals(Arrays.asList(deleteComp, reviewComp), subject1.getObservers());
    }

    @Test
    void addObserverTwiceDoesNothingTest() {
        subject1.addObserver(deleteComp);
        subject1.addObserver(deleteComp);
        assertEquals(Arrays.asList(deleteComp), subject1.getObservers());
    }

    @Test
    void notifyObserversTest() {
        subject1.addObserver(deleteComp);
        subject1.notifyObservers();
        assertEquals(deleteComp, deleteComp);
    }
}
