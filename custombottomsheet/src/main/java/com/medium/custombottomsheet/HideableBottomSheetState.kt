package com.medium.custombottomsheet

import android.content.res.Resources
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.key
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.medium.custombottomsheet.HideableBottomSheetValue.Expanded
import com.medium.custombottomsheet.HideableBottomSheetValue.HalfExpanded
import com.medium.custombottomsheet.HideableBottomSheetValue.Hidden
import java.lang.Float.min

@Composable
fun rememberHideableBottomSheetState(
    initialValue: HideableBottomSheetValue,
    animationSpec: AnimationSpec<Float> = HideableBottomSheetDefaults.AnimationSpec,
    confirmValueChange: (HideableBottomSheetValue) -> Boolean = { true },
    onDismiss: () -> Unit = {},
): HideableBottomSheetState {
    return key(initialValue) {
        rememberSaveable(
            initialValue, animationSpec, confirmValueChange,
            saver = HideableBottomSheetState.Saver(
                animationSpec = animationSpec,
                confirmValueChange = confirmValueChange,
                onDismiss = onDismiss,
            )
        ) {
            HideableBottomSheetState(
                initialValue = initialValue,
                animationSpec = animationSpec,
                confirmValueChange = confirmValueChange,
                onDismiss = onDismiss,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Stable
class HideableBottomSheetState(
    initialValue: HideableBottomSheetValue,
    val onDismiss: () -> Unit = {},
    private val animationSpec: AnimationSpec<Float> = HideableBottomSheetDefaults.AnimationSpec,
    private val confirmValueChange: (HideableBottomSheetValue) -> Boolean = { true },
) {

    val draggableState = AnchoredDraggableState(
        initialValue = initialValue,
        animationSpec = animationSpec,
        positionalThreshold = HideableBottomSheetDefaults.PositionalThreshold,
        velocityThreshold = HideableBottomSheetDefaults.VelocityThreshold,
        confirmValueChange = confirmValueChange
    )

    /**
     * The current value of the [HideableBottomSheetState].
     */
    val currentValue: HideableBottomSheetValue
        get() = draggableState.currentValue

    val targetValue: HideableBottomSheetValue
        get() = draggableState.targetValue

    /**
     * Whether the bottom sheet is visible.
     */
    val isVisible: Boolean
        get() = currentValue != Hidden

    /**
     * Whether the bottom sheet is expanded.
     */
    val isExpanded: Boolean
        get() = currentValue == Expanded

    /**
     * Whether the bottom sheet is half expanded.
     */
    val isHalfExpanded: Boolean
        get() = currentValue == HalfExpanded

    /**
     * Whether the bottom sheet is hidden.
     */
    val isHidden: Boolean
        get() = currentValue == Hidden

    private val hasHalfExpandedState: Boolean
        get() = draggableState.anchors.hasAnchorFor(HalfExpanded)

    /**
     * Show the bottom sheet with animation and suspend until it's shown.
     * If the sheet is taller than 50% of the parent's height, the bottom sheet will be half expanded.
     * Otherwise, it will be fully expanded.
     */
    suspend fun show() {
        val targetValue = when {
            hasHalfExpandedState -> HalfExpanded
            else -> Expanded
        }
        animateTo(targetValue)
    }

    /**
     * Expand the bottom sheet with an animation and suspend until the animation finishes or is cancelled.
     */
    suspend fun expand() {
        if (draggableState.anchors.hasAnchorFor(Expanded)) {
            animateTo(Expanded)
        }
    }

    /**
     * Half expand the bottom sheet with an animation and suspend until the animation finishes or is cancelled.
     */
    suspend fun halfExpand() {
        if (draggableState.anchors.hasAnchorFor(HalfExpanded)) {
            animateTo(HalfExpanded)
        }
    }

    /**
     * Hide the bottom sheet with an animation and suspend until the animation finishes or is cancelled.
     */
    suspend fun hide() {
        animateTo(Hidden)
    }

    fun requireOffset() = draggableState.requireOffset()

    fun updateAnchors(layoutHeight: Int, sheetHeight: Int) {
        val maxDragEndPoint = layoutHeight - 32.dp.toPixel
        val newAnchors = DraggableAnchors {
            HideableBottomSheetValue
                .values()
                .forEach { anchor ->
                    val fractionatedMaxDragEndPoint =
                        maxDragEndPoint * anchor.draggableSpaceFraction
                    val dragEndPoint =
                        layoutHeight - min(fractionatedMaxDragEndPoint, sheetHeight.toFloat())
                    anchor at dragEndPoint
                }
        }
        draggableState.updateAnchors(newAnchors)
    }

    fun isHidingInProgress() = isVisible && targetValue == Hidden

    private suspend fun animateTo(
        targetValue: HideableBottomSheetValue,
        velocity: Float = draggableState.lastVelocity
    ) = draggableState.animateTo(targetValue, velocity)

    companion object {
        /**
         * The default [Saver] implementation for [HideableBottomSheetState].
         */
        fun Saver(
            animationSpec: AnimationSpec<Float> = HideableBottomSheetDefaults.AnimationSpec,
            confirmValueChange: (HideableBottomSheetValue) -> Boolean = { true },
            onDismiss: () -> Unit = {},
        ): Saver<HideableBottomSheetState, HideableBottomSheetValue> =
            Saver(
                save = { it.currentValue },
                restore = {
                    HideableBottomSheetState(
                        initialValue = it,
                        animationSpec = animationSpec,
                        confirmValueChange = confirmValueChange,
                        onDismiss = onDismiss,
                    )
                }
            )
    }
}

object HideableBottomSheetDefaults {
    val AnimationSpec = SpringSpec<Float>()

    val PositionalThreshold = { distance: Float -> distance * 0.2f }

    val VelocityThreshold = { 125.dp.toPixel }
}

enum class HideableBottomSheetValue {
    Hidden,
    HalfExpanded,
    Expanded;

    val draggableSpaceFraction: Float
        get() = when (this) {
            Hidden -> 0f
            HalfExpanded -> 0.5f
            Expanded -> 1f
        }
}

val Dp.toPixel: Float
    get() = value * Resources.getSystem().displayMetrics.density
