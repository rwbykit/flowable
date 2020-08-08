package rwbykit.flowableTemp;

import io.reactivex.rxjava3.core.Observable;

public class Main {


    public static void main(String[] args) {
        /*Observable.defer(
                 ObservableReplay.fromFuture(new Future<Integer>() {
                    @Override
                    public boolean cancel(boolean mayInterruptIfRunning) {
                        return false;
                    }

                    @Override
                    public boolean isCancelled() {
                        return false;
                    }

                    @Override
                    public boolean isDone() {
                        return false;
                    }

                    @Override
                    public Integer get() throws InterruptedException, ExecutionException {
                        return null;
                    }

                    @Override
                    public Integer get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                        return null;
                    }
                }).
        ).doOnEach(notification ->{
            System.out.println("notification---------------:"+ notification.getValue());
        }).doAfterNext((i) -> {
            System.out.println("doAfterNext------------------:"+ i);
        }).subscribe((i) -> {
            System.out.println("subscribe------------------:"+ i);
        });*/




        Observable.defer(() -> Observable.fromCallable(() -> {
            System.out.println("this is biz");
           return 1;
        }))
                .doOnEach(notification -> System.out.println("notification---------------:"+ notification.getValue()))
                .doAfterNext((i) -> System.out.println("doAfterNext------------------:"+ i))
                .subscribe();
    }

    public static Integer create(Integer i) {
        System.out.println("create Integer:"+i);
        return i;
    }
}
