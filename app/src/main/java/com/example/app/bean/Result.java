package com.example.app.bean;

import java.util.List;

/**
 * Author Tao.ZT.Zhang
 * Date   2017/10/31
 */

public class Result<T> {
    private boolean error ;
    private List<T> results;;

    public Result(boolean error, List<T> results) {
        this.error = error;
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    
}
