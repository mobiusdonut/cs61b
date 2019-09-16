//package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
/*
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;
*/

public class TestClorus
{
    @Test
    public void testCharacteristics()
    {
        Clorus c = new Clorus(9);
        assertEquals(9, c.energy(), 0.001);
        assertEquals(new Color(34, 0 , 231), c.color());
    }
    @Test
    public void testMove()
    {
        Clorus c = new Clorus(10);
        c.move();
        assertEquals(9.97, c.energy(), 0.001);
    }
    @Test
    public void testStay()
    {
        Clorus c = new Clorus(10);
        c.stay();
        assertEquals(9.99, c.energy(), 0.001);
    }
    @Test
    public void testAttack()
    {
        Clorus c = new Clorus(10);
        Plip p = new Plip(2);
        c.attack(p);
        assertEquals(12, c.energy(), 0.01);
    }
    @Test
    public void testReplicate()
    {
        Clorus originalClorus = new Clorus(10);
        Clorus babyClorus = originalClorus.replicate();
        assertNotEquals(originalClorus, babyClorus);
        assertEquals(5, babyClorus.energy(), 0.01);
    }

    @Test
    public void testChooseReplicate()
    {
        Clorus c = new Clorus(10);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Empty());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertEquals(expected, actual);
    }

    @Test
    public void testChooseNotReplicate()
    {
        Clorus c = new Clorus(0.5);
        HashMap<Direction, Occupant> surrounded2 = new HashMap<Direction, Occupant>();
        surrounded2.put(Direction.TOP, new Impassible());
        surrounded2.put(Direction.BOTTOM, new Empty());
        surrounded2.put(Direction.LEFT, new Impassible());
        surrounded2.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(surrounded2);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);
    }

    @Test
    public void testAttackPlips()
    {
        Clorus c = new Clorus(10);
        HashMap<Direction, Occupant> plipSurrounded = new HashMap<Direction, Occupant>();
        plipSurrounded.put(Direction.TOP, new Empty());
        plipSurrounded.put(Direction.BOTTOM, new Empty());
        plipSurrounded.put(Direction.LEFT, new Plip(1));
        plipSurrounded.put(Direction.RIGHT, new Empty());
        Action actual = c.chooseAction(plipSurrounded);
        Action expected = new Action(Action.ActionType.ATTACK, Direction.LEFT);
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        TestClorus t = new TestClorus();
        t.testCharacteristics();
        t.testMove();
        t.testStay();
        t.testAttack();
        t.testReplicate();
        t.testChooseNotReplicate();
        t.testAttackPlips();
    }

}
