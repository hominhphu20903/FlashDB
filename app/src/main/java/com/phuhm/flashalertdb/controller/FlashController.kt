import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Handler
import android.os.Looper
import com.phuhm.flashalertdb.data.model.FlashType

class FlashController private constructor(context: Context) {

    private val cameraManager: CameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private val cameraId: String? = cameraManager.cameraIdList.firstOrNull()
    private val handler = Handler(Looper.getMainLooper())
    private var isFlashing = false
    private var isFlashOn = false
    private var flashType: FlashType = FlashType.CALLS
    private var timeOn: Long = 500
    private var timeOff: Long = 500
    private var flashCount = 0

    companion object {
        @Volatile
        private var instance: FlashController? = null

        fun getInstance(context: Context): FlashController {
            return instance ?: synchronized(this) {
                instance ?: FlashController(context.applicationContext).also { instance = it }
            }
        }
    }

    private val flashRunnable = object : Runnable {
        override fun run() {
            if (!isFlashing) return

            isFlashOn = !isFlashOn
            setFlash(isFlashOn)

            val delay = if (isFlashOn) timeOn else timeOff
            if (flashType != FlashType.CALLS) {
                flashCount++
                if (flashCount >= 6) {
                    stopFlashing()
                    return
                }
            }
            handler.postDelayed(this, delay)
        }
    }

    fun startFlashing(timeOn: Long, timeOff: Long, type: FlashType) {
        if (isFlashing) return

        this.timeOn = timeOn
        this.timeOff = timeOff
        this.flashType = type
        this.flashCount = 0

        isFlashing = true
        handler.post(flashRunnable)
    }

    fun stopFlashing() {
        isFlashing = false
        handler.removeCallbacks(flashRunnable)
        setFlash(false) // Tắt flash khi dừng
    }

    private fun setFlash(state: Boolean) {
        try {
            cameraId?.let { cameraManager.setTorchMode(it, state) }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}
