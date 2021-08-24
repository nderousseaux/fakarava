package fakarava.ecosystem;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PoissonTest {
    static Lagon baie = new Lagon(4);
    static int lola = baie.newProie("morue", 5, 1, 2, 52);
    static int yoann;

    @Before
    public void setUp() throws Exception 
    {
    
    }

    /**
     * @see Poisson#getNumero()
     */
    @Test
    public void testGetNumero() {
        Poisson test1 = baie.getPoisson(0);
        assertEquals(baie.getPoissons().toString(),0,test1.getNumero());
    }

    /**
     * @see Poisson#getAge()
     */
    @Test
    public void testGetAge() {
        Poisson test1 = baie.getPoisson(0);
        assertEquals(baie.getPoissons().toString(),0,test1.getAge());
    }

    /**
     * @see Poisson#getNom()
     */
    @Test
    public void testGetNom() {
        Poisson test1 = baie.getPoisson(0);
        assertEquals(baie.getPoissons().toString(),"morue",test1.getNom());
    }

    /**
     * @see Poisson#getPoid()
     */
    @Test
    public void testGetPoid() {
        Poisson test1 = baie.getPoisson(0);
        assertTrue(5.0==test1.getPoid());
    }

    /**
     * @see Poisson#getCellule()
     */
    @Test
    public void testGetCellule() {
        Poisson test1 = baie.getPoisson(0);
        assertTrue(baie.getCellule(1, 2)==test1.getCellule());
    }

    /**
     * @see Poisson#seDeplacer(Lagon)
     */
    @Test
    public void testSeDeplacer() {
        baie.getPoisson(0).seDeplacer(baie);
        assertTrue(baie.getPoissonbyCellule(baie.getCellule(1, 2)).size() == 0);
    }

    /**
     * @see Poisson#clone()
     */
    @Test
    public void testClone() {
        baie.cloneProie((Proie)baie.getPoisson(0));
        assertTrue(baie.getNbPoisson() == 2);
    }
}
