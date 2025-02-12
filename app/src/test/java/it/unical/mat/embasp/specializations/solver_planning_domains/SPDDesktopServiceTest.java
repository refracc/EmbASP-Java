/**
 *
 */
package it.unical.mat.embasp.specializations.solver_planning_domains;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.pddl.*;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.solver_planning_domains.desktop.SPDDesktopService;
import org.junit.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SPDDesktopServiceTest {

  private final String base_path = ".." + File.separator + ".." + File.separator + "test-resources" + File.separator + "pddl" + File.separator;
  /**
   * Countdown latch
   */
  private CountDownLatch lock = new CountDownLatch(1);
  private Plan plan;

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

  private void test(final int[] results_sizes, final String instance_name) {

    final String instance_path = base_path + instance_name + File.separator;

    System.out.println("Testing " + results_sizes.length + " files for " + instance_path);

    for (int i = 1; i <= results_sizes.length; i++)
      try {
        plan = null;
        lock = new CountDownLatch(1);

        final Handler handler = new DesktopHandler(new SPDDesktopService());
        System.out.println();

        final InputProgram inputProgramDomain = new PDDLInputProgram(PDDLProgramType.DOMAIN);

        inputProgramDomain.addFilesPath(instance_path + "domain.pddl");

        final InputProgram inputProgramProblem = new PDDLInputProgram(PDDLProgramType.PROBLEM);

        final String problem = instance_path + "p" + (i < 10 ? 0 : "") + i + ".pddl";

        System.out.println(problem);
        Assert.assertTrue("File not found: " + problem, new File(problem).exists());

        inputProgramProblem.addFilesPath(problem);

        handler.addProgram(inputProgramDomain);
        handler.addProgram(inputProgramProblem);

        PDDLMapper.getInstance().registerClass(PickUp.class);

        Assert.assertNull(plan);

        handler.startAsync(o -> {

          if (!(o instanceof Plan))
            return;

          plan = (Plan) o;

          for (final Action action : plan.getActions())
            System.out.print(action.getName() + ",");

          lock.countDown();

        });

        lock.await(50000, TimeUnit.MILLISECONDS);

        Assert.assertNotNull(plan);

        if (results_sizes[i - 1] != 0)
          Assert.assertTrue("Found error in the Plan " + problem + "\n" + plan.getErrors(), plan.getErrors().isEmpty());

        Assert.assertEquals(results_sizes[i - 1], plan.getActions().size());

        for (final Object obj : plan.getActionsObjects())
          if (obj instanceof PickUp pickUp) {
            System.out.println(pickUp.getBlock());
          }

        Thread.sleep(500);

      } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException |
               ObjectNotValidException
               | IllegalAnnotationException | InterruptedException e) {
        Assert.fail(e.getMessage());
      }

    System.out.println();

  }

  @Test
  public void test_blocksworld() throws InterruptedException {
    final int[] results_sizes = {6, 10, 6, 12, 10, 16, 12, 10, 20, 20, 24, 22, 18, 24, 16, 34, 28, 26, 42, 36, 36, 32, 46, 34, 40, 34, 58, 50, 44, 38, 46,
        58, 66, 56, 56};
    test(results_sizes, "blocksworld");
  }

  @Test
  public void test_depots() throws InterruptedException {
    final int[] results_sizes = {11, 16, 44, 48, 154, 64, 33, 56, 83, 25, 74, 133, 30, 63, 0, 36, 54, 103, 56, 146, 55, 0};
    test(results_sizes, "depots");
  }

  @Test
  public void test_gripper() throws InterruptedException {
    final int[] results_sizes = {15, 23, 31, 39, 47, 55, 63, 71, 79, 87, 95, 103, 111, 119, 127, 135, 143, 151, 159, 167};
    test(results_sizes, "gripper");
  }

  @Test
  public void test_logistics() throws InterruptedException {
    final int[] results_sizes = {24, 27, 15, 34, 17, 10, 30, 14, 33, 34, 58, 53, 48, 68, 46, 49, 74, 48, 0, 84, 69, 117, 134, 102, 109, 127, 141, 128};
    test(results_sizes, "logistics");
  }

}
