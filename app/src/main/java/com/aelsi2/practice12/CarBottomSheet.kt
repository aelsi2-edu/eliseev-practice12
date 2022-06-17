package com.aelsi2.practice12

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aelsi2.practice12.utils.debouncers.OnClickDebouncer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarBottomSheet : BottomSheetDialogFragment(), View.OnClickListener {
    private lateinit var bookButton : View
    private val onClickDebouncer = OnClickDebouncer(this, 500)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View?
    {
        val myView = inflater.inflate(R.layout.car_bottom_sheet, container, false)
        container?.background = ColorDrawable(android.graphics.Color.TRANSPARENT)
        bookButton = myView.findViewById(R.id.bp_book_button)
        bookButton.setOnClickListener(onClickDebouncer)
        return myView
    }
    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onClick(view: View?) {
        if (view == bookButton) showTimer()
    }
    private fun showTimer() {
        dismiss()
        val intent = Intent(context, TimerActivity::class.java)
        startActivity(intent)
    }
}