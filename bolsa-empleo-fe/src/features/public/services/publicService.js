// bolsa-empleo-fe/src/features/public/services/publicService.js
import { api } from '../../../shared/api/apiClient'

export const publicService = {
    getPuestosPublicos: (titulo) =>
        api.get(titulo ? `/public/puestos?titulo=${titulo}` : '/public/puestos'),
    getPuestoDetalle: (id) =>
        api.get(`/public/puestos/${id}`)
}