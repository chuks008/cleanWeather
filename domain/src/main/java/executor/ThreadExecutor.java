package executor;

import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public interface ThreadExecutor {

    Scheduler getScheduler();

}
