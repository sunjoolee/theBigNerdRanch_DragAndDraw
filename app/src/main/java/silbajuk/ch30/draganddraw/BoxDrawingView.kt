package silbajuk.ch30.draganddraw

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.util.jar.Attributes

private const val TAG = "BoxDrawingView"

class BoxDrawingView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var currentBox: Box? = null
    private val boxen = mutableListOf<Box>()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val current = PointF(event.x, event.y)
        var action = ""
        when(event.action){
            MotionEvent.ACTION_DOWN->{
                action = "ACTION_DOWN"
                //그리기 상태를 재설정
                currentBox = Box(current).also{
                    boxen.add(it)
                }
            }
            MotionEvent.ACTION_MOVE->{
                action = "ACTION_MOVE"
                updateCurrentBox(current)
            }
            MotionEvent.ACTION_UP->{
                action = "ACTION_UP"
                updateCurrentBox(current)
                currentBox = null
            }
            MotionEvent.ACTION_CANCEL->{
                action = "ACTION_CANCEL"
                currentBox = null
            }
        }
        Log.i(TAG, "$action at x = ${current.x}, y = ${current.y}")
        return true
    }

    private fun updateCurrentBox(current : PointF){
        currentBox?.let{
            it.end = current
            invalidate()
        }
    }
}