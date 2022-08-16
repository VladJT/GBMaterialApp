package jt.projects.gbmaterialapp.util

import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun <T> View.getUniString(text: T): String {
    return ((text as? Int)?.let { resources.getText(text as Int) } ?: text) as String
}

// принимает текст для вывода или как строку, или как id Resources (String)
fun <T> View.showSnackBarShort(text: T) {
    Snackbar.make(this, this.getUniString(text), Snackbar.LENGTH_SHORT).show()
}

// принимает текст для вывода SnackBar или как строку, или как id Resources (String)
fun <T, R> View.showSnackBarWithAction(text: T, actionText: R, action: () -> Unit) {
    Snackbar.make(
        this,
        this.getUniString(text),
        Snackbar.LENGTH_LONG
    )// отображается неопределенное время - Snackbar.LENGTH_INDEFINITE
        .setAction(this.getUniString(actionText)) { action.invoke() }
        .show()
}

fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.BOTTOM, 0, 250)
        show()
    }
}

fun Fragment.snackBar(text: String) {
    this.view?.let { Snackbar.make(it, text, Snackbar.LENGTH_LONG).show() }
}