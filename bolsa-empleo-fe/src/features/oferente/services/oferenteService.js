// bolsa-empleo-fe/src/features/oferente/services/oferenteService.js
import { api } from '../../../shared/api/apiClient'

export const oferenteService = {
    getPerfil:          ()       => api.get('/oferente/perfil'),
    updatePerfil:       (body)   => api.put('/oferente/perfil', body),
    getMisHabilidades:  ()       => api.get('/oferente/habilidades'),
    agregarHabilidad:   (body)   => api.post('/oferente/habilidades', body),
    eliminarHabilidad:  (id)     => api.delete(`/oferente/habilidades/${id}`),
    getPuestosDisp:     (titulo) => api.get(titulo ? `/oferente/puestos?titulo=${titulo}` : '/oferente/puestos'),
    subirCv: (file) => {
        const token = localStorage.getItem('token')
        const fd = new FormData()
        fd.append('file', file)
        return fetch('/api/oferente/cv', {
            method: 'POST',
            headers: { Authorization: `Bearer ${token}` },
            body: fd
        }).then(r => r.ok ? r.json() : r.json().then(e => Promise.reject(e)))
    }
}