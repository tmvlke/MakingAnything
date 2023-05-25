package wskim.corefeature.network.retrofit

import com.google.gson.GsonBuilder
import com.itkacher.okprofiler.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

class RetrofitClient(serverUrl:String, forceUseInterceptor: Boolean? = null) {

    val restService: ServerAPI

    init {

        val enableInterceptor = forceUseInterceptor?: BuildConfig.DEBUG

        val okHttpClient = if(enableInterceptor){
                    OkHttpClient.Builder()
                            .connectionPool(ConnectionPool(5, 10000, TimeUnit.MILLISECONDS))
                            .addInterceptor(OkHttpProfilerInterceptor())
                            .connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                            .readTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                            .writeTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                            .build()
                }else{
                    OkHttpClient.Builder()
                            .connectionPool(ConnectionPool(5, 10000, TimeUnit.MILLISECONDS))
                            .connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                            .readTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                            .writeTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                            .build()
                }

//        SSLContext.getInstance("TLSv1.3")


        val retrofit = Retrofit.Builder()
                .baseUrl(serverUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        this.restService = retrofit.create(ServerAPI::class.java)
    }

    companion object {
        const val CONNECTION_TIMEOUT_SEC = 5L
    }
}