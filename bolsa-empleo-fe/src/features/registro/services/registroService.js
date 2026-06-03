// bolsa-empleo-fe/src/features/registro/services/registroService.js
import { api } from '../../../shared/api/apiClient'

export const registroService = {
    registrarEmpresa:  (body) => api.post('/registro/empresa',  body),
    registrarOferente: (body) => api.post('/registro/oferente', body)
}