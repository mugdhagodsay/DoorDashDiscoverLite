package com.takehome.doordash.mgodsay.doordashdiscover.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.takehome.doordash.mgodsay.doordashdiscover.model.Restaurant;
import com.takehome.doordash.mgodsay.doordashdiscover.network.ApiClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends ViewModel {

    final ObservableField<Boolean> imagesVisible = new ObservableField<>(true);
    private final ApiClient apiClient;
    private CompositeDisposable disposable;
    private String restaurantId;

    private final MutableLiveData<Restaurant> response = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public DetailsViewModel()
    {
        this.apiClient = ApiClient.getInstance();
        disposable = new CompositeDisposable();
    }

    public void setRestaurantId(String restaurantId)
    {
        this.restaurantId = restaurantId;
    }

    public LiveData<Restaurant> getResponse()
    {
        getData();
        return response;
    }

    private void getData()
    {
        loading.setValue(true);
        disposable.add(apiClient.provideDoorDashApi()
                .getRestaurant(restaurantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Restaurant>() {
                    @Override
                    public void onSuccess(Restaurant value) {
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
