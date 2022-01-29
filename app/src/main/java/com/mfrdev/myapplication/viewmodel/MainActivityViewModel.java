package com.mfrdev.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import com.mfrdev.myapplication.data.repositories.CommonRepository;
import com.mfrdev.myapplication.model.Cloth;
import com.mfrdev.myapplication.model.GlossaryRequestBody;
import com.mfrdev.myapplication.model.GlossaryResponseBody;
import com.mfrdev.myapplication.utils.NetworkErrorHandler;
import com.mfrdev.myapplication.utils.Resource;

import java.util.List;

import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private CommonRepository commonRepository;
    private Application application;

    private String v_id;
    private String isAvailable;
    private String gname;
    private String gdesc;
    private String gqty;
    private String gprice;


    private final MediatorLiveData<Resource<GlossaryResponseBody>> glossaryInsertResponse = new MediatorLiveData<>();
    private final MediatorLiveData<Resource<List<Cloth>>> getCloth = new MediatorLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        commonRepository = new CommonRepository();
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public String getGqty() {
        return gqty;
    }

    public void setGqty(String gqty) {
        this.gqty = gqty;
    }

    public String getGprice() {
        return gprice;
    }

    public void setGprice(String gprice) {
        this.gprice = gprice;
    }


    public void sendInsertRequest(GlossaryRequestBody requestBody) {
        final LiveData<Resource<GlossaryResponseBody>> source = LiveDataReactiveStreams
                .fromPublisher(commonRepository.executeInsertGlossaryProduct(requestBody)
                        .doOnSubscribe(subscription -> glossaryInsertResponse.setValue(Resource.loading(null)))
                        .onErrorReturn(throwable -> ((Response<GlossaryResponseBody>) NetworkErrorHandler
                                .handleThrowable(throwable, application, "")))
                        .map(this::parserInsertResponse));

        glossaryInsertResponse.addSource(source, serviceListResource -> {
            glossaryInsertResponse.setValue(serviceListResource);
            glossaryInsertResponse.removeSource(source);
        });
    }

    private Resource<GlossaryResponseBody> parserInsertResponse(Response<GlossaryResponseBody> response) {
        try {
            int statusCode = response.code();
            if (statusCode == 201 && response.body() != null) {
                return Resource.success(response.body());
            }
            if (statusCode == 400) {
                return Resource.error("Bad request", null);
            }
            if (statusCode == 404) {
                return Resource.error(response.errorBody().string(), null);
            }
            NetworkErrorHandler.logUnsuccessfulResponse(response);
        } catch (Throwable th) {

        }
        return Resource.error("Bad request", null);
    }



    public void sendClothRequest() {
        final LiveData<Resource<List<Cloth>>> source = LiveDataReactiveStreams
                .fromPublisher(commonRepository.getClothProduct()
                        .doOnSubscribe(subscription -> getCloth.setValue(Resource.loading(null)))
                        .onErrorReturn(throwable -> ((Response<List<Cloth>>) NetworkErrorHandler
                                .handleThrowable(throwable, application, "")))
                        .map(this::parseClothResponse));

        getCloth.addSource(source, serviceListResource -> {
            getCloth.setValue(serviceListResource);
            getCloth.removeSource(source);
        });
    }

    private Resource<List<Cloth>> parseClothResponse(Response<List<Cloth>> response) {
        try {
            int statusCode = response.code();
            if (statusCode == 200 && response.body() != null) {
                return Resource.success(response.body());
            }
            if (statusCode == 400) {
                return Resource.error("Bad request", null);
            }
            if (statusCode == 404) {
                return Resource.error(response.errorBody().string(), null);
            }
            NetworkErrorHandler.logUnsuccessfulResponse(response);
        } catch (Throwable th) {

        }
        return Resource.error("Bad request", null);
    }


    public MediatorLiveData<Resource<GlossaryResponseBody>> getGlossaryInsertResponse() {
        return glossaryInsertResponse;
    }

    public MediatorLiveData<Resource<List<Cloth>>> getGetCloth() {
        return getCloth;
    }
}
