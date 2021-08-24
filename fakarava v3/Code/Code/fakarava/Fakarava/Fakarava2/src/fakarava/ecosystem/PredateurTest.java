package fakarava.ecosystem;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PredateurTest {
    static Lagon baie = new Lagon(4);
    static int lola = baie.newPredateur("Morue agressive", 5, 1, 2);
    public PredateurTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * @see Object#toString()
     */
    @Test
    public void testToString() {
        assertEquals("Prédateur --->  Nom:Morue agressive - Age:0 - Cellule:(1,2) - Poid:5.0",baie.getPoisson(0).toString());
    }

    /**
     * @see Predateur#clone(int)
     */
    @Test
    public void testClone() {
        int gertrude = baie.clonePredateur((Predateur)baie.getPoisson(0));
        assertEquals(baie.getPoisson(0).getCellule(),baie.getPoisson(1).getCellule());
        assertTrue(baie.getPoisson(0).getPoid() == baie.getPoisson(1).getPoid());
        assertEquals(baie.getPoisson(0).getType(),baie.getPoisson(1).getType());
        assertEquals(baie.getPoisson(0).getNom(),baie.getPoisson(1).getNom());
    }

    /**
     * @see Predateur#isIn(Cellule)
     */
    @Test
    public void testIsIn() {
        Predateur lolat = (Predateur)baie.getPoisson(0);
        assertEquals(lolat.isIn(baie.getCellule(1, 2)),true);
    }

    /**
     * @see Predateur#attaque(boolean)
     */
    @Test
    public void testAttaque() {
        fail("Unimplemented");
    }

    /**
     * @see Predateur#ticktock()
     */
    @Test
    public void testTicktock() {
        fail("Unimplemented");
    }

    /**
     * @see Predateur#toString()
     */
    @Test
    public void testToString1() {
        fail("Unimplemented");
    }

    /**
     * @see Predateur#setPredatorCloneTime(int)
     */
    @Test
    public void testSetPredatorCloneTime() {
        fail("Unimplemented");
    }

    /**
     * @see Predateur#getPredatorCloneTime()
     */
    @Test
    public void testGetPredatorCloneTime() {
        fail("Unimplemented");
    }

    /**
     * @see Predateur#setBiteFactor(int)
     */
    @Test
    public void testSetBiteFactor() {
        fail("Unimplemented");
    }

    /**
     * @see Predateur#getBiteFactor()
     */
    @Test
    public void testGetBiteFactor() {
        fail("Unimplemented");
    }
}
