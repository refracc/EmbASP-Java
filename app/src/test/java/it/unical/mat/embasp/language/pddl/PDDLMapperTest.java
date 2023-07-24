/**
 *
 */
package it.unical.mat.embasp.language.pddl;

import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.pddl.PDDLMapper;
import it.unical.mat.embasp.specializations.solver_planning_domains.PickUp;
import org.junit.*;

import java.lang.reflect.InvocationTargetException;

public class PDDLMapperTest {

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {

    final PDDLMapper instance = PDDLMapper.getInstance();

    try {

      instance.registerClass(PickUp.class);

      final Object object = instance.getObject("(pick-up b)");

      Assert.assertTrue(object instanceof PickUp);
      Assert.assertEquals("b", ((PickUp) object).getBlock());
    } catch (ObjectNotValidException | IllegalAnnotationException | IllegalAccessException | IllegalArgumentException |
             InvocationTargetException
             | NoSuchMethodException | SecurityException | InstantiationException e) {
      Assert.fail(e.getMessage());
    }

  }

}
