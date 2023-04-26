package StructuringConcurrentApplications_2.GUIApps_9.Examples;

import javax.swing.SwingUtilities;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class GuiExecutor extends AbstractExecutorService {
    /*
                NOTE: Singletons have a private constructor and a public factory
            */
    private static final GuiExecutor INSTANCE = new GuiExecutor();

    private GuiExecutor() {
    }

    public static GuiExecutor instance() {
        return INSTANCE;
    }

    public void execute(Runnable runnable) {
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        } else {
            SwingUtilities.invokeLater(runnable);
        }
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
