package ru.maksonic.rdpodcast.core.base

/**
 * @Author: maksonic on 05.02.2022
 */
abstract class Abstract {

    interface Object<T, M : Mapper> {

        fun map(mapper: M): T
    }

    interface DataObject

    interface CloudObject

    interface Mapper {

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(e: Exception): R
        }

        interface DomainToUi<S, T> : Data<S, T> {
            fun map(errorType: ErrorType): T
        }

        class Empty : Mapper
    }
}