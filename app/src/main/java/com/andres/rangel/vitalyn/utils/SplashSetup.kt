package com.andres.rangel.vitalyn.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen

fun SplashScreen.setAnimation(animation: SplashAnimationType) {
    this.setOnExitAnimationListener { splashViewProvider ->
        val view = splashViewProvider.view
        val animatorSet = AnimatorSet()

        when (animation) {
            SplashAnimationType.SCALE_FADE -> {
                val scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 1.5f)
                val scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1.5f)
                val fade = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
                animatorSet.playTogether(scaleX, scaleY, fade)
                animatorSet.interpolator = AccelerateDecelerateInterpolator()
            }

            SplashAnimationType.TRANSLATE_FADE -> {
                val translate =
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0f, -view.height * 0.25f)
                val fade = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
                animatorSet.playTogether(translate, fade)
                animatorSet.interpolator = AnticipateInterpolator()
            }

            SplashAnimationType.TRANSLATE_ROTATE_FADE -> {
                val translateX = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0f, view.width * 0.3f)
                val translateY = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0f, -view.height * 0.3f)
                val rotate = ObjectAnimator.ofFloat(view, View.ROTATION, 0f, 30f)
                val fade = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
                animatorSet.playTogether(translateX, translateY, rotate, fade)
                animatorSet.interpolator = AnticipateOvershootInterpolator()
            }
        }

        animatorSet.duration = 700
        animatorSet.doOnEnd { splashViewProvider.remove() }
        animatorSet.start()
    }
}

enum class SplashAnimationType {
    SCALE_FADE,
    TRANSLATE_FADE,
    TRANSLATE_ROTATE_FADE
}