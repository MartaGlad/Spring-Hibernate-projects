package com.gladysz.proxy.db;

public class LazyDataRetrieverProxy implements DbDataRetriever {

    private DbDataRetriever retriever;

    private DbDataRetriever getRetriever() throws InterruptedException {
        if(retriever == null)
            retriever = new PostgresDataRetriever();
        return retriever;
    }

    @Override
    public int getFirstValue() throws InterruptedException {

        return getRetriever().getFirstValue();
    }


    @Override
    public int getSecondValue() throws InterruptedException {

        return getRetriever().getSecondValue();
    }


    @Override
    public int getThirdValue() throws InterruptedException {

        return getRetriever().getThirdValue();
    }
}
