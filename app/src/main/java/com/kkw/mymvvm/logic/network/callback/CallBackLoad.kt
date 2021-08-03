import android.content.Context
import com.kkw.mymvvm.logic.model.ResponseData

interface CallBackLoad<T> {

    fun loading()

    fun loadSuccess(baseEntity: ResponseData<T>?)

    fun loadError(context: Context?, code: Int, msg: ResponseData<T>?)

    fun loadFailure(t: Throwable?)

}