// bolsa-empleo-fe/src/features/admin/pages/OferentesPendientesPage.jsx
import { useEffect, useState } from 'react'
import { adminService } from '../services/adminService'
import AprobacionCard from '../components/AprobacionCard'
import Loading from '../../../shared/components/Loading'
import Alert from '../../../shared/components/Alert'

export default function OferentesPendientesPage() {
    const [oferentes, setOferentes] = useState([])
    const [cargando, setCargando] = useState(true)
    const [mensaje, setMensaje] = useState({ tipo: '', texto: '' })

    function cargar() {
        setCargando(true)
        adminService.getOferentes()
            .then(setOferentes)
            .catch(() => setMensaje({ tipo: 'error', texto: 'Error cargando oferentes' }))
            .finally(() => setCargando(false))
    }

    useEffect(() => { cargar() }, [])

    async function activar(id) {
        await adminService.activarOferente(id)
        setMensaje({ tipo: 'success', texto: 'Oferente activado' })
        cargar()
    }

    async function desactivar(id) {
        await adminService.desactivarOferente(id)
        setMensaje({ tipo: 'success', texto: 'Oferente desactivado' })
        cargar()
    }

    if (cargando) return <Loading />

    return (
        <div>
            <h2>Gestión de oferentes</h2>
            <Alert tipo={mensaje.tipo} mensaje={mensaje.texto} />
            {oferentes.length === 0
                ? <p>No hay oferentes registrados.</p>
                : oferentes.map(o => (
                    <AprobacionCard key={o.idOferente} entidad={o} tipo="oferente"
                                    onActivar={activar} onDesactivar={desactivar} />
                ))
            }
        </div>
    )
}