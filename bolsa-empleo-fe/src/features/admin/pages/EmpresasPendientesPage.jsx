// bolsa-empleo-fe/src/features/admin/pages/EmpresasPendientesPage.jsx
import { useEffect, useState } from 'react'
import { adminService } from '../services/adminService'
import AprobacionCard from '../components/AprobacionCard'
import Loading from '../../../shared/components/Loading'
import Alert from '../../../shared/components/Alert'

export default function EmpresasPendientesPage() {
    const [empresas, setEmpresas] = useState([])
    const [cargando, setCargando] = useState(true)
    const [mensaje, setMensaje] = useState({ tipo: '', texto: '' })

    function cargar() {
        setCargando(true)
        adminService.getEmpresas()
            .then(setEmpresas)
            .catch(() => setMensaje({ tipo: 'error', texto: 'Error cargando empresas' }))
            .finally(() => setCargando(false))
    }

    useEffect(() => { cargar() }, [])

    async function activar(id) {
        await adminService.activarEmpresa(id)
        setMensaje({ tipo: 'success', texto: 'Empresa activada' })
        cargar()
    }

    async function desactivar(id) {
        await adminService.desactivarEmpresa(id)
        setMensaje({ tipo: 'success', texto: 'Empresa desactivada' })
        cargar()
    }

    if (cargando) return <Loading />

    return (
        <div>
            <h2>Gestión de empresas</h2>
            <Alert tipo={mensaje.tipo} mensaje={mensaje.texto} />
            {empresas.length === 0
                ? <p>No hay empresas registradas.</p>
                : empresas.map(e => (
                    <AprobacionCard key={e.idEmpresa} entidad={e} tipo="empresa"
                                    onActivar={activar} onDesactivar={desactivar} />
                ))
            }
        </div>
    )
}