// bolsa-empleo-fe/src/features/empresa/services/empresaService.js
import { api } from '../../../shared/api/apiClient'

export const empresaService = {
    getPerfil:       ()       => api.get('/empresa/perfil'),
    updatePerfil:    (body)   => api.put('/empresa/perfil', body),
    getMisPuestos:   ()       => api.get('/empresa/puestos'),
    createPuesto:    (body)   => api.post('/empresa/puestos', body),
    updatePuesto:    (id, b)  => api.put(`/empresa/puestos/${id}`, b),
    deletePuesto:    (id)     => api.delete(`/empresa/puestos/${id}`)
}