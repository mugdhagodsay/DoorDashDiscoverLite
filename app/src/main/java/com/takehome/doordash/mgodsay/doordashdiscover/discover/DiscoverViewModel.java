package com.takehome.doordash.mgodsay.doordashdiscover.discover;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.takehome.doordash.mgodsay.doordashdiscover.model.Restaurant;
import com.takehome.doordash.mgodsay.doordashdiscover.network.ApiClient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class DiscoverViewModel extends ViewModel {

    final ObservableField<Boolean> imagesVisible = new ObservableField<>(true);
    private final ApiClient apiClient;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Restaurant>> response = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public DiscoverViewModel() {
        this.apiClient = ApiClient.getInstance();
        disposable = new CompositeDisposable();
        getData();
    }

    public LiveData<List<Restaurant>> getResponse() {
        return response;
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    private void getData() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("lat", "37.422740");
        map.put("lng", "-122.139956");
        map.put("offset", "0");
        map.put("limit", "25");
        loading.setValue(true);
        disposable.add(apiClient.provideDoorDashApi()
                .discover(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Restaurant>>() {
            @Override
            public void onSuccess(List<Restaurant> value) {
                repoLoadError.setValue(false);
                response.setValue(value);
                loading.setValue(false);
            }

            @Override
            public void onError(Throwable e) {
                repoLoadError.setValue(true);
                loading.setValue(false);
            }
        }));
    }


}
