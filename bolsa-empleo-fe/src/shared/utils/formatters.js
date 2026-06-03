// bolsa-empleo-fe/src/shared/utils/formatters.js
export function formatSalario(valor) {
    if (valor == null) return '—'
    return new Intl.NumberFormat('es-CR', {
        style: 'currency',
        currency: 'CRC',
        minimumFractionDigits: 0
    }).format(valor)
}

export function formatFecha(isoString) {
    if (!isoString) return '—'
    return new Date(isoString).toLocaleDateString('es-CR')
}