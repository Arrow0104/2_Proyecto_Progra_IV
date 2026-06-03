// bolsa-empleo-fe/src/features/auth/services/authService.js
import { api } from '../../../shared/api/apiClient'

export const authService = {
    login: (correo, password) => api.post('/auth/login', { correo, password })
}