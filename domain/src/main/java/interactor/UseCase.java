package interactor;


import executor.PostExecutionThread;
import executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

// this class is used to create a use case called from a presenter to the data layer

public abstract class UseCase<T, Params> {


    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    // the use needs to be called in the background thread, and be "post-executed" on the main thread
    // the reason why the ThreadExecutor is an interface is to make the use case more testable
    // and platform independent (in the java sense anyway)

    // the disposable is there so that when an observable is created, we can dispose of it and avoid
    // a memory leak

    UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    abstract Single<T> buildUseCaseObservable(Params params);



    public void execute(DisposableSingleObserver<T> observer, Params params) {

        // add preconditions to check if the observer is not null

        final Single<T> single = this.buildUseCaseObservable(params)
                        .subscribeOn(Schedulers.from(threadExecutor)) // which thread to run the program
                        .observeOn(postExecutionThread.getScheduler()); // which thread to return results to

        addDisposable(single.subscribeWith(observer));
    }

    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void dispose(){
        if(!disposables.isDisposed()) {
            disposables.dispose();
        }
    }


}
