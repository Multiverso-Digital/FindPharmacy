package com.abreuretto.findpharmacy;

import java.util.List;

public interface FetchDataListenerFS {
    public void onFetchCompleteFS(List<ApplicationFS> data);
    public void onFetchFailureFS(String msg);
}
