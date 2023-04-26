package StructuringConcurrentApplications_2.TaskExecution_6.Examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class QuoteTask implements Callable<TravelQuote> {

    private final TravelCompany travelCompany;
    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany travelCompany, TravelInfo travelInfo) {
        this.travelCompany = travelCompany;
        this.travelInfo = travelInfo;
    }

    @Override
    public TravelQuote call() {
        return travelCompany.solicitQuote(travelInfo);
    }

    public TravelQuote getFailureQuote(Throwable cause) {
        return new TravelQuote();
    }

    public TravelQuote getTimeoutQuote(CancellationException e) {
        return new TravelQuote();
    }
}

class TravelQuote {}
class TravelCompany {
    public TravelQuote solicitQuote(TravelInfo travelInfo) {
        return new TravelQuote();
    }
}
class TravelInfo {}

class TravelUtil {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public List<TravelQuote> getRankedTravelQuotes(
            TravelInfo travelInfo,
            Set<TravelCompany> companies,
            Comparator<TravelQuote> ranking,
            long time,
            TimeUnit unit
    ) throws InterruptedException {

        List<QuoteTask> tasks = new ArrayList<>();

        for (TravelCompany company : companies)
            tasks.add(new QuoteTask(company, travelInfo));

        List<Future<TravelQuote>> futures = executor.invokeAll(tasks, time, unit);

        List<TravelQuote> quotes = new ArrayList<>(tasks.size());

        Iterator<QuoteTask> taskIterator = tasks.iterator();
        for (Future<TravelQuote> future : futures) {
            QuoteTask task = taskIterator.next();
            try {
                quotes.add(future.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }

        quotes.sort(ranking);
        return quotes;

    }
}