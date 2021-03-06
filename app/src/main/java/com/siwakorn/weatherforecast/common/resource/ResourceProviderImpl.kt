package com.siwakorn.weatherforecast.common.resource

import android.content.Context

class ResourceProviderImpl constructor(
    private val context: Context
) : ResourceProvider {

    override fun string(id: Int): String = context.getString(id)

    override fun string(id: Int, value: String): String = context.getString(id, value)

    override fun string(id: Int, vararg strings: String): String = context.getString(id, *strings)

}