package com.example.e_commerceapp.Extensions

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory


private fun reSizePhoto(image:Int,width:Int,height:Int): Bitmap {
        val options= BitmapFactory.Options()
        options.inMutable=true
        val imageBitmap=BitmapFactory.decodeResource(Resources.getSystem(), image,options)

        val newWidth = width // Yeni genişlik
        val newHeight = height // Yeni yükseklik
        val resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, newWidth, newHeight, true)
        return resizedBitmap
    }