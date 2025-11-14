package com.andres.rangel.vitalyn.authentication.data.remote

import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginRequestDto
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class LoginMockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Thread.sleep(1500)

        return when (request.url.encodedPath) {
            "/auth/login" -> mockLoginResponse(request)
            else -> errorResponse(chain, 404, "La ruta solicitada no existe")
        }
    }

    private fun mockLoginResponse(request: Request): Response {
        val emailMock = "[EMAIL]"
        val passwordMock = "[PASSWORD]"

        request.body ?: return errorResponse(null, 400, "Bad request")

        val buffer = okio.Buffer()
        request.body?.writeTo(buffer)

        val bodyString = buffer.readUtf8()

        val json = Gson().fromJson(bodyString, LoginRequestDto::class.java)

        if (emailMock != json.email || passwordMock != json.password) {
            return errorResponse(null, 401, "Credenciales incorrectas")
        }

        val responseJson = """
            {
              "email": "$emailMock",
              "name": "Administrador",
              "state": "1"
            }
        """.trimIndent()

        return successResponse(request, responseJson)
    }

    private fun successResponse(request: Request, json: String): Response =
        Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message(json)
            .body(json.toResponseBody("application/json".toMediaType()))
            .build()

    private fun errorResponse(chain: Interceptor.Chain?, code: Int, json: String) =
        Response.Builder()
            .request(chain?.request() ?: Request.Builder().url("http://localhost/").build())
            .protocol(Protocol.HTTP_1_1)
            .code(code)
            .message(json)
            .body(json.toResponseBody("application/json".toMediaType()))
            .build()
}
