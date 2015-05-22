package io.kimo.tmdb.domain;


import android.content.Context;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import io.kimo.tmdb.R;
import io.kimo.tmdb.TMDb;
import io.kimo.tmdb.domain.service.API;
import io.kimo.tmdb.domain.service.response.GetConfigurationResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class BaseUseCase extends Job {

    public static final int HIGH_PRIORITY = 1;
    public static final int RETRY_LIMIT = 0;
    public static final String NETWORK_ERROR = "Internet connection failure.";

    private Context context;
    protected BaseUseCaseCallback callback;
    protected String errorReason;

    public BaseUseCase(Context context, BaseUseCaseCallback callback) {
        super(new Params(HIGH_PRIORITY));
        this.context = context;
        this.callback = callback;
    }

    @Override
    public void onAdded() {/*EMPTY FOR NOW*/}

    @Override
    public void onRun() throws Throwable {
        if(!TMDb.LOCAL_DATA.hasBaseImageURL()) { //MEMOIZATION OF THE CONFIGURATIONS
            API.http().getConfigurations(context.getString(R.string.api_key), new Callback<GetConfigurationResponse>() {
                @Override
                public void success(GetConfigurationResponse getConfigurationResponse, Response response) {
                    TMDb.LOCAL_DATA.storeBaseImageURL(getConfigurationResponse.getImages().getBase_url());
                }

                @Override
                public void failure(RetrofitError error) {
                    if(error.getKind() == RetrofitError.Kind.NETWORK) {
                        errorReason = NETWORK_ERROR;
                    } else {
                        errorReason = error.getResponse().getReason();
                    }
                    onCancel();
                }
            });
        }
    }

    @Override
    protected void onCancel() {
        callback.onError(errorReason);
    }

    @Override
    protected int getRetryLimit() {
        return RETRY_LIMIT;
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        throwable.printStackTrace();
        errorReason = throwable.getMessage();
        onCancel();
        return false;
    }
}
