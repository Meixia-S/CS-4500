package Common;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Utils {

  /**
   * Call the given Callable. Throws a TimeoutException if the given callable fails to terminate
   * within the given time limit.
   * @param method
   * @param timeLimit
   * @param timeoutMsg
   * @throws IllegalStateException
   * @throws TimeoutException
   */
  public static <T> T runMethodWithTimeout(Callable<T> method, int timeLimit, String timeoutMsg)
      throws IllegalStateException, TimeoutException {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<T> future = executor.submit(method);
    T returnVal;
    try {
      returnVal = future.get(timeLimit, TimeUnit.SECONDS);
    } catch (TimeoutException e) {
      future.cancel(true);
      throw e;
    } catch (ExecutionException e) {
      throw new SecurityException("Misbehaved player");
    } catch (InterruptedException e) {
      //TODO: is this what we actually want to do with these exceptions?
      throw new IllegalStateException("Blow Up here");
    }
    executor.shutdownNow();
    return returnVal;
  }

  /**
   * Run the given Runnable. Throws a TimeoutException if the given runnable fails to terminate
   * within the given time limit.
   * @param method
   * @param timeLimit
   * @param timeoutMsg
   * @throws IllegalStateException
   * @throws TimeoutException
   */
  public static void runVoidMethodWithTimeout(Runnable method, int timeLimit, String timeoutMsg)
      throws IllegalStateException, TimeoutException {
    runMethodWithTimeout(new VoidWrapper<>(method, true), timeLimit, timeoutMsg);
  }

  /**
   * A wrapper class for a void method that calls the void method and returns the given return value.
   * @param <T> the type of the return value.
   */
  private static class VoidWrapper<T> implements Callable<T> {

    private final Runnable runnable; //the void method to run
    private final T returnVal;

    private VoidWrapper(Runnable runnable, T returnVal) {
      this.runnable = runnable;
      this.returnVal = returnVal;
    }

    @Override
    public T call() {
      runnable.run();
      return returnVal;
    }
  }

}
