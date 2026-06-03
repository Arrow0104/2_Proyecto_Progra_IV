// bolsa-empleo-fe/src/features/admin/services/adminService.js
import { api } from '../../../shared/api/apiClient'

export const adminService = {
    getEmpresas:         ()        => api.get('/admin/empresas'),
    activarEmpresa:      (id)      => api.put(`/admin/empresas/${id}/activar`),
    desactivarEmpresa:   (id)      => api.put(`/admin/empresas/${id}/desactivar`),
    getOferentes:        ()        => api.get('/admin/oferentes'),
    activarOferente:     (id)      => api.put(`/admin/oferentes/${id}/activar`),
    desactivarOferente:  (id)      => api.put(`/admin/oferentes/${id}/desactivar`),
    getUsuarios:         ()        => api.get('/admin/usuarios'),
    cambiarEstado:       (id, est) => api.put(`/admin/usuarios/${id}/estado?estado=${est}`),
    getCaracteristicas:  ()        => api.get('/caracteristicas'),
    crearCaracteristica: (body)    => api.post('/caracteristicas', body),
    eliminarCaracteristica: (id)   => api.delete(`/caracteristicas/${id}`)
}