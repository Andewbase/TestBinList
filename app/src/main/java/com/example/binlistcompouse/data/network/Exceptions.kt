package com.example.binlistcompouse.data.network

open class AppException : RuntimeException {
    constructor() : super()
    constructor(cause: Throwable) : super(cause)
}

class StorageException(cause: Throwable): AppException(cause = cause)

class EmptyException: AppException()

class NotTheAppropriateSizeException: AppException()

class DeleteOrAddOneCharacterException: AppException()

class LimitException(cause: Throwable): AppException(cause = cause)

class ThereIsNoBankCard(cause: Throwable): AppException(cause = cause)

class ConnectionException(cause: Throwable) : AppException(cause = cause)

class ParseBackendResponseException(
    cause: Throwable
) : AppException(cause = cause)

