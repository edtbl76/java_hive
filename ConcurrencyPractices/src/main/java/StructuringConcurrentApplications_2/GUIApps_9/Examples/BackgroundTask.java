package StructuringConcurrentApplications_2.GUIApps_9.Examples;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public abstract class BackgroundTask<V> implements Runnable, Future<V> {

    private final FutureTask<V> computation = new Computation();

    private class Computation extends FutureTask<V> {
        public Computation() {
            super(() -> BackgroundTask.this.compute());
        }

        protected final void done() {
            GuiExecutor.instance().execute(() -> {
                V value = null;
                Throwable thrown = null;
                boolean cancelled = false;
                try {
                    value = get();
                } catch (ExecutionException e) {
                    thrown = e.getCause();
                } catch (CancellationException e) {
                    cancelled = true;
                } catch (InterruptedException consumed) {

                } finally {
                    onCompletion(value, thrown, cancelled);
                }
            });
        }
    }

    /*
        This can be called from compute to provide visual feedback on the
        completed progress in numerical terms.

        This is really a wrapper that calls execute() on the EDT (singleton)
            - onProgress() is the runnable task passed to execute(), which is
            responsible for updating the user interface to represent visual
            progress of the underlying task.
     */
    protected void setProgress(final int current, final int max) {
        GuiExecutor.instance().execute(() -> onProgress(current, max));
    }

    /*
        This is the computation executed in the BACKGROUND THREAD.
        - This is the only necessary method you need to implement.
     */
    protected abstract V compute() throws Exception;

    /*
        onCompletion() and onProgress() are both executed on the EDT.
        - they can optionally be overridden to provide completion and progress notifications
     */
    protected abstract void onCompletion(V value, Throwable thrown, boolean cancelled);

    protected abstract void onProgress(int current, int max);




}
