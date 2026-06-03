// bolsa-empleo-fe/src/shared/api/apiError.js
export function getErrorMessage(error) {
    if (typeof error === 'string') return error
    if (error?.mensaje) return error.mensaje
    if (error?.message) return error.message
    return 'Ocurrió un error inesperado'
}