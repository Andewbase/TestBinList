package com.example.binlistcompouse.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.example.binlistcompouse.Const.ELEVEN_INT
import com.example.binlistcompouse.Const.FIFTEEN
import com.example.binlistcompouse.Const.FOURTEEN_INT
import com.example.binlistcompouse.Const.FOUR_INT
import com.example.binlistcompouse.Const.NINETEEN_INT
import com.example.binlistcompouse.Const.NINE_INT
import com.example.binlistcompouse.Const.ONE_INT
import com.example.binlistcompouse.Const.SEPARATOR
import com.example.binlistcompouse.Const.SEVEN_INT
import com.example.binlistcompouse.Const.SIXTEEN_INT
import com.example.binlistcompouse.Const.SIX_INT
import com.example.binlistcompouse.Const.STAR
import com.example.binlistcompouse.Const.THREE_INT
import com.example.binlistcompouse.Const.TWO_INT

fun creditCard(text: AnnotatedString): TransformedText{
    // Making XXXX XXXX **** **** string.
    val trimmed = if (text.text.length >= SIX_INT) text.text.padEnd(SIXTEEN_INT, STAR) else text.text

    var out = SEPARATOR
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % FOUR_INT == THREE_INT && i != FIFTEEN) out += " "
    }


    /**
     * The offset translator should ignore the hyphen characters, so conversion from
     *  original offset to transformed text works like
     *  - The 4th char of the original text is 5th char in the transformed text.
     *  - The 13th char of the original text is 15th char in the transformed text.
     *  Similarly, the reverse conversion works like
     *  - The 5th char of the transformed text is 4th char in the original text.
     *  - The 12th char of the transformed text is 10th char in the original text.
     */
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= THREE_INT) return offset
            if (offset <= SEVEN_INT) return offset + ONE_INT
            if (offset <= ELEVEN_INT) return offset + TWO_INT
            if (offset <= SIXTEEN_INT) return offset + THREE_INT
            return NINETEEN_INT
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= FOUR_INT) return offset
            if (offset <= NINE_INT) return offset - ONE_INT
            if (offset <= FOURTEEN_INT) return offset - TWO_INT
            if (offset <= NINETEEN_INT) return offset - THREE_INT
            return SIXTEEN_INT
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}